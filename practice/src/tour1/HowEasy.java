package tour1;

//SB了 正则没转义
//把 . 也算了word 没减1
public class HowEasy {

	
	public int pointVal(String problemStatement){
		String[] a = problemStatement.split(" ");
		int len = 0;
		int cnt = 0;
		for(int i=0;i<a.length;i++){
			if(a[i].matches("[a-zA-Z]+\\.?")){
				len += a[i].endsWith(".") ? a[i].length() -1 : a[i].length();
				cnt++;
			}
		}
		
		
		
		if(cnt == 0){
			return 250;
		}
		
		int av = len/cnt;
		
		if(av <= 3){
			return 250;
		}else if(av <= 5){
			return 500;
		}else{
			return 1000;
		}
	
	}
	
	
	public static void main(String[] args){
		String s = "Implement a class H5 which contains some method.";
		System.out.println(new HowEasy().pointVal(s));
	}
}
