package com.github.snigel.sudokuSolver;

public class BoardNotValidException extends Exception {
    public BoardNotValidException(String errorMessage) {
        super(errorMessage);
    }
}
