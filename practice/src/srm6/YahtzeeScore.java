package srm6;

public class YahtzeeScore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new YahtzeeScore().maxPoints(new int[]{
				
				 5, 3, 5, 3, 3 
		}));

	}
	
	public int maxPoints(int[] toss){
		int[] sums = new int[7];
		int max =0;
		for(int v : toss){
			sums[v] += v;
		}
		
		for(int m: sums){
			if(m> max){
				max = m;
			}
		}
		
		return max;
	}

}
