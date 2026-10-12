package 动态规划专题;

public class LC63_不同路径II {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		return process(obstacleGrid, 0, 0);
	}

	public int process(int[][] obstacleGrid, int x, int y) {
		if (x == obstacleGrid.length && y == obstacleGrid[0].length) {
			return 1;
		}
		if (x >= obstacleGrid.length || y >= obstacleGrid[0].length || obstacleGrid[x][y] == 1) {
			return 0;
		}
		return process(obstacleGrid, x + 1, y) + process(obstacleGrid, x, y + 1);
	}

	public int dpWays(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		if (obstacleGrid[m - 1][n - 1] == 1) {
			return 0;
		}
		dp[m - 1][n - 1] = 1;// 先得赋值才可以完成后序初始化
		for (int i = n - 2; i >= 0; i--) {
			dp[m - 1][i] = obstacleGrid[m - 1][i] == 0 ? dp[m - 1][i + 1] : 0;
		}
		for (int i = m - 2; i >= 0; i--) {
			dp[i][n - 1] = obstacleGrid[i][n - 1] == 0 ? dp[i + 1][n - 1] : 0;
		}
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i + 1][j] + dp[i][j + 1] : 0;
			}
		}
		return dp[0][0];
	}
}
