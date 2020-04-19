package com.github.snigel.sudokuSolver;

import java.util.*;

public class Board {
    private final int[][] board;
    private Map<Integer, Set<Integer>> boardSet;
    private int size;
    private boolean changed;

    public Board(final int[][] board) throws BoardNotValidException {
        this.board = board;
        this.changed = false;
        resetBoard();
    }

    public boolean changed() {
        boolean temp = changed;
        changed = false;
        return temp;
    }

    public Set<Integer> getAll(int row, int col) {
        return boardSet.get(coord2key(row, col));
    }

    public Integer getValue(int row, int col) {
        return getAll(row, col).iterator().next();
    }

    public void remove(int row, int col, Set<Integer> set) throws BoardNotValidException {
        int before = size(row, col);
        getAll(row, col).removeAll(set);
        int after = size(row, col);
        size -= (before - after);
        if (before != after) {
            changed = true;
        }
        if (size(row, col) == 0){
            throw new BoardNotValidException("Removed ALL alternatives for cell " + row + " " + col);
        }
    }

    private void put(int row, int col, int num) throws BoardNotValidException {
        List<Integer> oneToNine = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});

        if (num == 0){
            boardSet.put(coord2key(row, col), new HashSet<>(oneToNine));
            size += 9;
        } else if (oneToNine.contains(board[row][col])) {
            boardSet.put(coord2key(row, col), new HashSet<Integer>());
            getAll(row, col).add(board[row][col]);
            size += 1;
        } else {
            String message = "Error at row: " + row + ", col: " + col + "\n";
            message += "Cell contains number " + board[row][col] + ", which is out of bounds.";
            throw new BoardNotValidException(message);
        }
    }

    public boolean isDone() {
        return size == 81;
    }

    public boolean knownValue(int row, int col) {
        return size(row, col) == 1;
    }

    public int size(int row, int col) {
        return getAll(row, col).size();
    }

    public void resetBoard() throws BoardNotValidException {
        this.boardSet = new HashMap<>();
        size = 0;
        initBoardSet(board);
    }

    private void initBoardSet(int[][] board) throws BoardNotValidException {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                put(row, col, board[row][col]);
            }
        }
    }

    private int coord2key(int row, int col) {
        return row * 10 + col;
    }
}
