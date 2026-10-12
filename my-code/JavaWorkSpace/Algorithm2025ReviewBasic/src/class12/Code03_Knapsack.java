package class12;

public class Code03_Knapsack {
	// 动态规划
	public static int getMaxValueDP(int[] w, int[] v, int bag) {
		if (w == null || w.length < 1 || v == null || v.length < 1 || bag < 1 || w.length != v.length) {
			return 0;
		}
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int idx = N - 1; idx >= 0; idx--) {
			for (int rest = 0; rest <= bag; rest++) {
				dp[idx][rest] = dp[idx + 1][rest];
				if (rest >= w[idx]) {
					dp[idx][rest] = Math.max(dp[idx][rest], dp[idx + 1][rest - w[idx]] + v[idx]);
				}
			}
		}
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(getMaxValueDP(weights, values, bag));
	}
}
