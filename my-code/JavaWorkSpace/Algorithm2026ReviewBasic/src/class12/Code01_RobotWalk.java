package class12;

public class Code01_RobotWalk {
	// 机器人走
	public static int ways1(int N, int M, int P, int K) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		return rWProcess(N, M, K, P);
	}

	public static int rWProcess(int N, int cur, int K, int P) {
		if (K == 0) {
			return cur == P ? 1 : 0;
		}
		if (cur == 1) {
			return rWProcess(N, 2, K - 1, P);
		}
		if (cur == N) {
			return rWProcess(N, N - 1, K - 1, P);
		}
		return rWProcess(N, cur - 1, K - 1, P) + rWProcess(N, cur + 1, K - 1, P);
	}

	// 记忆化搜索
	public static int waysMemory(int N, int M, int P, int K) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		return rWMProcess(N, M, K, P, dp);
	}

	public static int rWMProcess(int N, int cur, int K, int P, int[][] dp) {
		if (dp[cur][K] != -1) {
			return dp[cur][K];
		}
		if (K == 0) {
			dp[cur][K] = cur == P ? 1 : 0;
			return dp[cur][K];
		}
		if (cur == 1) {
			dp[cur][K] = rWProcess(N, 2, K - 1, P);
			return dp[cur][K];
		}
		if (cur == N) {
			dp[cur][K] = rWProcess(N, N - 1, K - 1, P);
			return dp[cur][K];
		}
		dp[cur][K] = rWProcess(N, cur - 1, K - 1, P) + rWProcess(N, cur + 1, K - 1, P);
		return dp[cur][K];
	}

	// 动态规划Dynamic Programming
	public static int waysDP(int N, int M, int P, int K) {
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[][] dp = new int[N + 1][K + 1];
		dp[P][0] = 1;
		for (int col = 1; col <= K; col++) {
			for (int row = 1; row <= N; row++) {
				if (row == 1) {
					dp[row][col] = dp[2][col - 1];
				} else if (row == N) {
					dp[row][col] = dp[N - 1][col - 1];
				} else {
					dp[row][col] = dp[row + 1][col - 1] + dp[row - 1][col - 1];
				}
			}
		}
		return dp[M][K];
	}

	public static void main(String[] args) {
		System.out.println(ways1(7, 4, 5, 9));
		System.out.println(waysMemory(7, 4, 5, 9));
		System.out.println(waysDP(7, 4, 5, 9));
	}
}
