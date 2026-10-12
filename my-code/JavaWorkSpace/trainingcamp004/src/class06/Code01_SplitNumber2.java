package class06;

public class Code01_SplitNumber2 {
	// 数字分裂dp
	public static int splitNumDP(int N) {
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}
		for (int pre = N - 1; pre > 0; pre--) {
			for (int rest = pre + 1; rest <= N; rest++) {
				dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];// 斜率优化
			}
		}
		return dp[1][N];
	}
}
