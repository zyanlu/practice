package srm1;

import java.util.LinkedList;
import java.util.List;

//haha nb le
public class Lottery {

	/**
	 * 
	 * @param args
	 */
	
	private static long pow(int n,int m){
		return (long)Math.pow(n, m);
	}
	
	private static long limited(int n,int m){
		return inorder3(n,m,new long[n+1][m+1]);
	}
	
	//C 10 2
	private static long differ(int n,int m){
		long prod = 1;
		for(long i=0;i<m;i++){
			prod *= n-i;
		}
		return prod;
	}
	
	private  static long inorder(int n,int m,long[][] cache){
		if(m == 1){
			return n;
		}
		if(cache[n][m] != 0){
			return cache[n][m] ;
		}
		long sum = 0;
		for(int i=1;i<=n;i++){
			sum += inorder(i,m-1,cache);
		}
		return sum;
	}
	
	private static long inorder2(int n,int m,long[][] cache){
		if(m == 1){
			return n;
		}
		if(n == 1){
			return 1;
		}
		if(cache[n][m] != 0){
			return cache[n][m] ;
		}
		long sum = inorder2(n-1,m,cache) + inorder2(n,m-1,cache);
		cache[n][m] = sum;
		return sum;
	}
	
	private static long inorder3(int n,int m,long[][] cache){
		if(m == 1){
			return n;
		}
		if(n == 1){
			return 0;
		}
		if(cache[n][m] != 0){
			return cache[n][m] ;
		}
		long sum = inorder3(n-1,m,cache) + inorder3(n-1,m-1,cache);
		cache[n][m] = sum;
		return sum;
	}
	
	
	public String[] sortByOdds(String[] rules){
		List<long[]>  sortedlist= new LinkedList<long[]>();
		for(int i=0;i<rules.length;i++){
			String[] r = rules[i].split(":");
			String[] data = r[1].trim().split(" ");
			int n = Integer.parseInt(data[0]);
			int m = Integer.parseInt(data[1]);
			boolean issorted = data[2].equals("T");
			boolean isuniq = data[3].equals("T");
			
			
			long res = 0;
			if(!issorted && !isuniq){
				res = pow(n, m);
			}else if(issorted && !isuniq){
				res = inorder2(n, m, new long[n+1][m+1]);
			}else if(!issorted && isuniq){
				res = differ(n, m);
			}else{
				res = limited(n, m);
			}
			
			
			long[] item = new long[]{i,res};
		
			if(sortedlist.size() == 0){
				sortedlist.add(item);
			}else{
				for(int j=0;j<=sortedlist.size();j++){
					if( j == sortedlist.size()
					|| item[1] < sortedlist.get(j)[1]
					|| ( item[1] == sortedlist.get(j)[1] && rules[(int)item[0]].compareTo(rules[(int)sortedlist.get(j)[0]]) < 0 )
					){
						sortedlist.add(j, item);
						break;
					}
				}
			}
		}
		
		String[] names = new String[rules.length];
		for(int i=0;i<names.length;i++){
			names[i] = rules[(int)sortedlist.get(i)[0]].split(":")[0];
			System.out.println(names[i]+" "+ sortedlist.get(i)[1]);
		}
		
		return names;
	}
	
	
	public static void main(String[] args) {
		long[][] c = new long[11][3];
//		System.out.prlongln(inorder(10,2));
//		System.out.prlongln(inorder2(10,2,c));
//		System.out.prlongln(differ(10,3));
//		System.out.prlongln(pow(10,3));
//		System.out.prlongln(limited(10,2));
//		
		String[] rules2 = new String[]{"PICK ANY TWO: 10 2 F F"
				,"PICK TWO IN ORDER: 10 2 T F"
				,"PICK TWO DIFFERENT: 10 2 F T"
				,"PICK TWO LIMITED: 10 2 T T"};
		String[] rules = new String[]{"INDIGO: 93 8 T F",
				 "ORANGE: 29 8 F T",
				 "VIOLET: 76 6 F F",
				 "BLUE: 100 8 T T",
				 "RED: 99 8 T T",
				 "GREEN: 78 6 F T",
				 "YELLOW: 75 6 F F"};
		new Lottery().sortByOdds(rules);
		
	}

}
