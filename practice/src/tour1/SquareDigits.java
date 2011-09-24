package tour1;


//用hashmap 多求一个hascode 慢很多
//
import java.util.HashMap;


public class SquareDigits {

	
	private Integer S(Integer x){
		String in = x.toString();
		int sq = 0;
		for(int i=0;i<in.length();i++){
			int di = Integer.parseInt(in.charAt(i)+"");
			sq +=  di*di; 
		}
		
		return sq;
	}
	
	private HashMap<Integer, Integer> T(Integer x,Integer n){
		HashMap<Integer, Integer> v = new HashMap<Integer, Integer>();
		
		Integer cur = x;
		while(!v.containsKey(n) && !v.containsKey(cur)){
			Integer sq = this.S(cur);
			v.put(sq, 1);
			cur = sq;
		}
		
		return v;
	}
	
	public int smallestResult(int n){
		
		int i=0;
		while(true){
			if(this.T(i,n).containsKey(n)){
				return i;
			}else{
				i++;
			}
		}
	}
	
	public static void main(String[] args){
		//System.out.println(new SquareDigits().S(0));
		//System.out.println(new SquareDigits().T(7).keySet());
		for(int i=0;i<200;i++){
			System.out.println(new SquareDigits().smallestResult(i));
		}
		
	}
}
