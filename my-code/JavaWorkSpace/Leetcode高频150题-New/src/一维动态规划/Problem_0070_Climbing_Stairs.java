package 一维动态规划;

public class Problem_0070_Climbing_Stairs {
	public int climbStairs(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public int climbStairs1(int n) {
		if (n <= 0) {
			return 0;
		}
		return process(n);
	}

	public int process(int n) {
		if (n == 0) {
			return 1;
		}
		if (n < 0) {
			return 0;
		}
		return process(n - 1) + process(n - 2);
	}

}
