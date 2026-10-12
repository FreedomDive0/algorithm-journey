package class12;

public class Code08_MinPathSum {
	public static int minPath1(int[][] matrix) {
		int cow = matrix.length;
		int col = matrix[0].length;
		int[][] dp = new int[cow][col];
		dp[0][0] = matrix[0][0];
		for (int i = 1; i < cow; i++) {
			dp[i][0] = dp[i - 1][0] + matrix[i][0];
		}
		for (int i = 1; i < col; i++) {
			dp[0][i] = dp[0][i - 1] + matrix[0][i];
		}
		for (int x = 1; x < cow; x++) {
			for (int y = 1; y < col; y++) {
				dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]) + matrix[x][y];
			}
		}
		return dp[cow - 1][col - 1];
	}

	// 优化无非时间空间
	public static int minPath2(int[][] matrix) {
		int cow = matrix.length;
		int col = matrix[0].length;
		int[] dp = new int[col];
		dp[0] = matrix[0][0];
//		初始化第一行
		for (int i = 1; i < col; i++) {
			dp[i] = dp[i - 1] + matrix[0][i];
		}
		for (int x = 1; x < cow; x++) {
//			首列需要额外更新
			dp[0] += matrix[x][0];
			for (int y = 1; y < col; y++) {
				dp[y] = Math.min(dp[y], dp[y - 1]) + matrix[x][y];
			}
		}
		return dp[col - 1];
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

	// 复习
	public static int MinPath(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		return processMP(matrix, matrix.length - 1, matrix[0].length - 1, 0, 0);
	}

	public static int processMP(int[][] matrix, int x, int y, int curX, int curY) {
		if (curX == matrix.length - 1 && curY == matrix[0].length - 1) {
			return matrix[x][y];
		}
		int rightNext = Integer.MAX_VALUE;
		if (curX + 1 <= x) {
			rightNext = processMP(matrix, x, y, curX + 1, curY) + matrix[curX][curY];
		}
		int downNext = Integer.MAX_VALUE;
		if (curY + 1 <= y) {
			downNext = processMP(matrix, x, y, curX, curY + 1) + matrix[curX][curY];
		}
		return Math.min(rightNext, downNext);
	}

	// 9.1最小路径和DP
	public static int MinPathDP(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		int x = matrix.length - 1;
		int y = matrix[0].length - 1;
		int[][] dp = new int[x + 1][y + 1];
		dp[x][y] = matrix[x][y];
		for (int curY = y - 1; curY >= 0; curY--) {
			dp[x][curY] = dp[x][curY + 1] + matrix[x][curY];
		}
		for (int curX = x - 1; curX >= 0; curX--) {
			dp[curX][y] = dp[curX + 1][y] + matrix[curX][y];
		}
		for (int curX = x - 1; curX >= 0; curX--) {
			for (int curY = y - 1; curY >= 0; curY--) {
				int rightNext = Integer.MAX_VALUE;
				if (curX + 1 <= x) {
					rightNext = dp[curX + 1][curY] + matrix[curX][curY];
				}
				int downNext = Integer.MAX_VALUE;
				if (curY + 1 <= y) {
					downNext = dp[curX][curY + 1] + matrix[curX][curY];
				}
				dp[curX][curY] = Math.min(rightNext, downNext);
			}
		}
//		测试
//		for (int i = 0; i < dp.length; i++) {
//			System.out.println();
//			for (int j = 0; j < dp[0].length; j++) {
//				System.out.print(dp[i][j] + "\t");
//			}
//		}
		return dp[0][0];
	}

	// 9.2最小路径和DP 空间优化
	public static int MinPathDP1(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		int x = matrix.length - 1;
		int y = matrix[0].length - 1;
		int[] dp = new int[y + 1];
		dp[y] = matrix[x][y];// 因为只依赖上 左单个位置 所以可以只是用一行dp
		for (int curY = y - 1; curY >= 0; curY--) {
			dp[curY] = dp[curY + 1] + matrix[x][curY];
		}
		for (int curX = x - 1; curX >= 0; curX--) {
			for (int curY = y; curY >= 0; curY--) {
				dp[curY] = Math.min(dp[curY], curY + 1 > y ? Integer.MAX_VALUE : dp[curY + 1]) + matrix[curX][curY];
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		// int[][] m = generateRandomMatrix(3, 4);
		int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
		printMatrix(m);
		System.out.println(MinPathDP1(m));
		System.out.println(minPath2(m));

	}
}
