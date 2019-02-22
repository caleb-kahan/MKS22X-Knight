public class KnightBoard{
    private int [][] board;
    public KnightBoard(int startingRows, int startingCols){
      if(startingCols <=0 || startingRows<=0)throw IllegalArgumentException("A parameter is less than 1");
      board = new int [startingRows][startingCols];
    }
    public String toString(){
      String formatter = "";
      String [] values = new int[board.length*board[0].length];
      for(int [] row: board){
        for(int value: row){
          values+=value;
          formatter += "%2d ";
        }
        formatter +="%n";
      }
      return String.format(formatter,(Object[])values);
    }
    public boolean solve(int startingRow, int startingCol){
      if(! checker(startingRow,startingCol))
        throw IllegalArgumentException("Parameter Out of Bounds");
      for(int [] row: board){
        for(int value: row){
          if(value!=0)
            throw IllegalStateException("Non-0 Values on Board!!");
        }
      }
      return solveH(startingRow, startingCol, 1);
    }
    private boolean solveH(int row, int col, int level){
      board[row][col]=level;
      if(level==board.length*board[0].length) return true;
      ArrayList <MoverV1> movers = MoverV1.pos();
      for(MoverV1 mover: movers){
        int hor = mover.getHor();
        int ver = mover.getVer();
        if(checker(hor,ver)){
          if(solveH(hor,ver))return true;
        }
      }
      return false;
    }
    private static boolean checker(int row, int col){
        if(startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length)
          return false;
        return true;
    }
    public int countSolutions(int startingRow, int startingCol){
      if(! checker(startingRow,startingCol))
        throw IllegalArgumentException("Parameter Out of Bounds");
      for(int [] row: board){
        for(int value: row){
          if(value!=0)
            throw IllegalStateException("Non-0 Values on Board!!");
        }
      }
    }
    public int countSolutionsH(int startingRow, int StartingCol, int level){
      board[startingRow][startingCol]=level;
      if(level==board.length*board[0].length) return 1;
      ArrayList <MoverV1> movers = MoverV1.pos();
      for(MoverV1 mover: movers){
        int hor = mover.getHor();
        int ver = mover.getVer();
        if(checker(hor,ver)){
          if(solveH(hor,ver))return true;
        }
      }
    }




}
