package tour2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tothello {


	private char[][] grid;
	
	
	
	final int A = 65;
	
	//final int SIZE = 8;
	
	public Tothello(){
		this.grid = new char[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				this.grid[i][j] = '-';
			}
		}
		
	}
	
	public int bestMove(String[] redPieces, String[] blackPieces, String whoseTurn){
		
		int best = 0;
		char me = whoseTurn.endsWith("Red") ? 'R' : 'B';
		
		int ori =  me =='R'? redPieces.length: blackPieces.length;
		for(String red : redPieces){
			this.fill(red,'R');
			
		}
		for(String black : blackPieces){
			this.fill(black,'B');
		}
		
		
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(grid[i][j] == '-'){//try every set
					
					int c = score(i,j,me);
					if(c>best){
						best = c;
					}
					this.reset(redPieces,blackPieces);
				}
			}
		}
		
		
		//move("C1",'B');
		
		System.out.println("final score:"+ ori + "+" + best +" + 1 =" +(ori+best+1));
		
		return (ori+best+1);
	}
	
	private void reset(String[] redPieces, String[] blackPieces){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				this.grid[i][j] = '-';
			}
		}
		for(String red : redPieces){
			this.fill(red,'R');
		}
		for(String black : blackPieces){
			this.fill(black,'B');
		}
	}
	
	private int score(int y,int x,char color){
		int score = 0;
		String buff = move(y,x,color);
		int start = 0;
		int end = buff.length();
		this.print();
		while(start < end){
			String pos = buff.substring(start, start+2);
			String b = move(pos,color);
			
			for(int i=0,l=b.length();i<l;i+=3){
				String p = b.substring(i,i+3);
				if(buff.indexOf(p) == -1){
					buff += p;
					end +=3;
				}
			}
			
			System.out.println("buff:" + buff);
			//buff += b;
			//end += b.length();
			start += 3;
			this.print();
		}
		
		score = buff.length()/3;
		System.out.println("score:"+ score);
		return score;
	}
	
	private String move(String pos,char color){
		int x = pos.codePointAt(0) - A;
		int y = Integer.parseInt(pos.charAt(1)+"") -1 ;
		return move(y,x,color);
	}
	
	private String move(int y,int x,char color){
		String changes = "";
		System.out.println("move:"+ name(x,y)+color );
		grid[y][x] = color;
		
		String[] tmp = new String[]{"","","","","","","",""};
		for(int i=0;i<8;i++){
			if(i>=x){
				tmp[0] += name(i,y)+grid[y][i];
			}
			if(i<=x){
				tmp[1] += name(i,y)+grid[y][i];
			}
			if(i<=y){
				tmp[2] += name(x,i)+grid[i][x];
			}
			if(i>=y){
				tmp[3] += name(x,i)+grid[i][x];
			}
			
		}
		for(int i=y,j=x;i<8&&j<8;i++,j++){
			tmp[4] +=  name(j,i)+grid[i][j];
		}
		for(int i=y,j=x;i>=0&&j>=0;i--,j--){
			tmp[5] +=  name(j,i)+grid[i][j];
		}
		for(int i=y,j=x;i>=0&&j<8;i--,j++){
			tmp[6] +=  name(j,i)+grid[i][j];
		}
		for(int i=y,j=x;i<8&&j>=0;i++,j--){
			tmp[7] += name(j,i)+ grid[i][j];
		}
		
		System.out.println("right:" + tmp[0]);
		System.out.println("left:" + tmp[1]);
		System.out.println("up:" + tmp[2]);
		System.out.println("down:" + tmp[3]);
		System.out.println("downright:" + tmp[4]);
		System.out.println("upleft:" + tmp[5]);
		System.out.println("upright:" + tmp[6]);
		System.out.println("downleft:" + tmp[7]);
		
		
		for(String c : tmp){
			changes += change(c,color);
		}
		System.out.println("change:" + changes);
		
		return changes;
		
	}
	
	//C1BC2RC3B..
	private String change(String pos,char color){
		String res = "";
		char token  = color  == 'R' ?  'B':'R';
		String regex = ".*[A-H]\\d"+color+"((:?[A-H]\\d"+token+")+)"+"[A-H]\\d"+color+".*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(pos);
		if(m.matches()){
			res = m.group(1);
		}
		return res;
	}
	
	private String name(int x,int y){
		char c = (char)(x+A);
		return c + "" + (y+1);
	}
	
	private void fill(String pos,char color){
		int x = pos.codePointAt(0) - A;
		int y = Integer.parseInt(pos.charAt(1)+"") -1 ;
		this.grid[y][x] = color;
	}
	
	private void print(){
		String h = "ABCDEFGH";
		System.out.print("  ");
		for(int i=0;i<8;i++){
			System.out.print(h.charAt(i) + " ");
		}
		System.out.println();
		for(int i=0;i<8;i++){
			System.out.print((i+1)+" ");
			for(int j=0;j<8;j++){
				System.out.print(this.grid[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args){
		//new Tothello().print();
		
		String[] redPieces = new String[]{"C2","C3","C4","C5","D4","E4","F2","F3","F4","F5","G6"};
		String[] blackPieces = new String[]{"B1","E1","G1","C6","H7","G4"};
		String whoseTurn = "Black"; 
		Tothello t = new Tothello();
		//t.bestMove(redPieces, blackPieces, whoseTurn);
		
		String[] r = new String[]{"A1","B8","C6","C8","D8"};
		String[] b = new String[]{"B2","C2","C3","C4","C5"};
		Tothello t2 = new Tothello();
		t2.bestMove(r, b, "Red");
			
		//System.out.println(t.name(0, 2));
		//System.out.println(t.change("C1BC2RC4RC3B",'B'));
		
	}
	
	
}
