package srm531d1;

//当前值 = 正上方值+ 左上方值
public class NoRepeatPlaylist {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(new NoRepeatPlaylist().numPlaylists(50, 5,100));
	}
	
	public int numPlaylists(int N, int M, int P){
		long[][] dp = new long[P+1][N+1];
		long mod = 1000000007;
		//dp[1][1] = 1;
		for (int i = 1; i <= P; i++) {
			for (int j = 1; j <= N; j++) {
				//if(j>M)
				//dp[i][j] = dp[i-1][j] * (j-M);
				//dp[i][j] = dp[i][j] + dp[i-1][j-1] * (N-j+1);
				
				if(j == 1 && i == 1){
					dp[i][j]=1;
				}else{
					dp[i][j] = (dp[i-1][j-1]*j + dp[i-1][j]*(j-M > 0 ? j-M : 0) ) % mod;
				}
			}
		}
		
		
		return (int)dp[P][N];
	}

}
