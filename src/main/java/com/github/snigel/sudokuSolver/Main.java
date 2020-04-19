package com.github.snigel.sudokuSolver;

public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(Puzzles.ggboard);

        System.out.println("Board one:");
        sudoku.print_before();
        System.out.println("After: ");
        sudoku.solve();
        sudoku.check();
        sudoku.print_result();

        System.out.println("\n");

        System.out.println("Board two:");
        sudoku.newGame(Puzzles.ezboard);
        sudoku.print_before();
        System.out.println("After: ");
        sudoku.solve();
        sudoku.check();
        sudoku.print_result();
    }
}
