package class12;

public class Code08_MinPathSum {
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length < 1 || m[0] == null || m[0].length < 1) {
			return 0;
		}
		return processMP(m, 0, 0);
	}

	public static int processMP(int[][] m, int i, int j) {
		if (i == m.length - 1 && j == m[0].length - 1) {
			return m[i][j];
		}
		int waySum = m[i][j];
		if (i == m.length - 1) {
			waySum += processMP(m, i, j + 1);
		} else if (j == m[0].length - 1) {
			waySum += processMP(m, i + 1, j);
		} else {
			int right = processMP(m, i + 1, j);
			int down = processMP(m, i, j + 1);
			waySum += Math.min(right, down);
		}
		return waySum;
	}

	public static int minPathSumDP(int[][] m) {
		if (m == null || m.length < 1 || m[0] == null || m[0].length < 1) {// 注意条件m[0] == null
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[row - 1][col - 1] = m[row - 1][col - 1];
		for (int c = col - 2; c >= 0; c--) {
			dp[row - 1][c] = dp[row - 1][c + 1] + m[row - 1][c];
		}
		for (int r = row - 2; r >= 0; r--) {
			dp[r][col - 1] = dp[r + 1][col - 1] + m[r][col - 1];
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
