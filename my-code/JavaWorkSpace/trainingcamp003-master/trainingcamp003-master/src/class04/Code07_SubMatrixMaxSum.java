package class04;

public class Code07_SubMatrixMaxSum {

	public static int maxSum(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		int[] s = null;
		for (int i = 0; i != m.length; i++) { // 开始的行号i
			s = new int[m[0].length]; //
			for (int j = i; j != m.length; j++) { // 结束的行号j，i~j行是我讨论的范围
				cur = 0;
				for (int k = 0; k != s.length; k++) {
					s[k] += m[j][k];
					cur += s[k];
					max = Math.max(max, cur);
					cur = cur < 0 ? 0 : cur;
				}
			}
		}
		return max;
	}

	// 测试
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

	// 测试
	// 7.matrix[][] return子矩阵 最大累加和
	public static int subMatrixMaxSum(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			int[] s = new int[matrix[0].length];
			for (int j = i; j < matrix.length; j++) {
				cur = 0;
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

	public static void main(String[] args) {
		int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
		System.out.println(maxSum(matrix));
		System.out.println(subMatrixMaxSum(matrix));
	}

}
