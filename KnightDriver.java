public class KnightDriver{
    public static void main(String [] args){
      KnightBoard board = new KnightBoard(8,8);
      for(MoverV1 mover: MoverV1.pos()){
	System.out.println(mover);
      }
      //System.out.println(board.solve(0,0));  
    }
}
