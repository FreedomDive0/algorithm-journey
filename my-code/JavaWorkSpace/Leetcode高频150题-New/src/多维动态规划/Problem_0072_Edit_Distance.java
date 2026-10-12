package 多维动态规划;

public class Problem_0072_Edit_Distance {
	// 空间压缩
	public int minDistance(String word1, String word2) {
		int n = word1.length();
		int m = word2.length();
		int[] dp = new int[m + 1];
		for (int j = 1; j <= m; j++) {
			dp[j] = j;
		}
		for (int i = 1; i <= n; i++) {
			int leftUp = dp[0];
			dp[0] = i;
			for (int j = 1; j <= m; j++) {
				int up = dp[j];
				dp[j] = leftUp + 1;// 1.i-1 j-1位置不同替换
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {// 2.i-1 j-1位置相同copy
					dp[j] = leftUp;
				}
				dp[j] = Math.min(dp[j], up + 1);// 3.i-1长度搞定j 删除最后字符
				dp[j] = Math.min(dp[j], dp[j - 1] + 1);// 4.i超度搞定j-1长度 添加j位置字符
				leftUp = up;
			}
		}
		return dp[m];
	}

	public int minDistanceDP1(String word1, String word2) {
		int n = word1.length();
		int m = word2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + 1;// 1.i-1 j-1位置不同替换
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {// 2.i-1 j-1位置相同copy
					dp[i][j] = dp[i - 1][j - 1];
				}
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);// 3.i-1长度搞定j 删除最后字符
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);// 4.i超度搞定j-1长度 添加j位置字符
			}
		}
		return dp[n][m];
	}
}
