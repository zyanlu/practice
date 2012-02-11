package srm5;

/**
 * 
 * 
 * @author zyanlu
 *
 */
public class RectangularGrid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new RectangularGrid().countRectangles(592, 964));
	}
	
	public long countRectangles(int width, int height){
		long x = (width*(width+1)/2) 
				* (height*(height+1)/2);
		long a1 = (width*(width+1)/2);		
		long a2 = (height*(height+1)/2);
		
		System.out.println(x);
		System.out.println(a1);
		System.out.println(a2);

		System.out.println((int)a2 * (int)a1);
		long b = 0;
		for(;width>0 & height>0;width--,height--){
			b += width*height;
		}
		return a1*a2 - b ;
	}

}
