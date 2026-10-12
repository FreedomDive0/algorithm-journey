package 动态规划专题;

public class LC664_奇怪的打印机 {
	public int strangePrinter(String s) {
		int N = s.length();
		char[] arr = s.toCharArray();
		int[][] dp = new int[N][N];
		dp[N - 1][N - 1] = 1;
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = arr[i] == arr[i + 1] ? 1 : 2;// 小优化
		}
		for (int L = N - 3; L >= 0; L--) {// 画图建立空间感
			for (int R = L + 2; R < N; R++) {
				dp[L][R] = R - L + 1;
				for (int k = L + 1; k <= R; k++) {
					dp[L][R] = Math.min(dp[L][R], dp[L][k - 1] + dp[k][R] - (arr[L] == arr[k] ? 1 : 0));
				}
			}
		}
		return dp[0][N - 1];
	}

	public int process(String s, int L, int R) {// 打印L...R范围上的字符串需要的最少次数
		// 规定首先打印L位置 ∵L在边界 首先打印L和最佳次数打印L的顺序颠倒不会影响最终结果
		if (L == R) {
			return 1;
		}
		int ans = R - L + 1;
		for (int k = L + 1; k <= R; k++) {// 根据上述打印规定 - s.charAt(L) == s.charAt(k) ? 1 : 0 两边都首先打印第一位 ∴相同的话可以一起打印
			ans = Math.min(ans, process(s, L, k - 1) + process(s, k, R) - s.charAt(L) == s.charAt(k) ? 1 : 0);
		}
		return ans;
	}
}
