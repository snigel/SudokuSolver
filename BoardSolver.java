import java.util.*;

public class BoardSolver {
    private final Board board;

    public BoardSolver(Board board) {
        this.board = board;
    }

    public int solveBoard(){
        int loops = 0;
        while(!board.isDone()) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (!board.knownValue(row, col))
                        board.remove(row, col, excludeFromRow(row));
                    if (!board.knownValue(row, col))
                        board.remove(row, col, excludeFromCol(col));
                    if (!board.knownValue(row, col))
                        board.remove(row, col, excludeFromBox(row, col));
                    if (!board.knownValue(row, col))
                        board.remove(row, col, deduceFromBox(row, col));
                }
            }
            loops++;
        }
        return loops;
    }

    private Set<Integer> excludeFromCol(int col){
        Set<Integer> colSet = new HashSet<>();

        for(int row=0; row<9; row++){
            if(board.knownValue(row, col)){
                colSet.add(board.getValue(row, col));
            }
        }
        return colSet;
    }

    private Set<Integer> excludeFromRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int col=0; col<9; col++){
            if(board.knownValue(row, col)){
                rowSet.add(board.getValue(row, col));
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
                if(board.knownValue(row, col)){
                    boxSet.add(board.getValue(row, col));
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
                    boxSet.addAll(board.getAll(row, col));
                }
            }
        }

        //Make a work copy of current coordinates.
        Set<Integer> testSet = new HashSet<>(board.getAll(r, c));
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
