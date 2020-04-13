import java.util.Map;
import java.util.Set;

public class BoardPrinter {
    Board boardSet;

    public BoardPrinter(Board board){
        this.boardSet = board;
    }

    public void print(){
        for(int row = 0; row < 9; row++) {
            if(row % 3 == 0){
                System.out.println("-------------");
            }
            for (int col = 0; col < 9; col++) {
                if(col%3==0){
                    System.out.print("|");
                }
                if(boardSet.knownValue(row, col)){
                    System.out.print(boardSet.getValue(row, col).toString());

                } else {
                    System.out.print("_");
                }
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    public void printSize(){
        for(int row=0; row<9; row++) {
            if(row % 3 == 0){
                System.out.println("-------------");
            }
            for (int col=0; col<9; col++) {
                if(col % 3 == 0){
                    System.out.print("|");
                }
                System.out.print(boardSet.getAll(row, col).size());
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }
}
