package class02;

public class Code05_DungeonGame {
	// 暴力递归
	public static int dungeonGame(int[][] map) {
		if (map == null || map.length < 1 || map[0].length < 1) {
			return 0;
		}
		return process(map, 0, 0, map.length, map[0].length);
	}

	public static int process(int[][] map, int i, int j, int N, int M) {
		if (i == N - 1 && j == M - 1) {
			return map[i][j] < 0 ? 1 - map[i][j] : 1;
		}
		if (i == N - 1) {
			int next = process(map, i, j + 1, N, M);
			if (map[i][j] <= 0) {
				return -map[i][j] + next;// 隐含条件next 下一个格子可以完成 意味着dp[i][j]完成了还能完成下一个格子 所以不需要考虑经过该格子值>=1
			} else {
				return map[i][j] < next ? next - map[i][j] : 1;
			}
		}
		if (j == M - 1) {
			int next = process(map, i + 1, j, N, M);
			if (map[i][j] <= 0) {
				return -map[i][j] + next;
			} else {
				return map[i][j] < next ? next - map[i][j] : 1;
			}
		}
		int right = process(map, i, j + 1, N, M);
		int down = process(map, i + 1, j, N, M);
		int min = Math.min(right, down);
		if (map[i][j] <= 0) {
			return -map[i][j] + min;
		} else {
			return map[i][j] < min ? min - map[i][j] : 1;
		}
	}

	// 动态规划
	public static int dp(int[][] map) {
		if (map == null || map.length < 1 || map[0].length < 1) {
			return 0;
		}
		int N = map.length;
		int M = map[0].length;
		int[][] dp = new int[N][M];
		dp[N - 1][M - 1] = map[N - 1][M - 1] < 0 ? 1 - map[N - 1][M - 1] : 1;
		for (int j = M - 2; j >= 0; j--) {// 填写最后一行
			dp[N - 1][j] = Math.max(dp[N - 1][j + 1] - map[N - 1][j], 1);// 观察法
		}
		for (int i = N - 2; i >= 0; i--) {
			dp[i][M - 1] = Math.max(dp[i + 1][M - 1] - map[i][M - 1], 1);
			for (int j = M - 2; j >= 0; j--) {
				int right = Math.max(dp[i][j + 1] - map[i][j], 1);
				int down = Math.max(dp[i + 1][j] - map[i][j], 1);
				dp[i][j] = Math.min(right, down);
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] map = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 }, };
		System.out.println(dungeonGame(map));
		System.out.println(dp(map));
	}
}
