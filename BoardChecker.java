import java.util.HashSet;
import java.util.Set;

public class BoardChecker {
    private int[][] board;

    public BoardChecker(int[][] board){
        this.board = board;
        checkAll();
    }

    private void checkAll(){
        boolean correct = true;
        for(int i=0; i<9; i++){
            if(!checkCol(i)){
                System.out.println("Error at column " + i);
                correct = false;
            }
        }
        for(int i=0; i<9; i++){
            if(!checkRow(i)){
                System.out.println("Error at row " + i);
                correct = false;
            }
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(!checkBox(i, j)){
                    System.out.println("Error in 3x3 board at " + i + ", " + j);
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

        for(int i=0; i<9; i++){
            colSet.add(board[i][col]);
        }
        return colSet.size() == 9;
    }

    public boolean checkRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int i=0; i<9; i++){
            rowSet.add(board[row][i]);
        }
        return rowSet.size() == 9;
    }

    public boolean checkBox(int row, int col){
        Set<Integer> boxSet = new HashSet<>();
        int boxRow = row*3;
        int boxCol = col*3;
        for(int i=boxRow; i<3+boxRow; i++){
            for(int j=boxCol; j<3+boxCol; j++){
                boxSet.add(board[i][j]);
            }
        }
        return boxSet.size() == 9;
    }
}
