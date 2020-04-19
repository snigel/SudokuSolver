package com.github.snigel.sudokuSolver;

import java.util.HashSet;
import java.util.Set;

public class BoardChecker {
    private final Board board;
    private final Set<String> messages;

    public BoardChecker(Board board) {
        this.board = board;
        this.messages = new HashSet<String>();
    }

    public boolean check() {
        boolean isValid = true;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if( !(checkRow(row) && checkCol(col) && checkBox(row, col)) ) {
                    isValid = false;
                }
            }
        }
        if (isValid) {
            messages.add("All rows, columns and 3x3 boxes are valid!");
        }
        return isValid;
    }

    private boolean checkCol(int col) {
        Set<Integer> colSet = new HashSet<>();

        for (int row = 0; row < 9; row++) {
            colSet.add(board.getValue(row, col));
        }

        if(colSet.size() == 9){
            return true;
        } else {
            messages.add("Error at col " + col);
            return false;
        }
    }

    private boolean checkRow(int row){
        Set<Integer> rowSet = new HashSet<>();

        for(int col = 0; col < 9; col++){
            rowSet.add(board.getValue(row, col));
        }

        if(rowSet.size() == 9){
            return true;
        } else {
            messages.add("Error at row " + row);
            return false;
        }
    }

    private boolean checkBox(int row, int col) {
        Set<Integer> boxSet = new HashSet<>();
        int boxRow = row * 3;
        int boxCol = col * 3;
        for (int r = boxRow; r < 3 + boxRow; r++) {
            for (int c = boxCol; c < 3 + boxCol; c++) {
                boxSet.add(board.getValue(r, c));
            }
        }

        if(boxSet.size() == 9){
            return true;
        } else {
            messages.add("Error in 3x3 board at " + row + ", " + col);
            return false;
        }
    }

    public Set<String> getMessages(){
        return messages;
    }

}
