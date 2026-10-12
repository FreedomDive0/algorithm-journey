package class03;

public class Code02_SnacksWays {
	public static int snacksWays(int[] v, int bag) {
		if (v == null || v.length < 1 || bag < 1) {
			return 0;
		}
		return process(v, 0, bag);
	}

	// 到index 还剩rest有几种装法
	public static int process(int[] v, int index, int rest) {
		if (rest < 0) {
			return -1;// 装法不成立
		}
		if (index == v.length) {
			return 1;
		}
		int no = process(v, index + 1, rest);
		int yes = process(v, index + 1, rest - v[index]);
		return no + (yes != -1 ? yes : 0);
	}

	public static int dpWays(int[] v, int bag) {
		if (v == null || v.length < 1 || bag < 1) {
			return 0;
		}
		int[] dp = new int[bag + 1];
		for (int i = 0; i < bag + 1; i++) {
			dp[i] = 1;// 初始化第一行
		}
		for (int index = v.length - 1; index >= 0; index--) {
			for (int i = bag; i >= 0; i--) {
				dp[i] = dp[i] + (i - v[index] >= 0 ? dp[i - v[index]] : 0);
			}
		}
		return dp[bag];
	}
}
