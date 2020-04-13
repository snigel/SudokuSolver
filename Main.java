public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(Puzzles.ggboard);
        sudoku.solve();
        sudoku.check();
        sudoku.print();
        sudoku.newGame(Puzzles.ezboard);
        sudoku.solve();
        sudoku.check();
        sudoku.print();
    }
}
