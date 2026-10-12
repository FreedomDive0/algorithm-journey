package class08;

public class Code04_MoneyProblem {
	public static int minMoney(int[] d, int[] p) {
		if (d == null || p == null || d.length < 1 || p.length < 1) {
			return -1;
		}
		return process(d, p, 0, 0);
	}

	public static int process(int[] d, int[] p, int index, int ability) {
		if (index == d.length) {
			return 0;
		}
		if (ability < d[index]) {
			return p[index] + process(d, p, index + 1, ability + d[index]);
		} else {
			return Math.min(process(d, p, index + 1, ability), p[index] + process(d, p, index + 1, ability + d[index]));
		}
	}

	// 根据数据状况建立不同dp表
	// 钱数范围小做列 能力范围小做列
	public static int dpways(int[] d, int[] p) {
		if (d == null || p == null || d.length < 1 || p.length < 1) {
			return -1;
		}
		int sum = 0;
		int N = d.length;
		for (int i = 0; i < N; i++) {
			sum += p[i];
		}
		int[][] dp = new int[N][sum + 1];// i，j为表示到i号怪兽使用金钱j 能达到最大能力值且能通过该位置怪兽
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= sum; j++) {
				dp[i][j] = -1;// 钱使用不完为无效位置-1
			}
		}
		dp[0][p[0]] = d[0];// 第一行第一列赋值
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= sum; j++) {
				// 两种情况
				if (dp[i - 1][j] >= d[i]) {// 1.不获得该位置怪兽能力
					dp[i][j] = dp[i - 1][j];
				}
				if (j - p[i] >= 0 && dp[i - 1][j - p[i]] != -1) {// 2.获得该位置怪兽能力
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - p[i]] + d[i]);
				}
			}
		}
		int min = sum;
		for (int i = 0; i <= sum; i++) {
			if (dp[N - 1][i] != -1) {// 最后一行第一个不为0位置为答案
				min = i;
				break;
			}
		}
		return min;
	}
}
