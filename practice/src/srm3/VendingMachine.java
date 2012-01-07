package srm3;

public class VendingMachine {

	private int getMaxCol(int[][] pri) {
		int max = 0, value = 0;
		int[] colValues = new int[pri[0].length];
		for (int i = 0; i < colValues.length; i++) {
			for (int j = 0; j < pri.length; j++) {
				colValues[i] += pri[j][i];
			}
		}
		
		for (int i = 0; i < colValues.length; i++) {
			if(colValues[i] > value){
				value = colValues[i];
				max = i;
			}
		}
		return max;
	}

	private int[] toInt(String[] srow) {
		int[] row = new int[srow.length];
		for (int j = 0; j < srow.length; j++) {
			row[j] = Integer.parseInt(srow[j]);
		}
		return row;
	}

	private void print(int[] i) {
		for (int o : i) {
			System.out.print(o + ",");
		}
		System.out.println();
	}

	private int[] perform(String purchase, String last, int[][] pri, int col) {
		int[] ipur = this.toInt(purchase.split(",|:"));

		int[] res = new int[2];
		int total = pri[0].length;
		
		if(pri[ipur[0]][ipur[1]] == 0){
			return null;
		}
		
		if(last != null){
			int[] ilast = this.toInt(last.split(",|:"));
			if ( ipur[2] - ilast[2] >= 5) {
				 int toCol = this.getMaxCol(pri);
				 
				 res[0] += this.getSec(col, toCol, total);
				 col = toCol;
				// System.out.println(res[0]+ "-" + col);
			}
		}

		res[0] += this.getSec(col, ipur[1], total);
		res[1] = ipur[1];
		pri[ipur[0]][ipur[1]] = 0;
		
		
		return res;
	}

	private int getSec(int start,int end ,int total){
		int d1 =0;
		if(start > end){
			d1 = total - start + end;
		}else{
			d1 = total - end + start;
		}
		
		int d2 = Math.abs(end -start);
		return d1>d2 ? d2 :d1;
		
	}
	//  6 - 3
	//
	public int motorUse(String[] prices, String[] purchases) {
		int total = 0;
		int[][] pri = new int[prices.length][];
		for (int i = 0; i < prices.length; i++) {
			String[] srow = prices[i].split(" ");
			total = srow.length;
			pri[i] = this.toInt(srow);
		}

		int step = 0;
		int col = 0;
		String last = null;
		//before
		int maxCol = this.getMaxCol(pri);
		step += this.getSec(col, maxCol, total);
		col = maxCol;
		System.out.println( maxCol);
		//perform
		for (int i = 0; i < purchases.length; i++) {
			int[] ret = this.perform(purchases[i], last, pri, col);
			last = purchases[i];
			for (int j = 0; j < pri.length; j++) {
				print(pri[j]);
				//System.out.println("###");
			}
			if (ret != null) {
				step += ret[0];
				col = ret[1];
				
				System.out.println(ret[0] + "-" + ret[1]);
			} else {
				return -1;
			}
		}
		
		
		//after
		//if(sum == 0){
			int maxCol2 = this.getMaxCol(pri);
			
			step += this.getSec(col, maxCol2, total);
			System.out.println(maxCol2+ "  "+ this.getSec(col, maxCol2, total));
			
		//}
		
		return step;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[] prices = new String[] { "100 100 100" };
//		String[] purchases = new String[] { "0,0:0", "0,2:5", "0,1:10" };
////		
//		String[] prices = new String[]{"100 200 300 400 500 600"};
//		String[] purchases = new String[]  {"0,2:0", "0,3:5", "0,1:10", "0,4:15"};
		


		String[] prices = new String[] 
				{"100 200 300",
				 "600 500 400"};
		String[] purchases = new String[]
				{"0,0:0", "1,1:10", "1,2:20",
				 "0,1:21", "1,0:22", "0,2:35"};
		System.out.println(new VendingMachine().motorUse(prices, purchases));
		
		//System.out.println(new VendingMachine().getSec(5, 5, 6));
	}
}
