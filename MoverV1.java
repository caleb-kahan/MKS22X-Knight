import java.util.ArrayList;

public class MoverV1{
  public int hor;
  public int ver;

  public MoverV1(int horizontal, int vertical){
    hor = horizontal;
    ver = vertical;
  }
  @Override
  public String toString(){
	return "H:"+ hor+ " V:"+ ver+ "\n";
  }
 
  public static ArrayList<MoverV1> pos (){
    ArrayList<MoverV1> pos = new ArrayList<MoverV1>();
    for(int i=0;i<2;i++){
      int hoz = (i%2==0) ? 2 : 1;
      int vert = (hoz==2) ? 1 : 2;
      for(int j=0;j<2;j++){
        hoz*= (j%2==0) ? 1 : -1;
        for(int k=0;k<2;k++){
          vert*= (k%2==0) ? 1 : -1;
          pos.add(new MoverV1 (hoz, vert));
        }
      }
    }
    return pos;
  }
}
