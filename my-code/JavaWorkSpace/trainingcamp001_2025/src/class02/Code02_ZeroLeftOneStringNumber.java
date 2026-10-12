package class02;

public class Code02_ZeroLeftOneStringNumber {
	public static int getNum1(int n) {
		if (n < 1) {
			return 0;
		}
		return fi(n - 1);
	}

	public static int fi(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		int[][] modulus = { { 1, 1 }, { 1, 0 } };
		modulus = matrixPower(modulus, n - 2);
		return modulus[0][0] + modulus[1][0];
	}

	public static int[][] matrixPower(int[][] a, int p) {
		int n = a.length;
		int[][] ans = new int[n][n];
		for (int i = 0; i < n; i++) {// 将矩阵赋值为单位矩阵
			ans[i][i] = 1;
		}
		while (p != 0) {
			if ((p & 1) != 0) {
				ans = muliMatrix(ans, a);
			}
			a = muliMatrix(a, a);
			p >>= 1;
		}
		return ans;
	}

	public static int[][] muliMatrix(int[][] a, int[][] b) {
		if (a == null || a.length < 1 || b == null || b.length < 1 || b[0].length < 1 || a.length != b[0].length) {
			return null;
		}
		int row = a.length;
		int col = b[0].length;
		int n = a[0].length;
		int[][] res = new int[a.length][b[0].length];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < n; k++) {
					res[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		for (int i = 0; i != 20; i++) {
			System.out.println(getNum1(i));
			System.out.println("===================");
		}

	}

}
