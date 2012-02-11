package srm785;

public class GogoXCake {

	public static void main(String[] args) {
		new GogoXCake().solve(new String[]{
				 "X.X"
				,"..."
				,"..."
				,"X.X"}, new String[]{
				 ".X"
				,".."
			    ,"X."});
		new GogoXCake().solve(
new String[]{"..XX"
,"...X"
,"X..."
,"XX.."}, new String[]{".."
	,".."});
		
		new GogoXCake().solve(new String[]
{"XXXXXXX"
,"X.....X"
,"X.....X"
,"X.....X"
,"XXXXXXX"}, new String[]
{".X."
,"XXX"
,".X."});
	}
	
	private void p(char[][] a){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}

		System.out.println("**********");
	}
	
	private void p(Object o){
		System.out.println(o);
	}
	

	public String solve(String[] cake, String[] cutter){
		char[][] ca  = new char[cake.length][];
		int ca_w=0,ca_h=0;
		ca_h = ca.length;
		for (int i = 0; i < ca.length; i++) {
			ca[i] = cake[i].toCharArray();
			ca_w = ca[i].length;
		}
		char[][] cu = new char[cutter.length][];
		int cu_w=0,cu_h=0;
		cu_h = cu.length;
		for (int i = 0; i < cu.length; i++) {
			cu[i] = cutter[i].toCharArray();
			cu_w = cu[i].length;
		}
		for (int x = 0; x <= ca_w - cu_w; x++) {
			for(int y=0; y<= ca_h - cu_h; y++){
				
				boolean docut = true;
				for(int i=0;i<cu_w;i++){
					for(int j=0;j<cu_h;j++){
						if(ca[y+j][x+i] == 'X' &&
						   cu[j][i] == '.'){
							docut = false;
						}
						
					}
				}
				
				if(docut == true){
					for(int i=0;i<cu_w;i++){
						for(int j=0;j<cu_h;j++){
							if(ca[y+j][x+i] == '.' &&
							   cu[j][i] == '.'){
								ca[y+j][x+i] = 'X';
							}
							
						}
					}
//					p(ca);
				}
				
			}
		}
		
		String ret = "YES";
		for (int i = 0; i < ca_w; i++) {
			for (int j = 0; j < ca_h; j++) {
				if(ca[j][i] == '.'){
					ret = "NO";
				}
			}
		}
		
		
	//	p(ret);
		return ret;
	}
}
