package srm8;

public class CCipher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//QRSTUVWXYZABCDEFGHIJKLMNOP
		new CCipher().decode("LIPPSASVPH", 4);
	}
	
	private static void p(Object o){
		//System.out.println(o);
	}
	
	public String decode(String cipherText, int shift){
		String res = "";
		int A = 'A';
		int Z = 'Z';
		for (int i = 0; i < cipherText.length(); i++) {
			char cur = cipherText.charAt(i);
			shift = shift % (Z-A+1);
			int cast = ((int)cur - shift);
			if(cast < A){
				cast = Z+1 -(A-cast);
			}
			res += (char)cast;
		}
		p(res);
		return res;
	}
}
