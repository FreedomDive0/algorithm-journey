package class03;

public class Code03_MinPathSum {
	public static int minPath(int[][] martix) {
		if (martix == null || martix.length < 1 || martix[0].length < 1) {
			return 0;
		}
		int row = martix.length;
		int col = martix[0].length;
		int[] dp = new int[col];
		dp[col - 1] = martix[row - 1][col - 1];
		for (int i = col - 2; i >= 0; i--) {
			dp[i] = martix[row - 1][i] + (i + 1 < col ? dp[i + 1] : 0);// 先将最后一行填好
		}
		for (int r = row - 2; r >= 0; r--) {
			for (int c = col - 1; c >= 0; c--) {
//				Math.min(下,右)
				dp[c] = Math.min(dp[c] + martix[r][c], martix[r][c] + (c + 1 < col ? dp[c + 1] : 0));
			}
		}
		return dp[0];
	}
}
