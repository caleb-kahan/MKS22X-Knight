public class KnightBoard{
    private int [][] board;
    private int [][] complicatedBoard;
    public KnightBoard(int startingRows, int startingCols){
      if(startingCols <=0 || startingRows<=0)throw IllegalArgumentException("A parameter is less than 1");
      board = new int [startingRows][startingCols];
    }
    public void maker(int [][] complex){
      int start =0;
      int end = board.length-1;
      MoverV1 velocity = new MoverV1(1,0);
      MoverV1 position = new MoverV1(0,0);
      while(position.hor()!=0 || position.ver()!=1){
        if(position.hor==end && position.ver==start){
          velocity.hor=0;
          velocity.ver=1;
        }
        else if(position.hor==end && position.ver==end){
          velocity.hor=-1;
          velocity.ver=0;
        }
        else if(position.hor==0 && position.ver==end){
          velocity.hor=0;
          velocity.ver=1;
        }

      }

    }
    public void recurCircular(int start, int end, int round){
      int cornerValue;
      int middleValue;
      
    }
    public String toString(){
      String formatter = "";
      String [] values = new int[board.length*board[0].length];
      int i =0;
      for(int [] row: board){
        for(int value: row){
          values[i]+=value;
          formatter += "%2d ";
          i++;
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
        if(checker(row+hor,col+ver)){
          if(solveH(row+hor,col+ver,level+1))return true;
        }
      }
      return false;
    }
    private boolean checker(int row, int col){
        if(startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length || board[row][col]!=0)
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
    public int countSolutionsH(int startingRow, int startingCol, int level){
      board[startingRow][startingCol]=level;
      if(level==board.length*board[0].length) return 1;
      ArrayList <MoverV1> movers1 = MoverV1.pos();
      ArrayList <MoverV2> movers2 = new ArrayList <MoverV2>();
      for(MoverV1 mover: movers){
        int hor = mover.getHor();
        int ver = mover.getVer();
        if(checker(startingRow+hor,startingCol+ver)){

        }
      }
      int sum =0;
      for(MoverV1 mover: movers){
        int hor = mover.getHor();
        int ver = mover.getVer();
        if(checker(startingRow+hor,startingCol+ver)){
          sum+=countSolutionsH(startingRow+hor,startingCol+ver,level+1);
        }
      }
      return sum;
    }
}
