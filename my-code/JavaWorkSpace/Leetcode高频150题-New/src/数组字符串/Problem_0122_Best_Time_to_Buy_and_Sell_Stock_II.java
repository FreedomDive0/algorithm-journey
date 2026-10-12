package 数组字符串;

public class Problem_0122_Best_Time_to_Buy_and_Sell_Stock_II {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(0, prices[i] - prices[i - 1]);
		}
		return ans;
	}
}
