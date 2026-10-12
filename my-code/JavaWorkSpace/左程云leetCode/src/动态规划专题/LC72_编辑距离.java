package 动态规划专题;

public class LC72_编辑距离 {
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		char[] ch1 = word1.toCharArray();
		char[] ch2 = word2.toCharArray();
		int[][] dp = new int[m + 1][n + 1];// 添加了" "空行空列 这样一开始就不需要考虑两个字符串各自第一个字符在另一个字符出现的位置
		for (int i = 1; i <= m; i++) {// 填第一列
			dp[i][0] = dp[i - 1][0] + 1;
		}
		for (int i = 1; i <= n; i++) {// 填第一行
			dp[0][i] = dp[0][i - 1] + 1;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (ch1[i - 1] == ch2[j - 1]) {// 如果相同意味着不需要新的处理
					dp[i][j] = dp[i - 1][j - 1];
				} else {// 看那种处理需要步骤最小
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
				}
			}
		}
		return dp[m][n];
	}
}
