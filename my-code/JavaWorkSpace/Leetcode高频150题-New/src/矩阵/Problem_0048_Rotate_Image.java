package 矩阵;

public class Problem_0048_Rotate_Image {
	public void rotate(int[][] matrix) {
		if (matrix.length == 1) {
			return;
		}
		int lr = 0;
		int lc = 0;
		int rr = matrix.length - 1;
		int rc = matrix.length - 1;
		while (lr <= rr && lc <= rc) {
			process(matrix, lr++, lc++, rr--, rc--);
		}
	}

	public void process(int[][] matrix, int lr, int lc, int rr, int rc) {
		for (int i = 0; i < rc - lc; i++) {
			int tmp = matrix[lr][lc + i];
			matrix[lr][lc + i] = matrix[rr - i][lc];
			matrix[rr - i][lc] = matrix[rr][rc - i];
			matrix[rr][rc - i] = matrix[lr + i][rc];
			matrix[lr + i][rc] = tmp;
		}
	}
}
