package topinterviewquestions;

public class Problem_0044_WildcardMatching {
	// 暴力递归
	public static boolean isMatch1(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		return process1(s, p, 0, 0);
	}

	public static boolean process1(char[] s, char[] p, int si, int pi) {
		if (si == s.length) {
			if (pi == p.length) {
				return true;
			} else {
				return p[pi] == '*' && process1(s, p, si, pi + 1);
			}
		}
		if (pi == p.length) {
			return false;
		}
		if (p[pi] != '*') {
			if (s[si] == p[pi] || p[pi] == '?') {
				return process1(s, p, si + 1, pi + 1);
			} else {
				return false;
			}
		}
		int index = 0;
		while (index + si <= s.length) {
			if (process1(s, p, si + index, pi + 1)) {
				return true;
			}
			index++;
		}
		return false;
	}

	// 动态规划
	public static boolean isMatch2(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int n = s.length;
		int m = p.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 1; j >= 0; j--) {
			dp[n][j] = p[j] == '*' && dp[n][j + 1];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (p[j] != '*') {
					dp[i][j] = (s[i] == p[j] || p[j] == '?') && dp[i + 1][j + 1];
				} else {
					int index = 0;
					while (index + i <= n) {
						if (dp[i + index][j + 1]) {
							dp[i][j] = true;
							break;
						}
						index++;
					}
				}
			}
		}
		return dp[0][0];
	}

	// 动态规划斜率优化
	public static boolean isMatch3(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int n = s.length;
		int m = p.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 1; j >= 0; j--) {
			dp[n][j] = p[j] == '*' && dp[n][j + 1];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (p[j] != '*') {
					dp[i][j] = (s[i] == p[j] || p[j] == '?') && dp[i + 1][j + 1];
				} else {
					if (dp[i][j + 1] || dp[i + 1][j]) {
						dp[i][j] = true;
					}
				}
			}
		}
		return dp[0][0];
	}
}
