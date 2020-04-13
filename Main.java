public class Main {
    public static void main(String[] args) {
        final int[][] ezboard = new int[][] {
            {5,3,0, 0,7,0, 0,0,0},
            {6,0,0, 1,9,5, 0,0,0},
            {0,9,8, 0,0,0, 0,6,0},

            {8,0,0, 0,6,0, 0,0,3},
            {4,0,0, 8,0,3, 0,0,1},
            {7,0,0, 0,2,0, 0,0,6},

            {0,6,0, 0,0,0, 2,8,0},
            {0,0,0, 4,1,9, 0,0,5},
            {0,0,0, 0,8,0, 0,7,9}
        };
        final int[][] ggboard = new int[][] {
            {0,0,2, 5,0,0, 0,7,0},
            {0,0,4, 1,6,0, 0,8,0},
            {0,5,8, 0,0,0, 0,4,0},

            {0,0,0, 0,2,5, 0,0,3},
            {0,0,0, 0,8,1, 0,0,7},
            {1,6,0, 0,0,0, 0,0,0},

            {8,0,0, 0,0,7, 1,0,0},
            {0,0,9, 3,0,2, 7,0,0},
            {3,0,0, 0,0,0, 5,0,0}
        };
        BoardSolver boardSolver = new BoardSolver(ggboard);
        BoardPrinter.print(boardSolver.getBoardSet());
        new BoardChecker(boardSolver.getSolvedBoard());
    }
}
