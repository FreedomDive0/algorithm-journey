package class02;

public class Review1 {
	// 1.斐波那契数列 快速幂优化及推广
	public static int fibonacciProblem(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		int[][] base = new int[][] { { 1, 1 }, { 1, 0 } };// 需要快速求幂的矩阵
		int[][] res = fastExponentiation(n - 2, base);
		return res[0][0] + res[1][0];// 与k阶前k项行列式相乘 返回结果
	}

	// 快速幂
	public static int[][] fastExponentiation(int n, int[][] a) {
		if (n < 0) {
			return null;
		}
		int[][] res = new int[a.length][a[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		int[][] tmp = a;
		while (n != 0) {
			if ((n & 1) != 0) {// 二进制拆分幂
				res = multiplyingDeterminants(res, tmp);
			}
			tmp = multiplyingDeterminants(tmp, tmp);
			n >>= 1;
		}
		return res;
	}

	// 行列式相乘 两个数组相乘
	public static int[][] multiplyingDeterminants(int[][] a, int b[][]) {
		if (a == null || b == null || a.length != b[0].length) {
			return null;
		}
		int[][] res = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					res[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return res;
	}

	// 2.蓄水池算法
	public static class RandomBox1 {
		private int[] bag;
		private int count;
		private int capacity;

		public RandomBox1(int capacity) {
			this.bag = new int[capacity];
			this.count = 0;
			this.capacity = capacity;
		}

		public void add(int num) {
			count++;
			if (count < capacity) {
				bag[count - 1] = num;
			} else {
				if (rand(count) <= capacity) {
					bag[rand(capacity) - 1] = num;
				}
			}
		}

		public int[] choices() {
			int[] ans = new int[capacity];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = bag[i];
			}
			return ans;
		}

		private static int rand(int n) {
			return (int) (Math.random() * n) + 1;
		}
	}

	// 3.Random to Random 使用给定的随机函数改造出需要的随机函数
	// 3.Random to Random 使用给定的随机函数改造出需要的随机函数
	public static int randomToRandom(RandomBox randombox, int from, int to) {
		if (from == to) {
			return from;
		}
		int range = to - from;
		int ans = 0;
		int num = 1;// 所需二进数的位数
		while ((1 << num) - 1 < range) {
			num++;
		}
		do {
			ans = 0;
			for (int i = 0; i < num; i++) {
				ans |= (rand01(randombox) << i);
			}
		} while (ans > range);
		return ans + from;
	}

	public static int rand01(RandomBox randomBox) {// 等概率生成01
		int min = randomBox.min;
		int max = randomBox.max;
		int range = max - min + 1;
		boolean odd = (range & 1) == 1;// 是否为奇数
		int ans = 0;
		do {
			ans = randomBox.random();
		} while (odd && ans == range / 2);// 1.个数不是奇数 2.个数是奇数舍弃中间
		return ans < range / 2 ? 1 : 0;
	}

	public static class RandomBox {
		private final int min;
		private final int max;

		// 初始化时请一定不要让mi==ma
		public RandomBox(int mi, int ma) {
			min = mi;
			max = ma;
		}

		// 13 ~ 17
		// 13 + [0,4]
		public int random() {
			return min + (int) (Math.random() * (max - min + 1));
		}

		public int min() {
			return min;
		}

		public int max() {
			return max;
		}
	}

	public static void main(String[] args) {
		int[][] a = new int[][] { { 1, 0, 2 }, { -1, 3, 1 } };
		int[][] b = new int[][] { { 3, 1 }, { 2, 1 }, { 1, 0 } };
		int[][] res = multiplyingDeterminants(a, b);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + "   ");
			}
			System.out.println();
		}
	}
}
