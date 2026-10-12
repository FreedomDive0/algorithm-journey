package class12;

public class Code08_MinPathSum {
	// 最小路径和 暴力递归
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		return mPProcess(m, 0, 0);
	}

	public static int mPProcess(int[][] m, int x, int y) {
		if (x == m.length - 1 && y == m[0].length - 1) {
			return m[x][y];
		}
		int right = Integer.MAX_VALUE;
		if (x + 1 < m.length) {
			right = m[x][y] + mPProcess(m, x + 1, y);
		}
		int down = Integer.MAX_VALUE;
		if (y + 1 < m[0].length) {
			down = m[x][y] + mPProcess(m, x, y + 1);
		}
		return Math.min(right, down);
	}

	public static int minPathSumDP(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[row - 1][col - 1] = m[row - 1][col - 1];
		for (int r = row - 2; r >= 0; r--) {
			dp[r][col - 1] = m[r][col - 1] + dp[r + 1][col - 1];
		}
		for (int c = col - 2; c >= 0; c--) {
			dp[row - 1][c] = m[row - 1][c] + dp[row - 1][c + 1];
		}
		for (int r = row - 2; r >= 0; r--) {
			for (int c = col - 2; c >= 0; c--) {
				dp[r][c] = Math.min(dp[r + 1][c], dp[r][c + 1]) + m[r][c];
			}
		}
		return dp[0][0];
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// int[][] m = generateRandomMatrix(3, 4);
		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
		printMatrix(m);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSumDP(m));

	}
}
