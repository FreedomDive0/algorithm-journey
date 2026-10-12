package class05;

public class Code01_PalindromeSubsequence {
	// 字符串最长回文子序列
	public static int longestPalindromeSubStr(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		char[] chs = str.toCharArray();
		int N = chs.length;
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			dp[i][i] = 1;// 对角线
		}
		for (int j = 1; j < N; j++) {// 上对角线
			dp[j - 1][j] = chs[j - 1] == chs[j] ? 2 : 0;
		}
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {// R最小到L+2
				dp[L][R] = Math.max(dp[L + 1][R], dp[L][R - 1]);// dp[L + 1][R], dp[L][R - 1] 都跟dp[L+1][R-1]比较过
				if (chs[L] == chs[R]) {
					dp[L][R] = Math.max(dp[L][R], dp[L + 1][R - 1] + 2);
				}
			}
		}
		return dp[0][N - 1];
	}
}
