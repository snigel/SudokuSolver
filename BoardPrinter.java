import java.util.Map;
import java.util.Set;

public class BoardPrinter {
    public static void print(Map<Integer, Set<Integer>> boardSet){
        Set<Integer> coordinate;
        for(int row = 0; row < 9; row++) {
            if(row % 3 == 0){
                System.out.println("-------------");
            }
            for (int col = 0; col < 9; col++) {
                coordinate = boardSet.get(10*row+col);
                if(col%3==0){
                    System.out.print("|");
                }
                if(coordinate.isEmpty()){
                    System.out.print("_");
                } else {
                    System.out.print(coordinate.iterator().next().toString());
                }
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    public static void printSize(Map<Integer, Set<Integer>> boardSet){
        for(int row=0; row<9; row++) {
            if(row % 3 == 0){
                System.out.println("-------------");
            }
            for (int col=0; col<9; col++) {
                if(col % 3 == 0){
                    System.out.print("|");
                }
                System.out.print(boardSet.get(10*row+col).size());
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }
}
