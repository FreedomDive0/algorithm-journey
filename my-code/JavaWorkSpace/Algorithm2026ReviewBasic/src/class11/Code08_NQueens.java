package class11;

public class Code08_NQueens {
	// N皇后问题
	public static int nQueen(int n) {
		return nQProcess(n, 0, new int[n]);
	}

	public static int nQProcess(int N, int idx, int[] pos) {
		if (idx == N) {
			return 1;
		}
		int count = 0;
		for (int j = 0; j < N; j++) {
			if (isVailed(pos, idx, j)) {
				pos[idx] = j;// 直接覆盖 上面遍历不到 下面被赋值不会受到影响
				count += nQProcess(N, idx + 1, pos);
			}
		}
		return count;
	}

	public static boolean isVailed(int[] pos, int i, int j) {
		for (int k = 0; k < i; k++) {
			if (pos[k] == j || Math.abs(k - i) == Math.abs(pos[k] - j)) {// 共列 共斜线(斜率为1)
				return false;
			}
		}
		return true;
	}

	public static int nQueens2(int N) {
		int limit = N == 32 ? -1 : (1 << N) - 1;
		return nQDProcess(N, limit, 0, 0, 0);
	}

	public static int nQDProcess(int N, int limit, int colLimit, int leftLimit, int rightLimit) {
		if (limit == colLimit) {
			return 1;
		}
		int res = 0;
		int pos = limit & ~(colLimit | leftLimit | rightLimit);// ^可能高位出错
		while (pos != 0) {
			int rightOne = pos & (~pos + 1);
			res += nQDProcess(N, limit, colLimit | rightOne, (leftLimit | rightOne) << 1,
					(rightLimit | rightOne) >>> 1);
			pos -= rightOne;
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 15;

		long start = System.currentTimeMillis();
		System.out.println(nQueen(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");
//
		start = System.currentTimeMillis();
		System.out.println(nQueens2(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

	}
}
