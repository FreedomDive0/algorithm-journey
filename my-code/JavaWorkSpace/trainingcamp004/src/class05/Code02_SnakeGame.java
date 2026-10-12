package class05;

public class Code02_SnakeGame {
	// matrix[][]地图正负零都有 蛇可以向左 左上 右上移动 可以发动一次技能 把负数变正 返回最大路径和
	public static int Snake(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int[] ans = SnakeProcess(matrix, i, j);
				res = Math.max(Math.max(ans[0], ans[1]), res);
			}
		}
		return res;
	}

	// 从假想的最优左侧到达(i,j)的旅程中
	public static int[] SnakeProcess(int[][] matrix, int i, int j) {
		if (j == 0) {
			return new int[] { matrix[i][j], -matrix[i][j] };
		}
		int[] preAns = SnakeProcess(matrix, i, j - 1);// 左侧
		int preUnused = preAns[0];
		int preUsed = preAns[1];
		if (i - 1 >= 0) {
			preAns = SnakeProcess(matrix, i - 1, j - 1);// 左上
			preUnused = Math.max(preUnused, preAns[0]);
			preUsed = Math.max(preUsed, preAns[1]);
		}
		if (i + 1 < matrix.length) {
			preAns = SnakeProcess(matrix, i + 1, j - 1);// 右下
			preUnused = Math.max(preUnused, preAns[0]);
			preUsed = Math.max(preUsed, preAns[1]);
		}
		int no = -1;
		int yes = -1;
		if (preUnused >= 0) {
			no = preUnused + matrix[i][j];
			yes = preUnused - matrix[i][j];
		}
		if (preUsed >= 0) {
			yes = Math.max(yes, preUsed + matrix[i][j]);
		}
		return new int[] { no, yes };
	}
}
