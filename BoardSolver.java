import java.util.*;

public class BoardSolver {
    private final Map<Integer, Set<Integer>> boardSet;

    public BoardSolver(int[][] board) {
        this.boardSet = new HashMap<>();
        initBoardSet(board);

        System.out.println("Took " + solveBoard() + " loops to solve Sudoku.");
    }

    public void initBoardSet(int[][] board){
        Integer[] newSet = {1,2,3,4,5,6,7,8,9};
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                if(board[row][col] == 0){
                    boardSet.put(c(row, col), new HashSet<>(Arrays.asList(newSet)));
                } else{
                    boardSet.put(c(row, col), new HashSet<>());
                    boardSet.get(c(row, col)).add(board[row][col]);
                }
            }
        }
    }

    public int solveBoard(){
        int size = 0;
        Set<Integer> coordinate;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                coordinate = boardSet.get(c(row, col));
                if(coordinate.size() >1)
                    coordinate.removeAll(excludeFromRow(row));
                if(coordinate.size() >1)
                    coordinate.removeAll(excludeFromCol(col));
                if(coordinate.size() >1)
                    coordinate.removeAll(excludeFromBox(row, col));
                if(coordinate.size() >1)
                    coordinate.removeAll(deduceFromBox(row, col));
                size += coordinate.size();
            }
        }
        if(size > 81){
            return 1 + solveBoard();
        } else {
            return 1;
        }
    }

    public Set<Integer> excludeFromCol(int col){
        Set<Integer> colSet = new HashSet<>();

        for(int row=0; row<9; row++){
            if(boardSet.get(c(row, col)).size() == 1){
                colSet.add(boardSet.get(c(row, col)).iterator().next());
            }
        }
        return colSet;
    }

    public Set<Integer> excludeFromRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int col=0; col<9; col++){
            if(boardSet.get(c(row, col)).size() == 1){
                rowSet.add(boardSet.get(c(row, col)).iterator().next());
            }
        }
        return rowSet;
    }
    public Set<Integer> excludeFromBox(int r, int c){
        // Modify input coords to point to the start
        // of a box, instead of within it.
        int boxRow = (r/3)*3;
        int boxCol = (c/3)*3;

        Set<Integer> boxSet = new HashSet<>();
        for(int row=boxRow; row < boxRow+3; row++){
            for(int col=boxCol; col < boxCol+3; col++){
                if(boardSet.get(c(row, col)).size() == 1){
                    boxSet.add(boardSet.get(c(row, col)).iterator().next());
                }

            }
        }
        return boxSet;
    }

    public Set<Integer> deduceFromBox(int r, int c){
        // Checks if there's one single value that no neighbors
        // in the box can have.
        int boxRow = (r/3)*3;
        int boxCol = (c/3)*3;
        Set<Integer> boxSet = new HashSet<>();

        for(int row=boxRow; row < boxRow+3; row++){
            for(int col=boxCol; col < boxCol+3; col++){
                if(!(col==c && row==r)){ //Exclude current coordinates.
                    boxSet.addAll(boardSet.get(c(row, col)));
                }
            }
        }

        //Make a work copy of current coordinates.
        Set<Integer> testSet = new HashSet<>(boardSet.get(c(r, c)));
        testSet.removeAll(boxSet);

        //If result has one remaining number, return boxSet to do removeAll on.
        //else, return empty set that is safe to remove from current coord.
        if(testSet.size() == 1){
            return boxSet;
        } else {
            return new HashSet<>();
        }
    }

    public int[][] getSolvedBoard(){
        int[][] board = new int[9][9];
        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                    board[row][col] = boardSet.get(c(row, col)).iterator().next();
                }
            }
        return board;
    }

    private int c(int row, int col){
        //Converts row,col to a single value for hashmap key.
        //2,3 becomes 23.
        return row * 10 + col;
    }

    public Map<Integer, Set<Integer>> getBoardSet() {
        return this.boardSet;
    }
}
