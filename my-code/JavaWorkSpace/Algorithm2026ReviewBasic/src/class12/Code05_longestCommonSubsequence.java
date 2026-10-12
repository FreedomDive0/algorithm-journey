package class12;

public class Code05_longestCommonSubsequence {
	// 最长公共子序列 暴力递归
	public static int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
			return 0;
		}
		int[][] dp = new int[text1.length()][text2.length()];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		return lCSProcess(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1, dp);
	}

	// chs1从0~i chs2从0~j 最长公共子序列
	public static int lCSProcess(char[] chs1, char[] chs2, int i, int j, int[][] dp) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (i == 0 && j == 0) {
			return chs1[i] == chs2[j] ? 1 : 0;
		}
		int no = lCSProcess(chs1, chs2, i - 1, j - 1, dp);// 公共子序列不以两个字符串字符结尾
		int first = lCSProcess(chs1, chs2, i, j - 1, dp);// 公共子序列以chs1的字符为结尾
		int second = lCSProcess(chs1, chs2, i - 1, j, dp);// 公共子序列以chs2的字符为结尾
		int all = -1;
		if (chs1[i] == chs2[j]) {
			all = no + 1;
		}
		dp[i][j] = Math.max(Math.max(no, first), Math.max(second, all));
		return dp[i][j];
	}

	// 最长公共子序列 动态规划
	public static int longestCommonSubsequenceDP(String text1, String text2) {
		if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
			return 0;
		}
		int N1 = text1.length();
		int N2 = text2.length();
		int[][] dp = new int[N1][N2];
		char[] chs1 = text1.toCharArray();
		char[] chs2 = text2.toCharArray();
		dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
		for (int r = 1; r < N1; r++) {
			dp[r][0] = Math.max(dp[r - 1][0], chs1[r] == chs2[0] ? 1 : 0);
		}
		for (int c = 1; c < N2; c++) {
			dp[0][c] = Math.max(dp[0][c - 1], chs1[0] == chs2[c] ? 1 : 0);
		}
		for (int i = 1; i < N1; i++) {
			for (int j = 1; j < N2; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (chs1[i] == chs2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[N1 - 1][N2 - 1];
	}
}
