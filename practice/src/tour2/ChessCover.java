package tour2;


//print 严重影响速度……

//这题很水……
public class ChessCover {

	private char[][] grid;

	private int w;
	private int h;

	public ChessCover() {
	}

	public int getSafe(String[] param0) {
		h = param0.length;
		grid = new char[param0.length][];
		for (int i = 0; i < param0.length; i++) {
			grid[i] = param0[i].toCharArray();
			w = grid[i].length;
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				char unit = grid[i][j];
				if (unit == 'Q' || unit == 'P' || unit == 'R' || unit == 'B') {
					markX(i, j,unit);
				}else if(unit == 'K'){
					markK(i,j);
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(grid[i][j] == 'U'){
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private void markK(int i,int j){
		markKK(i-2,j-1);
		markKK(i-2,j+1);
		markKK(i+2,j-1);
		markKK(i+2,j+1);
		markKK(i-1,j-2);
		markKK(i-1,j+2);
		markKK(i+1,j-2);
		markKK(i+1,j+2);
	}
	private void markKK(int i,int j){
		if(i>=0 && i<h && j>=0 && j<w && !blocked(grid[i][j])){
			grid[i][j] = 'X';
		}
	}

	private void markX(int i, int j, char unit) {
		if (unit == 'Q' || unit == 'B' || unit =='P') {
			for (int m = i, n = j; ++m < h && ++n < w && !blocked(grid[m][n]);) {// right below
				grid[m][n] = 'X';
				if(unit =='P'){//1 step
					break;
				}
			}
		}

		if (unit == 'Q' || unit == 'R') {
			for (int m = i, n = j; ++n < w && !blocked(grid[m][n]);) {// right
				grid[m][n] = 'X';
			}
		}

		if (unit == 'Q' || unit == 'B' || unit =='P') {
			for (int m = i, n = j; --m >= 0 && ++n < w && !blocked(grid[m][n]);) {// right  up
				grid[m][n] = 'X';
				if(unit =='P'){//1 step
					break;
				}
			}
		}
		if (unit == 'Q' || unit == 'R') {
			for (int m = i, n = j; --m >= 0 && !blocked(grid[m][n]);) {// up
				grid[m][n] = 'X';
			}
		}

		if (unit == 'Q' || unit == 'B' || unit =='P') {
			for (int m = i, n = j; --m >= 0 && --n >= 0 && !blocked(grid[m][n]);) {// up left
				grid[m][n] = 'X';
				if(unit =='P'){//1 step
					break;
				}
			}
		}
		if (unit == 'Q' || unit == 'R') {
			for (int m = i, n = j; --n >= 0 && !blocked(grid[m][n]);) {// left
				grid[m][n] = 'X';
			}
		}
		if (unit == 'Q' || unit == 'B' || unit =='P') {
			for (int m = i, n = j; ++m < h && --n >= 0 && !blocked(grid[m][n]);) {// down left
				grid[m][n] = 'X';
				if(unit =='P'){//1 step
					break;
				}
			}
		}
		
		if (unit == 'Q' || unit == 'R') {
			for (int m = i, n = j; ++m < h && !blocked(grid[m][n]);) {// down
				grid[m][n] = 'X';
			}
		}

	}

	private boolean blocked(char a) {
		if (a != 'U' && a != 'X') {
			return true;
		} else {
			return false;
		}
	}

	private void print() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		String[] param0 = new String[] { "UUUU", "UUUU", "QUUU", "UUUU" };
		ChessCover c = new ChessCover();
		System.out.println(c.getSafe(param0));
		c.print();
		
		String[] param1 = new String[] { "UBUKUUUBUU","UUUUBUUQUR"};
		
		ChessCover c2 = new ChessCover();
		System.out.println(c2.getSafe(param1));
		
		c2.print();
		String[] p2 = new String[]{"UUUUUQ"
		,"UUUUUU"
				,"BURUUU"
				,"UUKUUU"
				,"UUUUUU"};
		
		ChessCover c3 = new ChessCover();
		System.out.println(c3.getSafe(p2));
		c3.print();
	
		// c.doQ(0, 0);
	}
}
