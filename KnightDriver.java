public class KnightDriver{
    public static void main(String [] args){
      KnightBoard board = new KnightBoard(20,20);
      /*for(MoverV1 mover: MoverV1.pos()){
	System.out.println(mover);
      }*/
      //System.out.println(board.countSolutions(0,0));
      System.out.println(board.solve(0,0)); 
      System.out.println(board); 
	
    }
}
