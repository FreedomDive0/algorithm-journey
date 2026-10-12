package class12;

public class Code05_PalindromeSubsequence {
	// 最长公共子序列
	public static int lcse(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < str2.length; i++) {
			dp[0][i] = Math.max(dp[0][i - 1], str1[0] == str2[i] ? 1 : 0);
		}
		for (int i = 1; i < str1.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					// 不加两边比较大小
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[str1.length - 1][str2.length - 1];
	}

	// 最长回文子序列
	// 1.正向反向最长公共子序列
	public static int lhw(char[] str) {
		int n = str.length;
		int dp[][] = new int[n][n];
		dp[n - 1][n - 1] = 1;
		for (int i = 0; i < n - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
		}
		for (int l = n - 3; l >= 0; l--) {
			for (int r = l + 2; r < n; r++) {
//				dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
//				if (str[l] == str[r]) {
//					dp[l][r] = Math.max(dp[l][r], dp[l + 1][r - 1] + 2);
//				}
				dp[l][r] = dp[l + 1][r - 1] + (str[l] == str[r] ? 2 : 0);
				dp[l][r] = Math.max(Math.max(dp[l][r - 1], dp[l + 1][r]), dp[l][r]);
			}
		}
		return dp[0][n - 1];
	}

	// 复习
	public static int SubSequence(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		int N1 = s1.length();
		int N2 = s2.length();
		int[][] dp = new int[N1][N2];
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		dp[0][0] = c1[0] == c2[0] ? 1 : 0;
		for (int i = 1; i < N1; i++) {
			dp[i][0] = c1[i] == c2[0] ? 1 : Math.max(dp[i - 1][0], 0);
		}
		for (int i = 1; i < N2; i++) {
			dp[0][i] = c1[0] == c2[i] ? 1 : Math.max(dp[0][i - 1], 0);
		}
		for (int i = 1; i < N1; i++) {
			for (int j = 1; j < N2; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);// 包含了dp[i-1][j-1]
				if (c1[i] == c2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[N1 - 1][N2 - 1];
	}

	public static void main(String[] args) {
		String str = "a1d2f3g4h5j";
		String str1 = "z1x2cv3b4n5m";
		System.out.println(lcse(str.toCharArray(), str1.toCharArray()));
		System.out.println(SubSequence(str, str1));
	}
}
