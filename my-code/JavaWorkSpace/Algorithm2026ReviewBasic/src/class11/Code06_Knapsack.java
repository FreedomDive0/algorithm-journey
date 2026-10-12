package class11;

public class Code06_Knapsack {
	// 背包问题
	public static int getMaxValue(int[] w, int[] v, int bag) {
		return bProcess(w, v, 0, bag);
	}

	public static int bProcess(int[] w, int[] v, int idx, int rest) {
		if (idx == w.length) {
			return 0;
		}
		int no = bProcess(w, v, idx + 1, rest);
		int yes = 0;
		if (rest >= w[idx]) {
			yes = v[idx] + bProcess(w, v, idx + 1, rest - w[idx]);
		}
		return Math.max(no, yes);
	}

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

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(getMaxValue(weights, values, bag));
		System.out.println(getMaxValueDP(weights, values, bag));
	}
}
