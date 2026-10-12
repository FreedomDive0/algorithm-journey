package 多维动态规划;

public class Problem_0123_Best_Time_To_Buy_And_Sell_Stock_III {
	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int min = prices[0];
		int doneOnceMax = 0;
		int doneOnceMaxAndBuy = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMaxAndBuy = Math.max(doneOnceMaxAndBuy, doneOnceMax - prices[i]);
			ans = Math.max(ans, doneOnceMaxAndBuy + prices[i]);
		}
		return ans;
	}
}
