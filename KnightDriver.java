public class KnightDriver{
    public static void main(String [] args){
      KnightBoard board = new KnightBoard(63,63);
      //System.out.println(board.countSolutions(0,0));
      System.out.println(board.solve(0,0)); 
      //System.out.println(board); 
	
    }
}
