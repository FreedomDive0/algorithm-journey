package 多维动态规划;

public class Problem_0221_Maximal_Square {
	public int maximalSquare(char[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int ans = 0;
		int[][] dp = new int[n][m];
		for (int j = 0; j < m; j++) {
			dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
			ans = matrix[0][j] == '1' ? 1 : ans;
		}
		for (int i = 0; i < n; i++) {
			dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
			ans = matrix[i][0] == '1' ? 1 : ans;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (matrix[i][j] == '1') {
					dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		return ans * ans;
	}

}
