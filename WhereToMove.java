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
  public ArrayList<MoverV1> pos (){
    for(int i=0;i++;i<2){
      int hoz = (k%2==0) ? 2 : 1;
      int vert = (hoz==2) ? 1 : 2;
      for(int j=0;j++;j<2){
        hoz*= (j%2==0) ? 1 : -1;
        for(int k=0;k++;k<2){
          vert*= (j%2==0) ? 1 : -1;
          pos.add(new MoverV1 (hoz, vert));
        }
      }
    }
  }
}
