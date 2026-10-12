package class11;

public class Code08_NQueens {
	public static int nQueens(int limit) {
		if (limit < 1) {
			return 0;
		}
		return process(limit, new int[limit], 0);
	}

	public static int process(int limit, int[] queens, int i) {
		if (i == limit) {
			return 1;
		}
		int res = 0;
		for (int j = 0; j < limit; j++) {
			if (isVailed(i, j, queens)) {
				queens[i] = j;
				res += process(limit, queens, i + 1);
			}
		}
		return res;
	}

	public static boolean isVailed(int i, int j, int[] queens) {
		for (int k = 0; k < i; k++) {
			if (queens[k] == j || (Math.abs(i - k) == Math.abs(j - queens[k]))) {
				return false;
			}
		}
		return true;
	}

	public static int nQueens2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		int limit = n == 32 ? -1 : (1 << n) - 1;
		return process2(limit, 0, 0, 0);
	}

	public static int process2(int limit, int collimit, int leftLimit, int rightLimit) {
		if (collimit == limit) {
			return 1;
		}
		// 全部可行位置
		int pos = limit & (~(collimit | leftLimit | rightLimit));
		int res = 0;
		while (pos != 0) {
			int mostRightOne = pos & (~pos + 1);
			pos -= mostRightOne;
			res += process2(limit, collimit | mostRightOne, (leftLimit | mostRightOne) << 1,
					(rightLimit | mostRightOne) >>> 1);
		}
		return res;
	}

	// 复习
	public static int NQueen(int N) {
		if (N < 1) {
			return 0;
		}
		int[] pos = new int[N];
		return processNQ(0, N, pos);
	}

	public static int processNQ(int index, int N, int[] pos) {
		if (index == N) {
			return 1;
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			if (isVaild(pos, index, i)) {
				pos[index] = i;
				res += processNQ(index + 1, N, pos);
			}
		}
		return res;
	}

	public static boolean isVaild(int[] pos, int i, int j) {
		for (int k = 0; k < i; k++) {
			if (pos[k] == j || (Math.abs(k - i) == Math.abs(pos[k] - j))) {
				return false;
			}
		}
		return true;
	}

	// 优化
	public static int NQueenNB(int N) {
		if (N < 1 || N > 32) {// 超过int位数不够使用
			return 0;
		}
		int limit = N == 32 ? -1 : (1 << N) - 1;// 获得N位个1
		return processNQNB(limit, 0, 0, 0);
	}

	public static int processNQNB(int limits, int colLim, int leftLim, int rightLim) {// 从限制中获得开始位置
		if (colLim == limits) {// 列限制又能代表皇后所放的位置
			return 1;
		}
		int pos = limits & ~(colLim | leftLim | rightLim);// 获得可以放1的位置
		int mostRightOne = 0;
		int res = 0;
		while (pos != 0) {
			mostRightOne = pos & ((~pos) + 1);// 提取1
			pos -= mostRightOne;
			res += processNQNB(limits, colLim | mostRightOne, (leftLim | mostRightOne) << 1,
					(rightLim | mostRightOne) >>> 1);
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 13;

		long start = System.currentTimeMillis();
		System.out.println(NQueenNB(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

		start = System.currentTimeMillis();
		System.out.println(nQueens2(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

	}
}
