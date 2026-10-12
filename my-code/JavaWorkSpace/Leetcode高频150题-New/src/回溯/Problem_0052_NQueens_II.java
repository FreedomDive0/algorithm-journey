package 回溯;

public class Problem_0052_NQueens_II {
	public static int totalNQueens(int N) {
		if (N < 1 || N > 32) {
			return 0;
		}
		int limit = N == 32 ? -1 : (1 << N) - 1;
		return process(limit, 0, 0, 0);
	}

	public static int process(int limit, int colLim, int leftLim, int rightLim) {
		if (limit == colLim) {
			return 1;
		}
		int res = 0;
		int pos = limit & ~(colLim | leftLim | rightLim);
		while (pos != 0) {
			int rightOne = pos & ((~pos) + 1);
			res += process(limit, colLim | rightOne, (leftLim | rightOne) << 1, (rightLim | rightOne) >>> 1);
			pos -= rightOne;
		}
		return res;
	}
}
