package class06;

public class Code01_SplitNumer1 {

	public static int ways1(int n) {
		if (n < 1) {
			return 0;
		}
		return process(1, n);
	}

	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) {
			return 0;
		}
		int ways = 0;
		for (int i = pre; i <= rest; i++) {
			ways += process(i, rest - i);
		}
		return ways;
	}

	public static int ways2(int n) {
		if (n < 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][0] = 1;
		}
		for (int pre = n; pre > 0; pre--) {
			for (int rest = pre; rest <= n; rest++) {
				for (int i = pre; i <= rest; i++) {
					dp[pre][rest] += dp[i][rest - i];
				}
			}
		}
		return dp[1][n];
	}

	public static int ways3(int n) {
		if (n < 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][0] = 1;
		}
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][pre] = 1;
		}
		for (int pre = n - 1; pre > 0; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
			}
		}
		return dp[1][n];
	}

	// 测试
	// 1.数字裂开方式
	public static int splitNumber(int N) {
		return f(1, N);
	}

	public static int f(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) {
			return 0;
		}
		int ways = 0;
		for (int i = pre; i <= rest; i++) {
			ways += f(i, rest - i);
		}
		return ways;
	}

	// 数字分裂dp
	public static int splitNumDP(int N) {
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}
		for (int pre = N - 1; pre > 0; pre--) {
			for (int rest = pre + 1; rest <= N; rest++) {
				dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
			}
		}
		return dp[1][N];
	}

	public static void main(String[] args) {
		int n = 30;
		System.out.println(ways1(n));
		System.out.println(ways2(n));
		System.out.println(ways3(n));
		System.out.println(splitNumber(n));
		System.out.println(splitNumDP(n));
	}

}
