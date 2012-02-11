package srm7;

import java.util.ArrayList;

//此题逆推哦
//穷举不了的
public class PeopleCircle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PeopleCircle().order(5, 5, 3);
		new PeopleCircle().order(25, 25, 1000);
		//new PeopleCircle().order(1, 0, 245);
	}
	
	private static void p(ArrayList o){
		for(Object x : o){
			System.out.print(x);
		}
		System.out.println();
	}
	
	private static void p(Object o){
		System.out.println(o);
	}
	
	public String order(int numMales, int numFemales, int K){
		ArrayList list = new ArrayList();
		for (int i = 0; i < numMales; i++) {
			list.add('M');
		}
		
		int index = 0;
		int len = numMales;
		while(numFemales >0){
			//int index = K - 1;
			list.add(index, 'F');
			len ++;
			index -= (K-1);		
			index = ((index) % len +len) %len;
			numFemales--;
			
		}
		String res = "";
		for (int i = index; i < list.size(); i++) {
			res += list.get(i);
		}
		for (int i = 0; i < index; i++) {
			res += list.get(i);
		}
		//p(res);
		return res;
	}

}
