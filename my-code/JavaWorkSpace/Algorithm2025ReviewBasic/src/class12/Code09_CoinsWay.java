package class12;

public class Code09_CoinsWay {
	// arr中都是正数且无重复值，返回组成aim的方法数
	public static int ways1(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 0) {
			return 0;
		}
		return processC(arr, 0, aim);
	}

	public static int processC(int[] arr, int idx, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (idx == arr.length) {
			return 0;
		}
		int ways = 0;
		for (int count = 0; count * arr[idx] <= rest; count++) {
			ways += processC(arr, idx + 1, rest - count * arr[idx]);
		}
		return ways;
	}

	// 动态规划
	public static int wayDP(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 1; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] += dp[i][j - arr[i]];// 把枚举行为优化了 通过观察经典动态规划构建过程发现可以化简掉遍历过程
				}
			}
		}
		return dp[0][aim];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 10, 50, 100 };
		int sum = 1000;
		System.out.println(ways1(arr, sum));
		System.out.println(wayDP(arr, sum));
	}
}
