package class12;

public class Code03_Knapsack {
	public static int getMaxValueDP(int[] w, int[] v, int bag) {
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int i = N - 1; i >= 0; i--) {
			for (int rest = 0; rest <= bag; rest++) {
				dp[i][rest] = dp[i + 1][rest];
				if (rest >= w[i]) {
					dp[i][rest] = Math.max(dp[i][rest], v[i] + dp[i + 1][rest - w[i]]);
				}
			}
		}
		return dp[0][bag];
	}
}
