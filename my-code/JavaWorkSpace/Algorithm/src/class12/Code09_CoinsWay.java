package class12;

public class Code09_CoinsWay {
//	暴力递归
	public static int ways1(int[] coins, int aim) {
		if (coins == null || coins.length == 0) {
			return 0;
		}
		return process1(coins, 0, aim);
	}

	public static int process1(int[] coins, int index, int rest) {
//		if(rest<0) {循环条件限定此情况不会出现
//			return 0;
//		}
		if (index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		// 枚举每一种情况
		for (int num = 0; num * coins[index] <= rest; num++) {
			ways += process1(coins, index + 1, rest - num * coins[index]);
		}
		return ways;
	}

//	记忆搜索
	public static int ways2(int[] coins, int aim) {
		if (coins == null || coins.length == 0) {
			return 0;
		}
		int dp[][] = new int[coins.length + 1][aim + 1];
		for (int i = 0; i <= coins.length; i++) {
			for (int j = 0; j <= aim; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(coins, 0, aim, dp);
	}

	public static int process2(int[] coins, int index, int rest, int[][] dp) {
		if (dp[index][rest] != -1) {
			return dp[index][rest];
		}
		if (index == coins.length) {
			dp[index][rest] = rest == 0 ? 1 : 0;
			return dp[index][rest];
		}
		dp[index][rest] = 0;
		for (int num = 0; num * coins[index] <= rest; num++) {
			dp[index][rest] += process1(coins, index + 1, rest - num * coins[index]);
		}
		return dp[index][rest];
	}

//	经典动态优化
	public static int ways3(int[] coins, int aim) {
		if (coins == null || coins.length == 0) {
			return 0;
		}
		int N = coins.length;
		int dp[][] = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int num = 0; num * coins[index] <= rest; num++) {
					ways += dp[index + 1][rest - num * coins[index]];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}

//	枚举优化
	public static int ways4(int[] coins, int aim) {
		if (coins == null || coins.length == 0) {
			return 0;
		}
		int N = coins.length;
		int dp[][] = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (coins[index] <= rest) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
			}
		}
		return dp[0][aim];
	}

	// 复习
	// 10.硬币凑钱
	public static int Coin(int[] coin, int target) {
		if (coin == null || coin.length < 1 || target < 0) {
			return 0;
		}
		return processCW(coin, 0, target);
	}

	public static int processCW(int[] coin, int index, int rest) {
		if (index == coin.length) {
			return rest == 0 ? 1 : 0;
		}
		if (rest < 0) {
			return 0;
		}
		int ways = 0;
		for (int n = 0; n * coin[index] <= rest; n++) {
			ways += processCW(coin, index + 1, rest - n * coin[index]);
		}
		return ways;
	}

	// 10.1硬币凑钱DP
	public static int CoinDP(int[] coin, int target) {
		if (coin == null || coin.length < 1 || target < 0) {
			return 0;
		}
		int N = coin.length;
		int[][] dp = new int[N + 1][target + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= target; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - coin[index] >= 0) {
					dp[index][rest] += dp[index][rest - coin[index]];
				}
				// 观察图形结构优化
//				for (int n = 0; n * coin[index] <= rest; n++) {
//					dp[index][rest] += dp[index + 1][rest - n * coin[index]];
//				}
			}
		}
		return dp[0][target];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 10, 50, 100 };
		int sum = 1000;
		System.out.println(CoinDP(arr, sum));
		System.out.println(ways2(arr, sum));
		System.out.println(ways3(arr, sum));
		System.out.println(ways4(arr, sum));
	}
}
