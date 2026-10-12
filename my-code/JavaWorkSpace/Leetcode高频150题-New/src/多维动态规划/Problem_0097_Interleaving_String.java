package 多维动态规划;

public class Problem_0097_Interleaving_String {
	public boolean isInterleave(String s1, String s2, String s3) {
		int n = s1.length();
		int m = s2.length();
		int t = s3.length();
		if (n + m != t) {
			return false;
		}
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				int index = i + j - 1;
				if (i > 0) {
					dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(index);
				}
				if (j > 0) {
					dp[j] |= dp[j - 1] && s2.charAt(j - 1) == s3.charAt(index);
				}
			}
		}
		return dp[m];
	}

	public boolean isInterleaveBL(String s1, String s2, String s3) {
		int n = s1.length();
		int m = s2.length();
		int t = s3.length();
		if (n + m != t) {
			return false;
		}
		return process(s1, s2, s3, n, m);
	}

	public boolean process(String s1, String s2, String s3, int len1, int len2) {
		if (len1 == 0 && len2 == 0) {
			return true;
		}
		int p = len1 + len2 - 1;
		boolean res = false;
		if (len1 > 0 && s1.charAt(len1 - 1) == s3.charAt(p)) {
			res = process(s1, s2, s3, len1 - 1, len2);
		}
		if (len2 > 0 && s2.charAt(len2 - 1) == s3.charAt(p)) {
			res |= process(s1, s2, s3, len1, len2 - 1);
		}
		return res;
	}

	public boolean isInterleave1(String s1, String s2, String s3) {
		int n = s1.length();
		int m = s2.length();
		int t = s3.length();
		if (n + m != t) {
			return false;
		}
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				int p = i + j - 1;
				if (i > 0) {
					dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
				}
				if (j > 0) {
					dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
				}
			}
		}
		return dp[m];
	}

}
