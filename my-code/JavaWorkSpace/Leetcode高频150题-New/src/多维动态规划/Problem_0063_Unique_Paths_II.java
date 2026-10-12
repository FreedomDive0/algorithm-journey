package 多维动态规划;

public class Problem_0063_Unique_Paths_II {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int n = obstacleGrid.length;
		int m = obstacleGrid[0].length;
		if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) {
			return 0;
		}
		int[] dp = new int[m];
		dp[m - 1] = 1;
		for (int j = m - 2; j >= 0; j--) {
			dp[j] = obstacleGrid[n - 1][j] == 1 ? 0 : dp[j + 1];
		}
		for (int i = n - 2; i >= 0; i--) {
			dp[m - 1] = obstacleGrid[i][m - 1] == 1 ? 0 : dp[m - 1];
			for (int j = m - 2; j >= 0; j--) {
				dp[j] = obstacleGrid[i][j] == 1 ? 0 : dp[j] + dp[j + 1];
			}
		}
		return dp[0];
	}

	public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
		int n = obstacleGrid.length;
		int m = obstacleGrid[0].length;
		if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1) {
			return 0;
		}
		return process1(obstacleGrid, 0, 0);
	}

	public int process1(int[][] obstacleGrid, int i, int j) {
		if (i == obstacleGrid.length - 1 && j == obstacleGrid[0].length - 1) {
			return 1;
		}
		int right = 0;
		if (i + 1 < obstacleGrid.length && obstacleGrid[i][j] == 0) {
			right = process1(obstacleGrid, i + 1, j);
		}
		int down = 0;
		if (j + 1 < obstacleGrid[0].length && obstacleGrid[i][j] == 0) {
			down = process1(obstacleGrid, i, j + 1);
		}
		return right + down;
	}

}
