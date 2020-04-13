import java.util.HashSet;
import java.util.Set;

public class BoardChecker {
    private final Board board;

    public BoardChecker(Board board){
        this.board = board;
    }

    public void check(){
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
            colSet.add(board.getValue(row, col));
        }
        return colSet.size() == 9;
    }

    public boolean checkRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int col=0; col<9; col++){
            rowSet.add(board.getValue(row, col));
        }
        return rowSet.size() == 9;
    }

    public boolean checkBox(int row, int col){
        Set<Integer> boxSet = new HashSet<>();
        int boxRow = row*3;
        int boxCol = col*3;
        for(int r=boxRow; r < 3+boxRow; r++){
            for(int c=boxCol; c < 3+boxCol; c++){
                boxSet.add(board.getValue(r, c));
            }
        }
        return boxSet.size() == 9;
    }
}
