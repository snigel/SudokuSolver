public class Sudoku {
    private BoardChecker checker;
    private BoardSolver solver;
    private BoardPrinter printer;

    public Sudoku(int[][] board) {
        newGame(board);
    }

    public void newGame(int[][] boardArr){
        Board board = new Board(boardArr);
        this.solver = new BoardSolver(board);
        this.printer = new BoardPrinter(board);
        this.checker = new BoardChecker(board);
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
