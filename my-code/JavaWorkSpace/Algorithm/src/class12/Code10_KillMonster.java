package class12;

public class Code10_KillMonster {
	public static Double kill1(int N, int M, int K) {
		int[][] dp = new int[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times < K; times++) {
			dp[times][0] = (int) Math.pow(M + 1, times);
			for (int hp = 1; hp < N; hp++) {
				int ways = 0;
				for (int i = 0; i <= M; i++) {
					if (hp - i >= 0) {
						ways += dp[times][hp - i];
					} else {
						ways += (int) Math.pow(M + 1, times - 1);
					}
				}
				dp[times][hp] = ways;
			}
		}
		// 测试
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		int all = (int) Math.pow(M + 1, K);
		return (double) ((double) dp[K][N] / (double) all);
	}

	// 优化
	public static Double kill2(int N, int M, int K) {
		int[][] dp = new int[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times < K; times++) {
			dp[times][0] = (int) Math.pow(M + 1, times);
			for (int hp = 1; hp < N; hp++) {
				dp[times][hp] = dp[times][hp - 1] - dp[times - 1][hp - 1 - M <= 0 ? 0 : hp - M] + dp[times - 1][hp];
			}
		}
		int all = (int) Math.pow(M + 1, K);
		return (double) ((double) dp[K][N] / (double) all);
	}

	// 复习
	// 11.怪兽N血量 M最大伤害 K攻击次数
	public static double killMonster(int N, int M, int K) {
		if (N <= 0 || M <= 0 || K <= 0) {
			return -1;
		}
		int res = processKM(M, N, K);
		return (double) res / (double) Math.pow(M + 1, K);
	}

	public static int processKM(int M, int restN, int restK) {
		if (restN <= 0) {
			return (int) Math.pow(M + 1, restK);
		}
		if (restK == 0) {
			return restN <= 0 ? 1 : 0;
		}

		int res = 0;
		for (int damage = 0; damage <= M; damage++) {
			res += processKM(M, restN - damage, restK - 1);
		}
		return res;
	}

	// 11.怪兽N血量 M最大伤害 K攻击次数DP
	public static double killMonsterDP(int N, int M, int K) {
		if (N <= 0 || M <= 0 || K <= 0) {
			return -1;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= K; i++) {
			dp[0][i] = 1;
		}
		for (int restN = 1; restN <= N; restN++) {
			for (int restK = 1; restK <= K; restK++) {
				for (int damage = 0; damage <= M && restN - damage >= 0; damage++) {
					dp[restN][restK] += dp[restN - damage][restK - 1];
				}
			}
		}
		// 测试
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		return (double) dp[N][K] / (double) Math.pow(M + 1, K);
	}

	public static void main(String[] args) {
		System.out.println(killMonster(5, 1, 5));
		System.out.println(killMonsterDP(5, 1, 5));
	}
}
