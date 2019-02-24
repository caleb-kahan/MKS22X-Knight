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
              board[position.ver][position.hor]=cornerValue;
            }
            else if(position.hor==end && position.ver==end){
              velocity.hor=-1;
              velocity.ver=0;
              board[position.ver][position.hor]=cornerValue;
            }
            else if(position.hor==start && position.ver==end){
              velocity.hor=0;
              velocity.ver=-1;
              board[position.ver][position.hor]=cornerValue;
            }
            else
              board[position.ver][position.hor]=middleValue;
            position.hor+=velocity.hor;
            position.ver+=velocity.ver;
          }
	  board[position.ver][position.hor]=middleValue;
          if(start<end-1)
            recurCircular(start+1,end-1,round+1,board);
        }
        else
          board[start][end]=8;
      }
    public String toString(){
      String formatter1= "", formatter2= "";
      String [] values1 = new String[regBoard.length*regBoard[0].length], 
		values2 =new String[regBoard.length*regBoard[0].length];
      int i =0;
      for(int [] row: regBoard){
        for(int value: row){
          values1[i]=value+"";
          formatter1 += "%2s ";
          i++;
        }
        formatter1 +="%n";
      }
      int j =0;
      for(int [] row: comBoard){
        for(int value: row){
          values2[j]=value+"";
          formatter2 += "%2s ";
          j++;
        }
        formatter2 +="%n";
      }
      return String.format(formatter1,(Object[])values1)+"\n"+String.format(formatter2,(Object[])values2);
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
	comBoard[ver][hor]-=1;
        if(solveH(ver,hor,level+1,movers1))return true;
	
      }
      for(MoverV2 mover: movers2){
        int hor = mover.hor;
        int ver = mover.ver;
	comBoard[ver][hor]+=1;
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
        int finalHor = startingCol+hor;
        int finalVer = startingRow+ver;
        if(checker(finalVer,finalHor)){
          movers2.add(new MoverV2(finalVer,finalHor,comBoard[finalVer][finalHor]));
        }
      }
      Collections.sort(movers2);
      int sum =0;
      for(MoverV2 mover: movers2){
        int hor = mover.hor;
        int ver = mover.ver;
        sum+=countSolutionsH(ver,hor,level+1);
      }
      regBoard[startingRow][startingCol]=0;
      return sum;
    }
}
