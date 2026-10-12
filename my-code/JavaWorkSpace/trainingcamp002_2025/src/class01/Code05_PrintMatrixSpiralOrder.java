package class01;

public class Code05_PrintMatrixSpiralOrder {
	// 转圈打印
	public static void spiralOrderPrint(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return;
		}
		int row = matrix.length - 1;
		int col = matrix[0].length - 1;
		int a = 0;
		int b = 0;
		int c = row;
		int d = col;
		while (a <= c && b <= d) {
			rotatePrint(matrix, a++, b++, c--, d--);
		}
	}

	public static void rotatePrint(int[][] matrix, int a, int b, int c, int d) {
		if (a == c) {// 一行
			int j = 0;
			while (b + j <= d) {
				System.out.print(matrix[a][b + j] + " ");
			}
		} else if (b == d) {// 一列
			int i = 0;
			while (a + i <= c) {
				System.out.print(matrix[a + i][b] + " ");
			}
		} else {// 正常转圈打印
			int curRow = a;
			int curCol = b;
			while (curCol < d) {
				System.out.print(matrix[a][curCol++] + " ");
			}
			while (curRow < c) {
				System.out.print(matrix[curRow++][d] + " ");
			}
			while (curCol > b) {
				System.out.print(matrix[c][curCol--] + " ");
			}
			while (curRow > a) {
				System.out.print(matrix[curRow--][b] + " ");
			}
		}
	}

//	1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}
}
