package class01;

public class Code05_MaxOneBorderSize {

	public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
		int r = m.length;
		int c = m[0].length;
		if (m[r - 1][c - 1] == 1) {
			right[r - 1][c - 1] = 1;
			down[r - 1][c - 1] = 1;
		}
		for (int i = r - 2; i != -1; i--) {
			if (m[i][c - 1] == 1) {
				right[i][c - 1] = 1;
				down[i][c - 1] = down[i + 1][c - 1] + 1;
			}
		}
		for (int i = c - 2; i != -1; i--) {
			if (m[r - 1][i] == 1) {
				right[r - 1][i] = right[r - 1][i + 1] + 1;
				down[r - 1][i] = 1;
			}
		}
		for (int i = r - 2; i != -1; i--) {
			for (int j = c - 2; j != -1; j--) {
				if (m[i][j] == 1) {
					right[i][j] = right[i][j + 1] + 1;
					down[i][j] = down[i + 1][j] + 1;
				}
			}
		}
	}

	public static int getMaxSize(int[][] m) {
		int[][] right = new int[m.length][m[0].length];
		int[][] down = new int[m.length][m[0].length];
		setBorderMap(m, right, down); // O(N^2); +

		for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
			if (hasSizeOfBorder(size, right, down)) {
				return size;
			}
		}
		return 0;
	}

	public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
		for (int i = 0; i != right.length - size + 1; i++) {
			for (int j = 0; j != right[0].length - size + 1; j++) {
				if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
						&& down[i][j + size - 1] >= size) {
					return true;
				}
			}
		}
		return false;
	}

	public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
		int[][] res = new int[rowSize][colSize];
		for (int i = 0; i != rowSize; i++) {
			for (int j = 0; j != colSize; j++) {
				res[i][j] = (int) (Math.random() * 2);
			}
		}
		return res;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 测试
	public static int maxSquare(int[][] m) {
		if (m == null || m.length < 1 || m[0].length < 1) {
			return 0;
		}
		int r = m.length;
		int c = m[0].length;
		int[][] rightNum = new int[r][c];
		int[][] downNum = new int[r][c];
		int ans = 0;
		for (int i = 0; i < r; i++) {
			rightNum[i][c - 1] = m[i][c - 1];
		}
		for (int j = c - 2; j >= 0; j--) {// (i,j)位置包括自身及右侧有多少个连续的1
			for (int i = 0; i < r; i++) {
				rightNum[i][j] += m[i][j] == 0 ? 0 : rightNum[i][j + 1] + 1;
			}
		}
		for (int j = 0; j < c; j++) {
			downNum[r - 1][j] = m[r - 1][j];
		}
		for (int i = r - 2; i >= 0; i--) {// (i,j)位置包括自身及下侧有多少个连续的1
			for (int j = 0; j < c; j++) {
				downNum[i][j] += m[i][j] == 0 ? 0 : downNum[i + 1][j] + 1;
			}
		}
		for (int k = Math.min(r, c); k > 0; k--) {
			if (hasBorder(rightNum, downNum, k)) {
				ans = Math.max(ans, k);
			}
		}
		return ans;
	}

	public static boolean hasBorder(int[][] rightNum, int[][] downNum, int k) {
		for (int i = 0; i < rightNum.length - k + 1; i++) {// 以(i,j)为正方形的左上角遍历矩阵
			for (int j = 0; j < downNum.length - k + 1; j++) {
				if (rightNum[i][j] >= k && downNum[i][j] >= k && rightNum[i + k - 1][j] >= k
						&& downNum[i][j + k - 1] >= k) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = generateRandom01Matrix(7, 8);
		printMatrix(matrix);
		boolean flag = true;
		for (int i = 0; i < 500000; i++) {
			if (getMaxSize(matrix) != maxSquare(matrix)) {
				flag = false;
				break;
			}
		}
		System.out.println(flag ? "成了" : "寄");
//		System.out.println(getMaxSize(matrix));
//		System.out.println(maxSquare(matrix));
	}
}
