package 多维动态规划;

public class Problem_0064_Minimum_Path_Sum {
	public int minPathSum(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[] dp = new int[m];
		dp[0] = grid[0][0];
		for (int j = 1; j < m; j++) {
			dp[j] = dp[j - 1] + grid[0][j];
		}
		for (int i = 1; i < n; i++) {
			dp[0] += grid[i][0];
			for (int j = 1; j < m; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
			}
		}
		return dp[m - 1];
	}

	public int minPathSum2(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		dp[0][0] = grid[0][0];
		for (int j = 1; j < m; j++) {
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}
		return dp[n - 1][m - 1];
	}

	public int minPathSum1(int[][] grid) {
		return process1(grid, 0, 0, 0);
	}

	public int process1(int[][] grid, int i, int j, int sum) {
		if (i == grid.length - 1 && j == grid[0].length - 1) {
			return sum;
		}
		int right = -1;
		if (i + 1 < grid.length) {
			right = process1(grid, i + 1, j, sum + grid[i][j]);
		}
		int down = -1;
		if (j + 1 < grid[0].length) {
			down = process1(grid, i, j + 1, sum + grid[i][j]);
		}
		if (right == -1) {
			return down;
		} else if (down == -1) {
			return right;
		}
		return Math.min(right, down);
	}
}
