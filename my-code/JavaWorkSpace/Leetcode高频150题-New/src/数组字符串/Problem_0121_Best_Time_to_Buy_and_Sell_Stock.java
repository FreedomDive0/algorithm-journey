package 数组字符串;

public class Problem_0121_Best_Time_to_Buy_and_Sell_Stock {
	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int res = 0;
		int max = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			res = Math.max(res, max - prices[i]);
		}
		return res;
	}
}
