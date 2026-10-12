package class02;

public class Code03_BestTimeToBuyAndSellStock3 {
	public static int maxProfit3(int[] prices, int k) {
		if (prices == null || prices.length < 2 || k < 1) {
			return 0;
		}
		int N = prices.length;
		if (k >= N / 2) {// 当k超过n/2 相当于任意次数购买
			return maxProfit2(prices);
		}
		int[][] dp = new int[N][k + 1];
		for (int j = 1; j <= k; j++) {
			// dp[1][1]
			// 1) dp[0][1]
			// 2) dp[1][0] + [1] - [1]
			// dp[0][0] + [1] - [0]
			int tmp = dp[j - 1][0] - prices[0];
			for (int i = 1; i < dp.length; i++) {
				tmp = Math.max(tmp, dp[i][j - 1] - prices[i]);
				dp[i][j] = Math.max(dp[i - 1][j], tmp + prices[i]);
			}
		}
		return dp[N - 1][k];
	}

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

	// 空间压缩
	public static int dp(int[] prices, int k) {
		if (prices == null || prices.length < 2 || k < 1) {
			return 0;
		}
		int N = prices.length;
		if (k >= N / 2) {// 当k超过n/2 相当于任意次数购买
			return maxProfit2(prices);
		}
		int[] dp = new int[N];
		for (int j = 1; j <= k; j++) {
			int tmp = dp[0] - prices[0];
			for (int i = 1; i < dp.length; i++) {
				tmp = Math.max(tmp, dp[i] - prices[i]);// dp[i]未赋值 实际为dp[i][j-1]
				dp[i] = Math.max(dp[i - 1], tmp + prices[i]);// dp[i-1] 为dp[i][j-1]
			}
		}
		return dp[N - 1];
	}
}
