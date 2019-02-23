import java.util.ArrayList;
import java.util.Collections;

public class KnightBoard{
    private int [][] regBoard;
    private int [][] comBoard;
    public KnightBoard(int startingRows, int startingCols){
      if(startingCols <=0 || startingRows<=0) throw new IllegalArgumentException("Parameter less than 1");
      regBoard = new int [startingRows][startingCols];
      comBoard = new int [startingRows][startingCols];
      maker(comBoard);
    }
    public void maker(int [][] comBoard){
      int start =0;
      int end = comBoard.length-1;
      recurCircular(start,end,1,comBoard);
    }
    public void recurCircular(int start, int end, int round, int[][] board){
      if(start!=end){
        int cornerValue;
        int middleValue;
        switch(round){
          case 1:
            cornerValue=2;
            middleValue=3;
            break;
          case 2:
            cornerValue=4;
            middleValue=6;
            break;
          default:
            cornerValue=8;
            middleValue=8;
          }
          MoverV1 velocity = new MoverV1(1,0);
          MoverV1 position = new MoverV1(start+1,start);
	  board[start][start]=cornerValue;
          while(position.hor!=start || position.ver!=start+1){
            if(position.hor==end && position.ver==start){
              velocity.hor=0;
              velocity.ver=1;
              board[position.hor][position.ver]=cornerValue;
            }
            else if(position.hor==end && position.ver==end){
              velocity.hor=-1;
              velocity.ver=0;
              board[position.hor][position.ver]=cornerValue;
            }
            else if(position.hor==start && position.ver==end){
              velocity.hor=0;
              velocity.ver=-1;
              board[position.hor][position.ver]=cornerValue;
            }
            else
              board[position.hor][position.ver]=middleValue;
            position.hor+=velocity.hor;
            position.ver+=velocity.ver;
          }
          if(start<end-1)
            recurCircular(start+1,end-1,round+1,board);
        }
        else
          board[start][end]=8;
      }
    public String toString(){
      String formatter = "";
      String [] values = new String[regBoard.length*regBoard[0].length];
      int i =0;
      for(int [] row: regBoard){
        for(int value: row){
          values[i]=value+"";
          formatter += "%2d ";
          i++;
        }
        formatter +="%n";
      }
      return String.format(formatter,(Object[])values);
    }
    public boolean solve(int startingRow, int startingCol){
      if(! checker(startingRow,startingCol))
        throw new IllegalArgumentException("Parameter Out of Bounds");
      for(int [] row: regBoard){
        for(int value: row){
          if(value!=0)
            throw new IllegalStateException("Non-0 Values on Board!!");
        }
      }
      return solveH(startingRow, startingCol, 1);
    }
    private boolean solveH(int row, int col, int level){
      regBoard[row][col]=level;
      if(level==regBoard.length*regBoard[0].length) return true;
      ArrayList <MoverV1> movers = MoverV1.pos();
      for(MoverV1 mover: movers){
        int hor = mover.hor;
        int ver = mover.ver;
        if(checker(row+ver,col+hor)){
          if(solveH(row+ver,col+hor,level+1))return true;
        }
      }
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
      return countSolutionsH(startingRow, startingCol, 1);
    }
    public int countSolutionsH(int startingRow, int startingCol, int level){
      regBoard[startingRow][startingCol]=level;
      if(level==regBoard.length*regBoard[0].length) return 1;
      ArrayList <MoverV1> movers1 = MoverV1.pos();
      ArrayList <MoverV2> movers2 = new ArrayList <MoverV2>();
      for(MoverV1 mover: movers1){
        int hor = mover.hor;
        int ver = mover.ver;
        int finalHor = startingRow+hor;
        int finalVer = startingCol+ver;
        if(checker(finalHor,finalVer)){
          movers2.add(new MoverV2(finalHor,finalVer,comBoard[finalHor][finalVer]));
        }
      }
      Collections.sort(movers2);
      int sum =0;
      for(MoverV2 mover: movers2){
        int hor = mover.hor;
        int ver = mover.ver;
        sum+=countSolutionsH(hor,ver,level+1);
      }
      return sum;
    }
}
