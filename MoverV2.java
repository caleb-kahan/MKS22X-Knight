public class MoverV2 extends MoverV1 implements Comparable<MoverV2>{
  private int pos;

  public MoverV2(int hor, int ver, int pos){
    super(hor,ver);
    this.pos=pos;
  }
  public int getPos(){
    return pos;
  }
  @Override
  public int compareTo(MoverV2 mov){
    return Integer.valueOf(getPos()).compareTo(mov.getPos());
  }
}
