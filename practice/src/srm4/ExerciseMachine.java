package srm4;

public class ExerciseMachine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new ExerciseMachine().getPercentages("00:28:00"));
	}
	
	public int getPercentages(String time){
		String[] t = time.split(":");
		int secs = Integer.parseInt(t[0]) * 3600
				+ Integer.parseInt(t[1]) * 60
				+ Integer.parseInt(t[2]);
		
		
		int cnt = 0 ;
		
		for (int i = 1; i < secs; i++) {
			if( (i*100 )% secs == 0){
				cnt ++;
			}
		}
		
		return cnt;
	}

}
