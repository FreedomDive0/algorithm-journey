package class06;

public class Code02_KInversePairs1 {
	// 给定N 有1~N这些数字 给定K 返回含有K个逆序的排序数量
	public static int kInverse(int N, int K) {
		if (N < 1 || K < 0) {
			return 0;
		}
		if (K == 0) {
			return 1;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				for (int s = j; s >= Math.max(0, j - i + 1); s--) {// dp[i-1][j]有i-1个数 最多可以提供i-1个逆序对 剩下的要由原本提供
					dp[i][j] += dp[i - 1][s];
				}
			}
		}
		return dp[N][K];
	}

	// 斜率优化版本
	public static int kInverseDP(int N, int K) {
		if (N < 1 || K < 0) {
			return 0;
		}
		if (K == 0) {
			return 1;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - (i <= j ? dp[i - 1][j - i] : 0);// dp[i][j-1] = dp[i-1][j-i~j-1] dp[i][j] = dp[i-1][j-i+1~j-1] ∴-dp[i-1][j-i]
			}
		}
		return dp[N][K];
	}
}
