import java.util.ArrayList;

public class MoverV1{
  private int horizontal;
  private int vertical;

  public MoverV1(int horizontal, int vertical){
    this.horizontal = horizontal;
    this.vertical = vertical;
  }
  public int getHor(){
    return horizontal;
  }
  public int getVer(){
    return vertical;
  }
  public static ArrayList<MoverV1> pos (){
    for(int i=0;i<2;i++){
      int hoz = (k%2==0) ? 2 : 1;
      int vert = (hoz==2) ? 1 : 2;
      for(int j=0;j<2;j++){
        hoz*= (j%2==0) ? 1 : -1;
        for(int k=0;k<2;k++){
          vert*= (j%2==0) ? 1 : -1;
          pos.add(new MoverV1 (hoz, vert));
        }
      }
    }
  }
}
