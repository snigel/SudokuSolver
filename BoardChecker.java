import java.util.HashSet;
import java.util.Set;

public class BoardChecker {
    private final int[][] board;

    public BoardChecker(int[][] board){
        this.board = board;
        checkAll();
    }

    private void checkAll(){
        boolean correct = true;
        for(int col=0; col<9; col++){
            if(!checkCol(col)){
                System.out.println("Error at column " + col);
                correct = false;
            }
        }
        for(int row=0; row<9; row++){
            if(!checkRow(row)){
                System.out.println("Error at row " + row);
                correct = false;
            }
        }
        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                if(!checkBox(row, col)){
                    System.out.println("Error in 3x3 board at " + row + ", " + col);
                    correct = false;
                }
            }
        }
        if(correct){
            System.out.println("All rows, columns and 3x3 boxes are valid!");
        }
    }

    public boolean checkCol(int col){
        Set<Integer> colSet = new HashSet<>();

        for(int row=0; row<9; row++){
            colSet.add(board[row][col]);
        }
        return colSet.size() == 9;
    }

    public boolean checkRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int col=0; col<9; col++){
            rowSet.add(board[row][col]);
        }
        return rowSet.size() == 9;
    }

    public boolean checkBox(int row, int col){
        Set<Integer> boxSet = new HashSet<>();
        int boxRow = row*3;
        int boxCol = col*3;
        for(int i=boxRow; i < 3+boxRow; i++){
            for(int j=boxCol; j < 3+boxCol; j++){
                boxSet.add(board[i][j]);
            }
        }
        return boxSet.size() == 9;
    }
}
