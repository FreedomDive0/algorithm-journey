package class05;

public class Code04_PalindromeWays {
	// 回文序列 不同位置相同字符为不同回文串
	public static int palindromeWays(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		char[] chs = str.toCharArray();
		int N = chs.length;
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {// 第一条对角线
			dp[i][i] = 1;
		}
		for (int i = 0; i < N - 1; i++) {// 第二条对角线
			dp[i][i + 1] = chs[i] == chs[i + 1] ? 3 : 2;
		}
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {
				dp[L][R] = dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
				if (chs[L] == chs[R]) {
					dp[L][R] += dp[L + 1][R - 1] + 1;
				}
			}
		}
		return dp[0][N - 1];
	}
}
