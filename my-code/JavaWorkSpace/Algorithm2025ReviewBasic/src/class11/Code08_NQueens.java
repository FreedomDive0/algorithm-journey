package class11;

public class Code08_NQueens {
	// N皇后问题
	public static int num1(int n) {
		if (n <= 3) {
			return 0;
		}
		return processQ(n, 0, new int[n]);
	}

	public static int processQ(int n, int i, int[] record) {
		if (i == n) {
			return 1;
		}
		int res = 0;
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;// 因为对应行的列值是直接覆盖的 所以不用还原
				res += processQ(n, i + 1, record);
			}
		}
		return res;
	}

	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {// 只需要检查到i-1行 之后的数据还未添加
			if (record[k] == j || Math.abs(k - i) == Math.abs(record[k] - j)) {// 同列共斜线
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int n = 12;
		System.out.println(num1(n));
	}
}
