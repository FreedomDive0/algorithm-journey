package class01;

public class Code01_LongestIncreasingPath {
	public static int increasingPath(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int[][] dp = new int[matrix.length][matrix[0].length];
		int ans = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				ans = Math.max(ans, process(matrix, i, j, dp));// 尝试每个位置 返回最大值
			}
		}
		return ans;
	}

	public static int process(int[][] matrix, int i, int j, int[][] dp) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}
		if (dp[i][j] != 0) {// 记忆化搜索 因为动态规划依赖关系复杂需要上下左右不能用严格顺序填写
			return dp[i][j];
		}
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		if (i - 1 >= 0 && matrix[i][j] < matrix[i - 1][j]) {
			up = process(matrix, i - 1, j, dp);
		}
		if (i + 1 < matrix.length && matrix[i][j] < matrix[i + 1][j]) {
			down = process(matrix, i - 1, j, dp);
		}
		if (j - 1 >= 0 && matrix[i][j] < matrix[i][j - 1]) {
			left = process(matrix, i - 1, j, dp);
		}
		if (j + 1 < matrix[0].length && matrix[i][j] < matrix[i][j + 1]) {
			right = process(matrix, i - 1, j, dp);
		}
		dp[i][j] = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
		return dp[i][j];
	}
}
