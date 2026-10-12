package class07;

public class Code04_SubsquenceMaxModM {
	// arr[]非负 子序列累加和%m 的最大值
	public static int subMaxMod(int[] arr, int m) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int n = arr.length;
		boolean[][] dp = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			dp[i][0] = true;// 不使用数字 余数都是0
		}
		dp[0][arr[0] % m] = true;// 第一个数字能搞定的数字
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = dp[i - 1][j];// 如果不使用i位置数就能搞定 直接搞定
				int cur = arr[i] % m;// 当前位置的数字%m 的余数
				if (cur <= j) {// 余数<=j 可以凑
					dp[i][j] = dp[i - 1][j - cur] | dp[i][j];//
				}
				if (cur > j) {// 余数>j 需要凑大一轮 即j + m == cur + ?
					dp[i][j] = dp[i - 1][m + j - cur] | dp[i][j];
				}
			}
		}
		int res = 0;
		for (int j = m - 1; j >= 0; j--) {
			if (dp[n - 1][j]) {
				res = j;
				break;
			}
		}
		return res;
	}
}
