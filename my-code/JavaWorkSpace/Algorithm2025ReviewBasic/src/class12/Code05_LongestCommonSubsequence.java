package class12;

public class Code05_LongestCommonSubsequence {
	// 一个样本作行一个样本作列的对应模型
	public static int lcse(char[] str1, char[] str2) {
		if (str1 == null || str2 == null || str1.length < 1 || str2.length < 1) {
			return 0;
		}
		int N1 = str1.length;
		int N2 = str2.length;
		int[][] dp = new int[N1][N2];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < N1; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		for (int j = 1; j < N2; j++) {
			dp[0][j] = Math.max(dp[0][j - 1], str2[j] == str1[0] ? 1 : 0);
		}
		for (int j = 1; j < N2; j++) {// 如何分析 可以想象暴力过程怎么判断 以谁作为结尾作为分类条件
			for (int i = 1; i < N1; i++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[N1 - 1][N2 - 1];
	}
}
