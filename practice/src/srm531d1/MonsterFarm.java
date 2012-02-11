package srm531d1;

import java.util.Stack;

public class MonsterFarm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new MonsterFarm().numMonsters(new String[]{"2 2", "3", "4 4 4", "5", "6", "7 7 7 7", "7"});
		
		//new MonsterFarm().numMonsters(new String[]{"2 3","5 7","2 4","5","6","4","7"});
		//{{}}
	//	new MonsterFarm().numMonsters(new String[]{"40", "16", "20", "13", "19", "28", "23", "3", "29", "33", "22", "14", "18", "44", "9", "12", "48", "39", "46", "41", "5", "15", "32", "47", "50", "31", "36", "11", "2", "37", "21", "6", "7", "45", "10", "30", "24", "26", "25", "4", "49", "35", "1 1", "27", "38", "42", "17", "43", "34", "8"});
	//	new MonsterFarm().numMonsters(new String[]{"2 3", "1", "1"});
		
		new MonsterFarm().numMonsters(new String[]{"43 34 44", "34 19 41 13 44 49 38 13 34 49 13 11 23", "13 6 37 11 11", "39 2 11 11 49 14 17 43 45", "38 38 46", "49 8 39 43 2 13 37 14 44 18 31", "44 23 5 46 43 23 5 46", "38", "25", "36", "38 7 13 39 13 14 45 38 43", "9", "46 46 39 46 17 38 41 5 46 41", "17 44 44 26 7", "18 6 38 14 26 31 37 37 26 17 37 46 12 39 11", "32", "26 38 38", "43 44 45", "43 39 5 43 38 26 26 23 5 44 38 46 23", "3 7 13 29 44 5", "42", "29", "17", "28", "50", "41", "22", "10", "27", "47", "43 44 38 45 13 49 13 19 43 13 23 38 49 49", "30", "11 35 6 31 24", "45 39 23 7 23 14 49 49 44 19 13 45 7 43", "40", "24", "49 49 11 18 8", "46 46 46 46 46 46 46 46 46 46 46 46 46", "5", "16", "26", "21", "17 23", "38 46 46 13 26 46 17 38 17 17 26 17 38 39 46 17", "7 44 26 26", "46", "35", "3 17 4 19 17 24 37 17 46 49 8 14 3 8", "38 43 46 17 14 23 45", "12"});

	}
	
	int N;
	long ret = 0;
	boolean[] reach;
	int[][] trans;
	long mod = 1000000007;
	
	public int numMonsters(String[] transforms){
		N = transforms.length;
		trans = new int[N+1][N+1];
		reach = new boolean[N+1];
		for(int i=0;i<N;i++){
			for(String s : transforms[i].split(" ")){
				trans[i+1][Integer.parseInt(s)] += 1;
				trans[i+1][0] += 1; 
			}
		}
		
		Stack<long[]> s = new Stack<long[]>();
		s.push(new long[]{1,1});
		while(s.size() != 0){
			long[] cur = s.pop();
			int me = (int)cur[0];
			
			reach[me] = true;
			System.out.println(cur[0] + " " + cur[1]);
			for (int i = 1; i <=N ; i++) {
				int go = trans[me][i];
				if(go >0 && trans[i][0] >1 &&  reach[i]){
					//System.out.println("*"+(-1));
					return -1;
				}
				if(go >0 && reach[i] && cur[1] != 1 && i != me){
					//System.out.println(-1);
					return -1;
				}else if(go == 1 && reach[i]){
					ret =  (ret + cur[1]) % mod;
				}else if(go != 0 ){
					s.push(new long[]{i, cur[1]*trans[me][i] % mod });
				}
				
				
			}
			
		}
		
		//System.out.println(ret);
		return (int) ret ;
		
	}

}
