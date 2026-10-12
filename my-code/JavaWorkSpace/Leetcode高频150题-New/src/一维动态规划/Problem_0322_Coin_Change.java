package 一维动态规划;

import java.util.Arrays;

public class Problem_0322_Coin_Change {
	public int coinChange(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;
		for (int rest = 1; rest <= amount; rest++) {
			for (int index = 0; index < coins.length; index++) {
				if (coins[index] <= rest) {
					dp[rest] = Math.min(dp[rest], dp[rest - coins[index]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}
//	public int coinChange(int[] coins, int amount) {
//		int ans = process(coins, amount);
//		return ans == Integer.MAX_VALUE ? -1 : ans;
//	}
//
//	// 使用coins凑出rest最少需要几张
//	public int process(int[] coins, int rest) {
//		if (rest < 0) {
//			return -1;
//		}
//		if (rest == 0) {
//			return 0;
//		}
//		int min = Integer.MAX_VALUE;
//		for (int coin : coins) {
//			int res = coinChange(coins, rest - coin);
//			if (res >= 0 && res < min) {
//				min = res + 1;
//			}
//		}
//		return min;
//	}
}
