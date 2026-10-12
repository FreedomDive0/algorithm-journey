package class12;

public class Code06_Coffee {
	// drinks为按从小到大排序喝完咖啡的时间 a为洗杯子的时间 b为挥发完的时间
	// 1.暴力递归方法
	public static int coffee(int[] drinks, int a, int b) {
		return processC(drinks, a, b, 0, 0);
	}

	// ...idx-1全部洗净不需考虑
	public static int processC(int[] drinks, int a, int b, int idx, int washLine) {
		if (idx == drinks.length - 1) {
			return Math.min(Math.max(washLine, drinks[idx]) + a, drinks[idx] + b);
		}
		int dryNext = processC(drinks, a, b, idx + 1, washLine);
		int dry = drinks[idx] + b;
		int wash = Math.max(washLine, drinks[idx]) + a;
		int washNext = processC(drinks, a, b, idx + 1, wash);
		return Math.min(Math.max(dry, dryNext), Math.max(wash, washNext));
	}

	public static int dp(int[] drinks, int a, int b) {
		if (drinks == null || drinks.length < 1) {
			return 0;
		}
		if (a >= b) {
			return drinks[drinks.length - 1] + b;// 最后一个杯子挥发的时间
		}
		int limit = 0;
		for (int time : drinks) {
			limit = Math.max(limit, time) + a;// 所有的杯子都洗 求出一个可变参数的极限 这就是业务限制
		}
		int N = drinks.length;
		int[][] dp = new int[N][limit + 1];
		for (int j = 0; j <= limit; j++) {
			dp[N - 1][j] = Math.min(Math.max(j, drinks[N - 1]) + a, drinks[N - 1] + b);
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j <= limit; j++) {
				int dryNext = dp[i + 1][j];
				int dry = drinks[i] + b;
				int wash = Math.max(j, drinks[i]) + a;// +a后可能越界
				int washNext = Integer.MAX_VALUE;// 无效答案标记
				if (wash <= limit) {
					washNext = dp[i + 1][wash];
				}
				dp[i][j] = Math.min(Math.max(dry, dryNext), Math.max(wash, washNext));
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] test = { 1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15 };
		int a1 = 3;
		int b1 = 10;
		System.out.println(coffee(test, a1, b1));
		System.out.println(dp(test, a1, b1));
	}
}
