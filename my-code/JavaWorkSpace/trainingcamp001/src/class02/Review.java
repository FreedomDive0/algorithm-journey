package class02;

public class Review {
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

//	 蓄水池算法
//	 明白流程
	// 由等概率生成a-b——>等概率生成c——>d
	// 这个结构是唯一的随机机制
	// 你只能初始化并使用，不可修改
	public static class RandomBox {
		private int min;
		private int max;

		// 初始化时请一定不要让mi==ma
		public RandomBox(int mi, int ma) {
			min = mi;
			max = ma;
		}

//				随机生成min~max的数
		public int rand() {
			return (int) (Math.random() * (max - min + 1)) + min;
		}

		public int min() {
			return min;
		}

		public int max() {
			return max;
		}
	}

	public static int RandomToRandom(int a, int b, int c, int d) {
		if (c == d) {
			return c;
		}
		RandomBox rb = new RandomBox(a, b);
		int size = d - c;
		int pos = 0;// c-d包含数字有几位二进制
		int res = 0;
		while ((1 << pos) - 1 < size) {
			pos++;
		}
		do {
			res = 0;
			for (int i = 0; i < pos; i++) {
				res |= RandomOneZero(rb) << i;
			}
		} while (res > size);// 范围不一定吻合 需要剔除

		return res + c;
	}

	public static int RandomOneZero(RandomBox rb) {// 等概率生成01
		int size = rb.max - rb.min + 1;
		int res = 0;
		boolean odd = size % 2 != 0;
		int mid = size / 2;
		do {
			res = rb.rand();
		} while (odd && res == mid);// 是奇数 舍弃mid
		return res < mid ? 1 : 0;
	}

	public static void main(String[] args) {
		RandomBox rb = new RandomBox(1, 10);
		for (int i = 0; i < 100; i++) {
			System.out.print(rb.rand() + " ");
		}
	}
}
