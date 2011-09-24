package srm1;
//这尼玛 message =1,0的时候 坑爹啊！

//这题考边界条件判断 要淡定啊!!
public class BinaryCode {

	public String[] decode(String message) {
		String p0 = "0";
		int plast = 0;
		int plastlast = 0;
		// System.out.print(0);
		for (int i = 0, l = message.length(); i < l; i++) {
			int qcur = Character.getNumericValue(message.charAt(i));
			int p_this = qcur - plast - plastlast;
			
			if (p_this == 0 || (i!=l-1 &&  p_this == 1)) {
				if (i != l - 1) {
					p0 += p_this;
				}
					plastlast = plast;
					plast = p_this;
				
			} else {
				p0 = "NONE";
				break;
			}
		}
		if(message.length() == 1 && 0 != Integer.parseInt(message)){
			p0 = "NONE";
		}

		//System.out.println(p0);

		String p1 = "1";
		int p_last = 1;
		int p_lastlast = 0;
		// 111
		// 1001
		// p0 = 1
		// p1 = q0 - p0 - 0 = 0
		// p2 = q1 - p0 - p1 = 0
		// p3 = q2 - p1 - p2 = 0
		// p4 = q3 - p2 - p1 = 0
		for (int i = 0, l = message.length(); i < l; i++) {
			int qcur = Character.getNumericValue(message.charAt(i));
			int p_this = qcur - p_last - p_lastlast;
			System.out.println(p_this);
	
			if (p_this == 0 || (i!=l-1 && p_this == 1)) {
				if (i != l - 1) {
					p1 += p_this;
				}
					p_lastlast = p_last;
					p_last = p_this;
				
			} else {
				p1 = "NONE";
				break;
			}
		}

		if(message.length() == 1 && 1 != Integer.parseInt(message)){
			p1 = "NONE";
		}
		//System.out.println(p1);

		
		return new String[] { p0, p1 };
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BinaryCode().decode("111");
	}

}
