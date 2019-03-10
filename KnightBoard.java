import java.util.ArrayList;
import java.util.Collections;

public class KnightBoard{
    private int [][] regBoard;
    private int [][] comBoard;
    public KnightBoard(int startingRows, int startingCols){
      if(startingCols <=0 || startingRows<=0) throw new IllegalArgumentException("Parameter less than 1");
      regBoard = new int [startingRows][startingCols];
      comBoard = new int [startingRows][startingCols];
      manualMaker();
    }
    private int convert(int x){
	if(x>3) return 3;
	return x;
    }
    public void manualMaker(){
      for(int i=0;i<comBoard.length;i++){
        for(int j=0;j<comBoard[0].length;j++){
	  int pos =0;
	  int verDist1 = convert(i+1);
	  int verDist2 = convert(comBoard.length-i);
	  int verMax = Math.max(verDist1,verDist2);
	  int horDist1 =  convert(j+1);
	  int horDist2 =  convert(comBoard[0].length-j);
    int horMax = Math.max(horDist1,horDist2);
	  int sum = verDist1+verDist2 + horDist1+horDist2;

	  //If Dist is 3 on all sides, every move is possible.
    if(sum == 12) pos =8;
	  //Two moves become unpossible, so 8-2=6
	  if(sum == 11) pos =6;
	  //Now first Quandary, sum ==10. Two pos: 3+3+3+1: 4 or 2+2+3+3: 4.
	  if(sum==10) pos =4;
	  //Now second quandary, sum ==9. Three Possibilities.
	  //If sum is 2,2,2,3 only two outgoing moves.
	  //If sum is 3,3,2,1. 33+21:only two outgoing , 32+31: 3 outgoing.
	  if(sum == 9){
		    pos=2;
		    if(verMax==3&&horMax==3) pos = 3;
   }
	  //Now complicated, sum ==8.
    //One:2222 0 Possibilities
    //Two:3311 0 pos
    //Three: 3131 2 possible
    //Four: 2231 2 possible
    //Five: 2321 1 possible
	  if(sum ==8){
    if(verMax==2 && horMax==2 || verMax==1 || horMax==1) pos=0;
    if(verDist1+verDist2==5 || verDist1+verDist2==3) pos=1;
		else pos =2;
	  }
	  //sum ==7, 3211=0,1 or 2221=0.
	  if(sum == 7 && verMax>1 && horMax>1)
		  pos =1;
	  //Automatically, pos becomes 0.
          comBoard[i][j]=pos;
        }
      }
    }
    public String toString(){
      String formatter1= "", formatter2= "";
      String [] values1 = new String[regBoard.length*regBoard[0].length],
		values2 =new String[regBoard.length*regBoard[0].length];
      int i =0;
      for(int [] row: regBoard){
        for(int value: row){
          values1[i]=value+"";
          //????
          if(values1[i]=="0") values1[i]="_";
          formatter1 += "%3s ";
          i++;
        }
        formatter1 +="%n";
      }
      //For Debugging Purposes
      int j =0;
      for(int [] row: comBoard){
        for(int value: row){
          values2[j]=value+"";
          formatter2 += "%1s ";
          j++;
        }
        formatter2 +="%n";
      }
      return /*String.format(formatter1,(Object[])values1)+;*/"\n"+String.format(formatter2,(Object[])values2);
    }
    public boolean solve(int startingRow, int startingCol){
      ArrayList <MoverV1> movers1 = MoverV1.pos();
      if(! checker(startingRow,startingCol))
        throw new IllegalArgumentException("Parameter Out of Bounds");
      for(int [] row: regBoard){
        for(int value: row){
          if(value!=0)
            throw new IllegalStateException("Non-0 Values on Board!!");
        }
      }
      return solveH(startingRow, startingCol, 1, movers1);
    }
    private boolean solveH(int row, int col, int level, ArrayList<MoverV1> movers1){
      regBoard[row][col]=level;
      if(level==regBoard.length*regBoard[0].length) return true;
      ArrayList <MoverV2> movers2 = new ArrayList <MoverV2>();
      for(MoverV1 mover: movers1){
        int hor = mover.hor;
        int ver = mover.ver;
        int finalHor = col+hor;
        int finalVer = row+ver;
        if(checker(finalVer,finalHor)){
          movers2.add(new MoverV2(finalHor,finalVer,comBoard[finalVer][finalHor]--));
        }
      }
      Collections.sort(movers2);
      for(MoverV2 mover: movers2){
        int hor = mover.hor;
        int ver = mover.ver;
        if(solveH(ver,hor,level+1,movers1))return true;
	comBoard[ver][hor]++;
      }
      regBoard[row][col]=0;
      return false;
    }
    private boolean checker(int row, int col){
        if(row < 0 || col < 0 ||
           row >= regBoard.length ||
           col >= regBoard[0].length || regBoard[row][col]!=0)
          return false;
        return true;
    }
    public int countSolutions(int startingRow, int startingCol){
      if(! checker(startingRow,startingCol))
        throw new IllegalArgumentException("Parameter Out of Bounds");
      for(int [] row: regBoard){
        for(int value: row){
          if(value!=0)
            throw new IllegalStateException("Non-0 Values on Board!!");
        }
      }
      return countSolutionsH(startingRow, startingCol, 1, MoverV1.pos());
    }
    public int countSolutionsH(int startingRow, int startingCol, int level, ArrayList<MoverV1> movers1){
      regBoard[startingRow][startingCol]=level;
      if(level==regBoard.length*regBoard[0].length) {
	regBoard[startingRow][startingCol]=0;
	return 1;
      }
      int sum =0;
      for(MoverV1 mover: movers1){
        int hor = mover.hor;
        int ver = mover.ver;
        int finalHor = startingCol+hor;
        int finalVer = startingRow+ver;
        if(checker(finalVer,finalHor)){
          sum+=countSolutionsH(finalVer,finalHor,level+1,movers1);
        }
      }
      regBoard[startingRow][startingCol]=0;
      return sum;
    }
}
