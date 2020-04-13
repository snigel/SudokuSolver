import java.util.*;

public class Sudoku {
    private Board board;
    private BoardChecker checker;
    private BoardSolver solver;
    private BoardPrinter printer;

    public Sudoku(int[][] board) {
        this.board = new Board(board);
        this.solver = new BoardSolver(this.board);
        this.printer = new BoardPrinter(this.board);
        this.checker = new BoardChecker(this.board);
    }

    public void solve(){
        System.out.println("Took " + solver.solveBoard() + " loops to solve Sudoku.");
    }

    public void check(){
        checker.check();
    }

    public void print(){
        printer.print();
    }
}
