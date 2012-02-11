package srm7;

import java.math.BigInteger;

public class Dragons {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new Dragons().snaug(new int[]{1, 2, 3, 4, 5, 6}, 45);
		//new Dragons().snaug(new int[]{1000, 1000, 1000, 1000, 1000, 1000}, 45);
		
	}
	
	private static void p(Div[] o){
		for(Div x : o){
			p(x);
			System.out.print(",");
		}
		System.out.println();
	}
	
	private static void p(Div o){
		System.out.print(o.a + "/" + o.b);
	}
	
	final class Div{
		public BigInteger a;
		public BigInteger b;
		public Div(BigInteger aa,BigInteger bb){
			a = aa;
			b = bb;
		}
		public Div(long aa, long bb){
			a = BigInteger.valueOf(aa);
			b = BigInteger.valueOf(bb);
		}
		public void sum(Div other){
			if(b == other.b){
				a = a.add(other.a);
			}else{
				a = a.multiply(other.b).add(b.multiply(other.a));
				//a = a*other.b + b*other.a;
				b = b.multiply(other.b);
				//b = b*other.b;
			}
			
			BigInteger red = reduce(a,b);
			a = a.divide(red);
			b = b.divide(red);
		}
		private BigInteger reduce(BigInteger a,BigInteger b){
			return a.remainder(b).compareTo(BigInteger.valueOf(0)) == 0 ? b : reduce(b,a.remainder(b));
			//return a % b == 0? b : reduce(b,a % b);
		}
		
				
	}
	
	
	public Div[] round(Div[] in){
		Div[] out = new Div[in.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = new Div(0,1);
		}
		int[] except = new int[]{
				1,//front
				0,//back
				3,//up
				2,//down
				5,//left
				4,//right
		};
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in.length; j++) {
				if(i != j && j != except[i]){
					out[i].sum(new Div(in[j].a,in[j].b.multiply(BigInteger.valueOf(4))));
				}
			}
		}
		//p(out);
		return out;
	}
	
	private long reduce(long a,long b){
		return a % b == 0? b : reduce(b,a % b);
	}
	

	public String snaug(int[] initialFood, int rounds){
		
		//front, back, up, down, left, right.
		int r = rounds;
		Div[] cast = new Div[initialFood.length];
		for (int i = 0; i < cast.length; i++) {
			cast[i] = new Div(initialFood[i],1);
		}
		while(r > 0){
			cast = round(cast);
			r --;
		}
		
		Div up = cast[2];
		if(up.a.remainder(up.b).compareTo(BigInteger.valueOf(0)) == 0){
			return up.a.divide(up.b).toString();
		}else{
			return up.a +"/" + up.b;
		}
	}

}
