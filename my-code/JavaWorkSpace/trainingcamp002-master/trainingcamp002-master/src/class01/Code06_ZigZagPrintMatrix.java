package class01;

public class Code06_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int Ar = 0;
		int Ac = 0;
		int Br = 0;
		int Bc = 0;
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		boolean fromUp = false;
		while (Ar != endR + 1) {
			printLevel(matrix, Ar, Ac, Br, Bc, fromUp);
			Ar = Ac == endC ? Ar + 1 : Ar;
			Ac = Ac == endC ? Ac : Ac + 1;
			Bc = Br == endR ? Bc + 1 : Bc;
			Br = Br == endR ? Br : Br + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	public static void printLevel(int[][] m, int ar, int ac, int br, int bc, boolean f) {
		if (f) {
			while (ar != br + 1) {
				System.out.print(m[ar++][ac--] + " ");
			}
		} else {
			while (br != ar - 1) {
				System.out.print(m[br--][bc++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
