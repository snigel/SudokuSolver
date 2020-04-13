import java.util.*;

public class BoardSolver {
    private Board sudoku;

    public BoardSolver(Board sudoku) {
        this.sudoku = sudoku;
    }

    public int solveBoard(){
        int size = 0;
        Set<Integer> coordinate;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if(sudoku.unknown(row, col))
                    sudoku.remove(row,col,excludeFromRow(row));
                if(sudoku.unknown(row, col))
                    sudoku.remove(row,col,excludeFromCol(col));
                if(sudoku.unknown(row, col))
                    sudoku.remove(row,col,excludeFromBox(row, col));
                if(sudoku.unknown(row, col))
                    sudoku.remove(row,col,deduceFromBox(row, col));
                size += sudoku.size(row, col);
            }
        }
        if(size > 81){
            return 1 + solveBoard();
        } else {
            return 1;
        }
    }

    private Set<Integer> excludeFromCol(int col){
        Set<Integer> colSet = new HashSet<>();

        for(int row=0; row<9; row++){
            if(sudoku.knownValue(row, col)){
                colSet.add(sudoku.getValue(row, col));
            }
        }
        return colSet;
    }

    private Set<Integer> excludeFromRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int col=0; col<9; col++){
            if(sudoku.knownValue(row, col)){
                rowSet.add(sudoku.getValue(row, col));
            }
        }
        return rowSet;
    }

    private Set<Integer> excludeFromBox(int r, int c){
        // Modify input coords to point to the start
        // of a box, instead of within it.
        int boxRow = (r/3)*3;
        int boxCol = (c/3)*3;

        Set<Integer> boxSet = new HashSet<>();
        for(int row=boxRow; row < boxRow+3; row++){
            for(int col=boxCol; col < boxCol+3; col++){
                if(sudoku.knownValue(row, col)){
                    boxSet.add(sudoku.getValue(row, col));
                }
            }
        }
        return boxSet;
    }

    private Set<Integer> deduceFromBox(int r, int c){
        // Checks if there's one single value that no neighbors
        // in the box can have.
        int boxRow = (r/3)*3;
        int boxCol = (c/3)*3;
        Set<Integer> boxSet = new HashSet<>();

        for(int row=boxRow; row < boxRow+3; row++){
            for(int col=boxCol; col < boxCol+3; col++){
                if(!(col==c && row==r)){ //Exclude current coordinates.
                    boxSet.addAll(sudoku.getAll(row, col));
                }
            }
        }

        //Make a work copy of current coordinates.
        Set<Integer> testSet = new HashSet<>(sudoku.getAll(r, c));
        testSet.removeAll(boxSet);

        //If result has one remaining number, return boxSet to do removeAll on.
        //else, return empty set that is safe to remove from current coord.
        if(testSet.size() == 1){
            return boxSet;
        } else {
            return new HashSet<>();
        }
    }
}
