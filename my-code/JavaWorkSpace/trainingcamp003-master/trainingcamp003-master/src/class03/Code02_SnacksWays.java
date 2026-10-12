package class03;

public class Code02_SnacksWays {

	public static int ways1(int[] arr, int w) {
		// arr[0...]
		return process(arr, 0, w);
	}

	// 从左往右的经典模型
	// 还剩的容量是rest，arr[index...]自由选择，
	// 返回选择方案
	// index ： 0～N
	// rest : 0~w
	public static int process(int[] arr, int index, int rest) {
		if (rest < 0) { // 没有容量了
			// -1 无方案的意思
			return -1;
		}
		// rest>=0,
		if (index == arr.length) { // 无零食可选
			return 1;
		}
		// rest >=0
		// 有零食index
		// index号零食，要 or 不要
		// index, rest
		// (index+1, rest)
		// (index+1, rest-arr[i])
		int next1 = process(arr, index + 1, rest); // 不要
		int next2 = process(arr, index + 1, rest - arr[index]); // 要
		return next1 + (next2 == -1 ? 0 : next2);
	}

	public static int ways2(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N + 1][w + 1];
		for (int j = 0; j <= w; j++) {
			dp[N][j] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= w; j++) {
				dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[i]] : 0);
			}
		}
		return dp[0][w];
	}

	public static int ways3(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N][w + 1];
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		if (arr[0] <= w) {
			dp[0][arr[0]] = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= w; j++) {
				dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
			}
		}
		int ans = 0;
		for (int j = 0; j <= w; j++) {
			ans += dp[N - 1][j];
		}
		return ans;
	}

	// 测试
	public static int snacksWays(int[] v, int bag) {
		if (v == null || v.length < 1 || bag < 1) {
			return 0;
		}
		return process(v, 0, bag);
	}

	// 到index 还剩rest有几种装法
	public static int process1(int[] v, int index, int rest) {
		if (rest < 0) {
			return -1;// 装法不成立
		}
		if (index == v.length) {
			return 1;
		}
		int no = process1(v, index + 1, rest);
		int yes = process1(v, index + 1, rest - v[index]);
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

	// 测试
	// 2.背包容量为w n袋零食 v[] 一共有多少种装法
	public static int snacksWays(int w, int[] v) {
		if (w <= 0 || v == null || v.length < 1) {
			return -1;
		}
		return processSW(v, 0, w);
	}

	public static int processSW(int[] v, int index, int rest) {
		if (rest < 0) {
			return 0;
		}
		if (index == v.length) {
			return 1;
		}
		int next1 = processSW(v, index + 1, rest);
		int next2 = processSW(v, index + 1, rest - v[index]);
		return next1 + next2;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 2, 9 };
		int w = 8;
		System.out.println(snacksWays(arr, w));
		System.out.println(dpWays(arr, w));
		System.out.println(snacksWays(arr, w));

	}

}
