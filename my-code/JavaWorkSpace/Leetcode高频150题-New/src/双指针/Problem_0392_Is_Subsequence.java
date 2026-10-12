package 双指针;

public class Problem_0392_Is_Subsequence {
	// 1.偷鸡
	public boolean isSubsequence(String s, String t) {
		if (s.length() == 0) {
			return true;
		}
		int index = 0;
		for (char c : s.toCharArray()) {
			index = t.indexOf(c, index);
			if (index == -1) {
				return false;
			}
			index++;
		}
		return true;
	}

	// DP最长公共子序列
	public boolean isSubsequence1(String s, String t) {
		if (s.length() == 0) {
			return true;
		}
		int n = s.length();
		int m = t.length();
		char[] s1 = s.toCharArray();
		char[] s2 = t.toCharArray();
		int[][] dp = new int[n][m];
		dp[0][0] = s1[0] == s2[0] ? 1 : 0;
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
		}
		for (int j = 1; j < dp[0].length; j++) {
			dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (s1[i] == s2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[n - 1][m - 1] == s.length();
	}
}
