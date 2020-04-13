import java.util.*;

public class BoardSolver {
    private Map<Integer, HashSet> boardSet;

    public BoardSolver(int[][] board) {
        this.boardSet = new HashMap<Integer, HashSet>();
        initBoardSet(board);
        System.out.println("Needed " + solveBoard() + " loops to solve.");
    }

    public void initBoardSet(int[][] board){
        Integer[] newSet = {1,2,3,4,5,6,7,8,9};
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j]==0){
                    boardSet.put((10*i + j), new HashSet<>(Arrays.asList(newSet)));
                } else{
                    boardSet.put((10*i + j), new HashSet<>());
                    boardSet.get(10*i + j).add(board[i][j]);
                }
            }
        }
    }

    public int solveBoard(){
        int size = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Exclude from row.
                if (boardSet.get((10 * i + j)).size() > 1) {
                    boardSet.get((10 * i + j)).removeAll(excludeFromRow(i));
                }
                if (boardSet.get((10 * i + j)).size() > 1) {
                    boardSet.get((10 * i + j)).removeAll(excludeFromCol(j));
              }
                if (boardSet.get((10 * i + j)).size() > 1) {
                    boardSet.get((10 * i + j)).removeAll(excludeFromBox(i, j));
                    if(boardSet.get((10 * i + j)).size() == 0){
                        System.out.println("3");
                    }                    }
                if (boardSet.get((10 * i + j)).size() > 1) {
                    boardSet.get((10 * i + j)).removeAll(ruleOutFromBoxAlts(i, j));

                }
                size += boardSet.get((10 * i + j)).size();
            }
        }
        if(size>81){
            return 1 + solveBoard();
        }
        else{
            return 1;
        }
    }

    public Set<Integer> excludeFromCol(int col){
        Set<Integer> colSet = new HashSet<>();

        for(int row=0; row<9; row++){
            if(boardSet.get((10 * row + col)).size() == 1){
                colSet.add((Integer) boardSet.get((10 * row + col)).iterator().next());
            }
        }
        return colSet;
    }

    public Set<Integer> excludeFromRow(int row){
        Set<Integer> rowSet = new HashSet<>();
        for(int col=0; col<9; col++){
            if(boardSet.get((10 * row + col)).size() == 1){
                rowSet.add((Integer) boardSet.get((10 * row + col)).iterator().next());
            }
        }
        return rowSet;
    }
    public Set<Integer> excludeFromBox(int r, int c){
        int trow = (r/3)*3;
        int tcol = (c/3)*3;

        Set<Integer> boxSet = new HashSet<>();

        for(int row=trow; row < trow+3; row++){
            for(int col=tcol; col < tcol+3; col++){
                if(boardSet.get((10 * row + col)).size() == 1){
                    boxSet.add((Integer) boardSet.get((10 * row + col)).iterator().next());
                }

            }
        }
        return boxSet;
    }

    public Set<Integer> ruleOutFromBoxAlts(int r, int c){
        int trow = (r/3)*3;
        int tcol = (c/3)*3;
        Set<Integer> boxSet = new HashSet<>();

        for(int row=trow; row < trow+3; row++){
            for(int col=tcol; col < tcol+3; col++){
                if(!(col==c && row==r)){ //Exclude current coordinates.
                    boxSet.addAll(boardSet.get((10 * row + col)));
                }
            }
        }

        //Make a copy of current coordinates.
        Set<Integer> testSet = new HashSet<Integer>();
        testSet.addAll(boardSet.get((10*r + c)));

        //Remove the union from the copy.
        testSet.removeAll(boxSet);

        if(testSet.size() == 1){
            //If result has one remaining number, return boxSet to do removeAll on.
            return boxSet;
        }
        else{
            //Return an empty set, that is safe to do removeAll on.
            return new HashSet<Integer>();
        }
    }

    public int[][] getSolvedBoard(){
        int[][] tboard = new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                    tboard[i][j] = (int) boardSet.get(c(i, j)).iterator().next();
                }
            }
        return tboard;
    }

    private int c(int row, int col){
        //Converts row,col to row*10+col
        //ie 2,3 becomes 23.
        return row * 10 + col;
    }

    public void print(){
        for(int i=0; i<9; i++) {
            if(i%3==0){
                System.out.println("-------------");
            }
            for (int j = 0; j < 9; j++) {
                if(j%3==0){
                    System.out.print("|");
                }
                if(boardSet.get((10 * i + j)).size() == 1){
                    System.out.print(boardSet.get((10 * i + j)).iterator().next().toString());
                } else {
                    System.out.print("_");
                }

            }
            System.out.print("|");
            System.out.println("");

        }
    }

    public void printSize(){
        for(int i=0; i<9; i++) {
            if(i%3==0){
                System.out.println("-------------");
            }
            for (int j = 0; j < 9; j++) {
                if(j%3==0){
                    System.out.print("|");
                }
                System.out.print(boardSet.get((10 * i + j)).size());

            }
            System.out.print("|");
            System.out.println("");

        }
    }
}
