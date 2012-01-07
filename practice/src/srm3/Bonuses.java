package srm3;

public class Bonuses {
	
	public int[] getDivision(int[] points){
		int[] res = new int[points.length];
		int sum = 0;
		for(int x : points){
			sum += x;
		}
		int bonuse = 0;
		for(int i=0;i<res.length;i++){
			res[i] = points[i]*100/sum;
			bonuse += res[i];
		}
		
		bonuse = 100 - bonuse;
		if(bonuse == 0){
			return res;
		}
		
		while(bonuse >0 ){
			int max = 0,max_idx = 0;
			for(int i=0;i<points.length;i++){
				if(points[i] > max){
					max = points[i];
					max_idx = i;
				}
			}
			
			bonuse --;
			res[max_idx] ++;
			points[max_idx] = 0;//drop him in the next round;
		}
		
		//for(int x :res)System.out.print(x+",");
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s = new int[]{7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 8};
		new Bonuses().getDivision(s);
	}

}
