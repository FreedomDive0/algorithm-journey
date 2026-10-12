package 动态规划专题;

public class LC剑指Offer_10II青蛙跳台阶问题 {
	public int numWays(int n) {
		int step1 = 1;// 优化只需要两个位置
		int step2 = 1;
		int sum = 0;
		for (int i = 2; i < n; i++) {
			sum = step1 + step2;
			step1 = step2;
			step2 = sum;
		}
		return sum;
	}

	public int numWays1(int n) {
		if (n <= 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
		}
		return dp[n];
	}

	public int process(int rest) {
		if (rest == 0) {
			return 1;
		}
		if (rest < 0) {
			return 0;
		}
		int ways = 0;
		ways += process(rest - 1);
		ways += process(rest - 2);
		return ways;
	}
}
