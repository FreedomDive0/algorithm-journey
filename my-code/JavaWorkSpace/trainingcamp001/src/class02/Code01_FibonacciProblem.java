package class02;

public class Code01_FibonacciProblem {
//	0,1字符同理
//	暴力递归
	public static int f1(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		return f1(n - 1) + f1(n - 2);
	}

//	线性计算
	public static int f2(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		int res = 0;
		int first = 1;
		int second = 1;
		for (int i = 2; i < n; i++) {
			res = first + second;
			first = second;
			second = res;
		}
		return res;
	}

//	矩阵优化
	public static int f3(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		int[][] m = { { 1, 1 }, { 1, 0 } };
		int[][] res = matrixPower(m, n - 2);
		return res[0][0] + res[1][0];
	}

//	矩阵次方
	public static int[][] matrixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
//		单位矩阵为默认值
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		int[][] tmp = m;
		for (; p != 0; p >>= 1) {
			if ((p & 1) == 1) {
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
		}
		return res;
	}

//	矩阵乘法
	public static int[][] muliMatrix(int[][] m1, int[][] m2) {
		if (m1[0].length != m2.length || m1 == null || m2 == null) {
			return null;
		}
		int[][] res = new int[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m1[0].length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return res;
	}

//	奶牛问题
//	今年   =    前一年的  +   可以生的 
//	n     =     n-1    +    n-3
//	暴力递归
	public static int c1(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		return c1(n - 1) + c1(n - 3);
	}

//	线性计算
	public static int c2(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int res = 3;
		int pre = 2;
		int prepre = 1;
		int tmp = 0;
		for (int i = 4; i <= n; i++) {
			tmp = prepre;
			prepre = pre;
			pre = res;
			res = res + tmp;
		}
		return res;
	}

//	矩阵优化
	public static int c3(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int[][] m = { { 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } };
//		计算出后面矩阵n-3次方
		int[][] res = matrixPower(m, n - 3);
//		结果要乘初始矩阵
		return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
	}

	// 复习
	public static int F(int N) {
		if (N < 0) {
			return -1;
		}
		if (N == 1 || N == 2) {
			return 1;
		}
		int[][] c = { { 1, 1 }, { 1, 0 } };
		int[][] c1 = QuickM(c, N - 2);
		return c1[0][0] + c1[0][1];
	}

	public static int[][] QuickM(int[][] c, int n) {
		int[][] res = new int[c.length][c.length];
		for (int i = 0; i < res.length; i++) {// 单位矩阵用于记录结果
			res[i][i] = 1;
		}
		for (; n > 0; n >>= 1) {
			if ((n & 1) == 1) {
				res = matrix(c, res);
			}
			c = matrix(c, c);
		}
		return res;
	}

	// 矩阵乘法
	public static int[][] matrix(int[][] c1, int[][] c2) {
		if (c1[0].length != c2.length || c1 == null || c2 == null) {
			return null;
		}
		int[][] res = new int[c1.length][c2[0].length];
		for (int i = 0; i < c1.length; i++) {
			for (int j = 0; j < c2[0].length; j++) {
				for (int k = 0; k < c2.length; k++) {
					res[i][j] += c1[i][k] * c2[k][j];
				}
			}
		}
		return res;
	}

//	for test
	public static void main(String[] args) {
		int n = 20;
		System.out.println(F(n));
		System.out.println(f2(n));
		System.out.println(f3(n));
		System.out.println("===");

//		System.out.println(s1(n));
//		System.out.println(s2(n));
//		System.out.println(s3(n));
//		System.out.println("===");
//
		System.out.println(c1(n));
		System.out.println(c2(n));
		System.out.println(c3(n));
//		System.out.println("===");

	}
}
