package class01;

public class Code06_ZigZagPrintMatrix {
	// 蛇形打印
	public static void printMatrixZigZag(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return;
		}
		int row = matrix.length - 1;
		int col = matrix[0].length - 1;
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		boolean fromLeftUp = false;
		while (b <= col) {
			printZigZag(matrix, a, b, c, d, fromLeftUp);
			b = a == row ? b + 1 : b;
			a = a < row ? a + 1 : row;// 作为判断条件的变量一定要后变
			c = d == col ? c + 1 : c;
			d = d < col ? d + 1 : col;
			fromLeftUp = !fromLeftUp;
//			System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d + " fromLeftUp: " + fromLeftUp);
		}
	}

	// 左下右上点
	public static void printZigZag(int[][] matrix, int a, int b, int c, int d, boolean fromLeftUp) {
		if (fromLeftUp) {
			while (c <= a) {
				System.out.print(matrix[c++][d--] + " ");
			}
		} else {
			while (b <= d) {
				System.out.print(matrix[a--][b++] + " ");
			}
		}
	}

	// 1 2 5 9 6 3 4 7 10 11 8 12
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}
}
