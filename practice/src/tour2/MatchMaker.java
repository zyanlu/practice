package tour2;

public class MatchMaker {

	
	public String[] getBestMatches(String[] param0, String param1, int param2){
		
		String[][]  members =  new String[param0.length][4];
		String[] me = null;
		String[] scores = new String[11];
		
		for(int i=0;i<param0.length;i++){
			String[] s = param0[i].split(" ");
			String match = "";
			for(int j=3;j<s.length;j++){
				match += s[j];
			}
			members[i] = new String[]{s[0],s[1],s[2],match};
			
			if(param1.equals(s[0])){
				me = members[i];
			}
		}
		
		
		for(int i=0;i<members.length;i++){
			if(members[i]!= me
			   && me[2].equals(members[i][1])
			){
				int score = this.getScore(me[3], members[i][3]);
				if(score >= param2){
					if(scores[score] == null){
						scores[score] = members[i][0];
					}else{
						scores[score] += ("#" + members[i][0]);
					}
				}
			}
		}
		
		String res = null;
		for(int i=scores.length -1 ;i>=param2;i--){
			if(scores[i] != null){
				if(res == null){
					res = scores[i];
				}else{
					res +=  ("#" + scores[i]);
				}
			}
		}
		
		System.out.println(res);
		if(res == null){
			return new String[0];
		}else{
			return 	res.split("#");
		}
	}
	
	private int getScore(String a,String b){
		int cnt = 0;
		byte[] ba =  a.getBytes();
		byte[] bb = b.getBytes();
		
		for(int i=0;i<ba.length;i++){
			if(ba[i] == bb[i]){
				cnt ++;
			}
		}
		return cnt;
	}

	
	public static void main(String[] args){
		String[] s = {"BETTY F M A A C C",
				 "TOM M F A D C A",
				 "SUE F M D D D D",
				 "ELLEN F M A A C A",
				 "JOE M F A A C A",
				 "ED M F A D D A",
				 "SALLY F M C D A B",
				 "MARGE F M A A C C"};
		String[] s2 = {"BETTY F M A A C C", "TOM M F A D C A", "SUE F M D D D D", "ELLEN F M A A C A", "JOE M F A A C A", "ED M F A D D A", "SALLY F M C D A B", "MARGE F M A A C C"};

		System.out.println(new MatchMaker().getBestMatches(s2, "MARGE", 4));
		
	}
}
