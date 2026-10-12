package class02;

public class Code01_BestTimeToBuyAndSellStock1 {
	// 每日只能买卖一次
	public static int BTTBASS(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, prices[i] - min);
		}
		return ans;
	}
}
