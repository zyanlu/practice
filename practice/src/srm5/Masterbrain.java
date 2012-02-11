package srm5;

import java.util.ArrayList;

public class Masterbrain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	

		new Masterbrain().possibleSecrets(new String[]{"2241", "5412", "5445", "2611", "7524", "5262"},
				
				new String[]{"0b 1w", "0b 1w", "1b 3w", "4b 0w", "2b 2w", "1b 0w"});
		
					
	}
	
	private String pegs(String guess,String sec){
		int w = 0;
		int b = 0;
		int[] bused = new int[4];
		int[] wused = new int[4];
		
		for(int i=0;i<4;i++){
			if(guess.charAt(i) == sec.charAt(i)){
				b++;
			//	System.out.println(guess.charAt(i));
				bused[i] = 1;
			}
		}
		
		for(int i=0;i<4;i++){
			if(bused[i] == 1){
				continue;
			}
				char cur = guess.charAt(i);
				for(int j=0;j<4;j++){
					if(cur == sec.charAt(j)){
						if(wused[j] != 1 && bused[j] != 1){
							w++;
						//	System.out.println(cur);
							wused[j] = 1;
							break;
						}
					}
				}
		}
		
		return String.format("%db %dw", b,w);
	}

	public int possibleSecrets(String[] guesses, String[] results){
		ArrayList[] trues = new ArrayList[guesses.length];
		ArrayList[] falses = new ArrayList[guesses.length];
		for (int i = 0; i < falses.length; i++) {
			trues[i] = new ArrayList();
			falses[i] = new ArrayList();
		}
		
		for(int a0=1;a0<8;a0++){
			for(int a1=1;a1<8;a1++){
				for(int a2=1;a2<8;a2++){
					for(int a3=1;a3<8;a3++){
						String s = String.format("%d%d%d%d", a0,a1,a2,a3);
						for(int i = 0 ;i<guesses.length;i++){
							if (this.pegs(guesses[i], s).endsWith(results[i])){
								trues[i].add(s);
							}else{
								falses[i].add(s);
							}
						}
						
					}
				}
			}
		}
		
		
		int len = 0;
		for (int i = 0; i < trues.length; i++) {
			ArrayList set = falses[i];
			for (int j = 0; j <trues.length; j++) {
				if(i == j){
					continue;
				}
				set.retainAll(trues[j]);
			}
			len += set.size();
		}
		
		System.out.println(len);
		return len;
	}
}
