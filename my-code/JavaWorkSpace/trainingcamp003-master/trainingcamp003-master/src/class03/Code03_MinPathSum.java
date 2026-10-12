package class03;

public class Code03_MinPathSum {

	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}

	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int more = Math.max(m.length, m[0].length);
		int less = Math.min(m.length, m[0].length);
		boolean rowmore = more == m.length;
		int[] arr = new int[less];
		arr[0] = m[0][0];
		for (int i = 1; i < less; i++) {
			arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);
		}
		for (int i = 1; i < more; i++) {
			arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
			for (int j = 1; j < less; j++) {
				arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? m[i][j] : m[j][i]);
			}
		}
		return arr[less - 1];
	}

	public static int minPathSum3(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int[] dp = new int[m[0].length];
		int N = m.length;
		int M = m[0].length;
		dp[0] = m[0][0];
		for (int col = 1; col < M; col++) {
			dp[col] = dp[col - 1] + m[0][col];
		}
		for (int row = 1; row < N; row++) {
			dp[0] = dp[0] + m[row][0];
			for (int col = 1; col < M; col++) {
				dp[col] = Math.min(dp[col - 1], dp[col]) + m[row][col];
			}
		}
		return dp[M - 1];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 10);
			}
		}
		return result;
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

	// 测试
	public static int minPath(int[][] martix) {
		if (martix == null || martix.length < 1 || martix[0].length < 1) {
			return 0;
		}
		int row = martix.length;
		int col = martix[0].length;
		int[] dp = new int[col];
		dp[col - 1] = martix[row - 1][col - 1];
		for (int i = col - 2; i >= 0; i--) {
//			dp[i] = martix[row - 1][i] + (i + 1 < col ? dp[i + 1] : 0);// 先将最后一行填好
			dp[i] = martix[row - 1][i] + dp[i + 1];
		}
		for (int r = row - 2; r >= 0; r--) {
			for (int c = col - 1; c >= 0; c--) {
//				dp[c] = Math.min(dp[c] + martix[r][c], martix[r][c] + (c + 1 < col ? dp[c + 1] : 0));
				dp[c] = Math.min(dp[c] + martix[r][c], martix[r][c] + dp[c + 1]);
			}
		}
		return dp[0];
	}

	// 测试
	// 3.matrix[][] 全为正数 可以向下向右走 return 最小路径和
	public static int minPathSum(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return -1;
		}
		return processMPS(matrix, 0, 0, matrix.length, matrix[0].length);
	}

	public static int processMPS(int[][] matrix, int row, int col, int n, int m) {
		if (row == n - 1 && col == m - 1) {
			return matrix[row][col];
		}
		if (row == n - 1) {
			return matrix[row][col] + processMPS(matrix, row, col + 1, n, m);
		}
		if (col == m - 1) {
			return matrix[row][col] + processMPS(matrix, row + 1, col, n, m);
		}
		int next3 = matrix[row][col] + processMPS(matrix, row + 1, col, n, m);
		int next4 = matrix[row][col] + processMPS(matrix, row, col + 1, n, m);
		int min = Math.min(next3, next4);
		return min;
	}

	public static int MPSDP(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return -1;
		}
		int n = matrix.length;
		int m = matrix[0].length;
		int[] dp = new int[m];
		dp[m - 1] = matrix[n - 1][m - 1];
		for (int col = m - 2; col >= 0; col--) {
			dp[col] = dp[col + 1] + matrix[n - 1][col];
		}
		for (int row = n - 2; row >= 0; row--) {
			dp[m - 1] += matrix[row][m - 1];
			for (int col = m - 2; col >= 0; col--) {
				dp[col] = matrix[row][col] + Math.min(dp[col], dp[col + 1]);
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		// int[][] m = generateRandomMatrix(3, 4);
		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
		printMatrix(m);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));
		System.out.println(minPathSum3(m));
		System.out.println(MPSDP(m));
	}
}
