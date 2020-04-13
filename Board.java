import java.util.*;

public class Board {
    final private int[][] board;
    private Map<Integer, Set<Integer>> boardSet;

    public Board(int[][] board) {
        this.board = board;
        resetBoard();
    }

    public Set<Integer> getAll(int row, int col){
        return boardSet.get(c(row, col));
    }

    public Integer getValue(int row, int col){
        return getAll(row, col).iterator().next();
    }

    public void remove(int row, int col, Collection set){
        getAll(row, col).removeAll(set);
    }

    public boolean knownValue(int row, int col){
        return size(row, col) == 1;
    }

    public boolean unknown(int row, int col){
        return !knownValue(row, col);
    }

    public int size(int row, int col){
        return getAll(row, col).size();
    }

    public void resetBoard(){
        this.boardSet = new HashMap<>();
        initBoardSet(board);
    }

    private void initBoardSet(int[][] board){

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

    private int c(int row, int col){
        return row * 10 + col;
    }
}
