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

}
