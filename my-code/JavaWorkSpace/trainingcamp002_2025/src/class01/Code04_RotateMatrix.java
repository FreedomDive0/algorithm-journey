package class01;

public class Code04_RotateMatrix {
	// 旋转矩阵 从宏观角度观察
	// 矩阵需要是正方形
	public static void rotateMatrix(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix.length != matrix[0].length) {
			return;
		}
		int n = matrix.length - 1;
		int a = 0;
		int b = 0;
		int c = n;
		int d = n;
		while (a <= c) {
			rotate(matrix, a++, b++, c--, d--);
		}
	}

	public static void rotate(int[][] matrix, int a, int b, int c, int d) {
		for (int i = 0; i < d - b; i++) {
			int tmp = matrix[a][b + i];
			matrix[a][b + i] = matrix[c - i][b];
			matrix[c - i][b] = matrix[c][d - i];
			matrix[c][d - i] = matrix[a + i][d];
			matrix[a + i][d] = tmp;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		printMatrix(matrix);
		rotateMatrix(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}
}
