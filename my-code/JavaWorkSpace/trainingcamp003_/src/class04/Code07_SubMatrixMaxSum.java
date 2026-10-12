package class04;

public class Code07_SubMatrixMaxSum {
	public static int maxSubMatrix(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < matrix.length; i++) {// 从i行开始 往下所有i~i i~i+1 ... i~length-1
			int[] s = new int[matrix[0].length];// 记录每一行叠加的和 数组压缩技巧
			for (int j = i; j < matrix.length; j++) {// 每一行开始
				// cur每次求新一行的最大子数组和 ∴得刷新cur
				cur = 0;// 虚拟数组
				for (int k = 0; k < matrix[0].length; k++) {
					s[k] += matrix[j][k];
					cur += s[k];
					max = Math.max(max, cur);
					cur = cur < 0 ? 0 : cur;
				}
			}
		}
		return max;
	}
}
