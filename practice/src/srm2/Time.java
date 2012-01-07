package srm2;

public class Time {

	
	
	public String whatTime(int seconds){
		int h = seconds/3600;
		int m = (seconds % 3600)/60;
		int s = (seconds % 60);
		
		return String.format("%d:%d:%d",h,m,s);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Time().whatTime(86399));

	}

}
