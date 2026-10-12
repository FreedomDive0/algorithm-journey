package class12;

public class Code01_RobotWalk {
	// 1~N个格子 M初始位置 能走K步 P目标位置
	// 1.暴力递归
	public static int ways1(int N, int M, int P, int K) {
		if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
			return 0;
		}
		return processRW(N, M, K, P);
	}

	public static int processRW(int N, int cur, int K, int P) {
		if (K == 0) {
			return cur == P ? 1 : 0;
		}
		if (cur == 1) {
			return processRW(N, 2, K - 1, P);
		}
		if (cur == N) {
			return processRW(N, N - 1, K - 1, P);
		}
		return processRW(N, cur - 1, K - 1, P) + processRW(N, cur + 1, K - 1, P);
	}

	// 2.记忆化搜索 省略添加dp[][]存储 计算记录 节省时间
	// 3.动态规划
	public static int robatWalkDP(int N, int M, int P, int K) {
		if (N < 1 || M < 1 || K < 1 || P < 1 || M > N || P > N) {
			return 0;
		}
		int[][] dp = new int[N + 1][K + 1];
		dp[P][0] = 1;
		for (int j = 1; j <= K; j++) {
			for (int i = 1; i <= N; i++) {
				if (i == 1) {
					dp[i][j] = dp[2][j - 1];
				} else if (i == N) {
					dp[i][j] = dp[N - 1][j - 1];
				} else {
					dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
				}
			}
		}
		return dp[M][K];
	}

	public static void main(String[] args) {
		System.out.println(ways1(7, 4, 5, 9));
		System.out.println(robatWalkDP(7, 4, 5, 9));
	}
}
