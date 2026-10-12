package class12;

public class Code09_CoinsWay {
	// arr中都是正数且无重复值，返回组成aim的方法数
	// 1.暴力递归
	public static int ways1(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 0) {
			return 0;
		}
		return cProcess(arr, 0, aim);
	}

	public static int cProcess(int[] arr, int idx, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (idx == arr.length) {
			return 0;
		}
		int ways = 0;
		for (int count = 0; count * arr[idx] <= rest; count++) {
			ways += cProcess(arr, idx + 1, rest - count * arr[idx]);
		}
		return ways;
	}

	public static int waysDP1(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 1; j <= aim; j++) {
				for (int count = 0; count * arr[i] <= j; count++) {
					dp[i][j] += dp[i + 1][j - count * arr[i]];
				}
			}
		}
		return dp[0][aim];
	}

	public static int waysDP2(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 1; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0);// 发现遍历可以优化
			}
		}
		return dp[0][aim];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 10, 50, 100 };
		int sum = 1000;
		System.out.println(ways1(arr, sum));
		System.out.println(waysDP1(arr, sum));
		System.out.println(waysDP2(arr, sum));
//		System.out.println(ways4(arr, sum));
	}
}
