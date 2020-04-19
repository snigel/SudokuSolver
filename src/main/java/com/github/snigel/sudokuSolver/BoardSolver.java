package com.github.snigel.sudokuSolver;

import java.util.*;

public class BoardSolver {
    private final Board board;
    private final Set<String> messages;

    public BoardSolver(Board board) {
        this.board = board;
        this.messages = new HashSet<String>();
    }

    public Set getMessages(){
        return messages;
    }

    public boolean solveBoard() throws BoardNotValidException {
        int loops = 0;
        while(!board.isDone()) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    solveCell(row, col);
                }
            }
            loops++;
            if(!board.changed()){
                messages.add("Couldn't solve board!");
                return false;
            }
        }
        messages.add("Took " + loops + " loops to solve the board!");
        return true;
    }

    private void solveCell(int row, int col) throws BoardNotValidException {
        if (!board.knownValue(row, col)){
            board.remove(row, col, excludeFromRow(row, col));
            board.remove(row, col, excludeFromCol(row, col));
            board.remove(row, col, excludeFromBox(row, col));
            board.remove(row, col, deduceFromBox(row, col));
        }
    }

    private Set<Integer> excludeFromCol(int r, int col){
        Set<Integer> colSet = new HashSet<>();

        if(board.knownValue(r, col)){
            return colSet;
        }

        for(int row=0; row<9; row++){
            if(board.knownValue(row, col)){
                colSet.add(board.getValue(row, col));
            }
        }
        return colSet;
    }

    private Set<Integer> excludeFromRow(int row, int c){
        Set<Integer> rowSet = new HashSet<>();

        if(board.knownValue(row, c)){
            return rowSet;
        }

        for(int col=0; col<9; col++){
            if(board.knownValue(row, col)){
                rowSet.add(board.getValue(row, col));
            }
        }
        return rowSet;
    }

    private Set<Integer> excludeFromBox(int r, int c){
        Set<Integer> boxSet = new HashSet<>();

        if(board.knownValue(r, c)){
            return boxSet;
        }

        // Modify input coords to point to the start
        // of a box, instead of within it.
        int boxRow = (r/3)*3;
        int boxCol = (c/3)*3;

        for(int row=boxRow; row < boxRow+3; row++){
            for(int col=boxCol; col < boxCol+3; col++){
                if(board.knownValue(row, col)){
                    boxSet.add(board.getValue(row, col));
                }
            }
        }
        return boxSet;
    }

    private Set<Integer> deduceFromBox(int r, int c){
        Set<Integer> boxSet = new HashSet<>();
        if(board.knownValue(r, c)){
            return boxSet;
        }

        // Checks if there's one single value that no neighbors
        // in the box can have.
        int boxRow = (r/3)*3;
        int boxCol = (c/3)*3;

        for(int row=boxRow; row < boxRow+3; row++){
            for(int col=boxCol; col < boxCol+3; col++){
                if(!(col==c && row==r)){ //Exclude current coordinates.
                    boxSet.addAll(board.getAll(row, col));
                }
            }
        }

        //Make a work copy of current coordinates.
        Set<Integer> testSet = new HashSet<>(board.getAll(r, c));
        testSet.removeAll(boxSet);

        //If result set has one remaining number, return boxSet to do removeAll on.
        //else, return empty set that is safe to remove from current coord.
        if(testSet.size() == 1){
            return boxSet;
        } else {
            return new HashSet<>();
        }
    }
}
