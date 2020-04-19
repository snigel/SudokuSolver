package com.github.snigel.sudokuSolver;

public class Sudoku {
    private BoardChecker checker;
    private BoardSolver solver;
    private BoardPrinter boardPrinter;

    public Sudoku(final int[][] board) {
        newGame(board);
    }

    public void newGame(final int[][] boardArr) {
        Board board = null;
        try {
            board = new Board(boardArr);
        } catch (BoardNotValidException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        this.solver = new BoardSolver(board);
        this.boardPrinter = new BoardPrinter(board);
        this.checker = new BoardChecker(board);
    }

    public boolean solve() {
        try {
            return solver.solveBoard();
        } catch (BoardNotValidException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean check() {
        return checker.check();
    }

    public void print_before() {
        System.out.println(boardPrinter.print());
    }
    public void print_result() {
        System.out.println(boardPrinter.print());
        System.out.println("Solver: " + solver.getMessages().toString());
        System.out.println("Checker: " + checker.getMessages().toString());
    }
}
