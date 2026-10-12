package 动态规划专题;

public class LC518_零钱兑换II {
	public int change(int amount, int[] coins) {
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		dp[n][0] = 1;
		for (int index = n - 1; index >= 0; index--) {
			for (int rest = 0; rest <= amount; rest++) {
				dp[index][rest] = dp[index + 1][rest];// 优化 将之前循环和拆分为前一项与下面一项的和
				if (rest >= coins[index]) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
			}
		}
		return dp[0][amount];
	}

	public int change1(int amount, int[] coins) {// 暴力递归尝试
		return process(coins, 0, amount);
	}

	public int process(int[] coins, int index, int rest) {// 用到index硬币
		if (index == coins.length) {
			if (rest == 0) {
				return 1;
			}
			return 0;
		}
		int ways = 0;
		for (int i = 0; i * coins[index] <= rest; i++) {// 把所有能用的情况统计
			ways += process(coins, index + 1, rest - i * coins[i]);
		}
		return ways;
	}
}
