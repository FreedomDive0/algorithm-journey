package class02;

public class Code02_BestTimeToBuyAndSellStock2 {
	// 每日买卖次数不限
	public static int maxProfit2(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += prices[i] - prices[i - 1] > 0 ? prices[i] - prices[i - 1] : 0;
		}
		return ans;
	}

}
