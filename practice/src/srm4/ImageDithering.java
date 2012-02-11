package srm4;

public class ImageDithering {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String d = "BW";
		String[] sc = {"AAAAAAAA",
				 "ABWBWBWA",
				 "AWBWBWBA",
				 "ABWBWBWA",
				 "AWBWBWBA",
				 "AAAAAAAA"};
		System.out.println(new ImageDithering().count(d, sc));
	}

	public int count(String dithered, String[] screen) {
		
		int c = 0;
		
		for (String s : screen) {
			int len = s.length();
			for (int i = 0; i < len; i++) {
				if(dithered.indexOf(s.charAt(i)) != -1){
					c++;
				}
			}
		}
		
		return c;
	}

}
