package 多维动态规划;

public class Problem_0188_Best_Time_To_Buy_And_Sell_Stock_IV {
	// 空间压缩
	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if (k > n / 2) {
			return maxProfit2(prices);
		}
		int[] dp = new int[n];
		int ans = 0;
		for (int j = 1; j <= k; j++) {
			int t = dp[0] - prices[0];
			for (int i = 1; i < n; i++) {
				t = Math.max(t, dp[i] - prices[i]);
				dp[i] = Math.max(dp[i - 1], t + prices[i]);
				ans = Math.max(ans, dp[i]);
			}
		}
		return ans;
	}

	public int maxProfit1(int k, int[] prices) {
		int n = prices.length;
		if (k > n / 2) {
			return maxProfit2(prices);
		}
		int[][] dp = new int[n][k + 1];
		int ans = 0;
		for (int j = 1; j <= k; j++) {
			int t = dp[0][j - 1] - prices[0];
			for (int i = 1; i < n; i++) {
				t = Math.max(t, dp[i][j - 1] - prices[i]);
				dp[i][j] = Math.max(dp[i - 1][j], t + prices[i]);
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}

	public int maxProfit2(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
		}
		return ans;
	}
}
