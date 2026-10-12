package class22;

public class Code01_KillMonster {

	public static double right(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long kill = process(K, M, N);
		return (double) ((double) kill / (double) all);
	}

	// 怪兽还剩hp点血
	// 每次的伤害在[0~M]范围上
	// 还有times次可以砍
	// 返回砍死的情况数！
	public static long process(int times, int M, int hp) {
		if (times == 0) {
			return hp <= 0 ? 1 : 0;
		}
		if (hp <= 0) {
			return (long) Math.pow(M + 1, times);
		}
		long ways = 0;
		for (int i = 0; i <= M; i++) {
			ways += process(times - 1, M, hp - i);
		}
		return ways;
	}

	public static double dp1(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				long ways = 0;
				for (int i = 0; i <= M; i++) {
					if (hp - i >= 0) {
						ways += dp[times - 1][hp - i];
					} else {
						ways += (long) Math.pow(M + 1, times - 1);
					}
				}
				dp[times][hp] = ways;
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
	}

	public static double dp2(int N, int M, int K) {
		if (N < 1 || M < 1 || K < 1) {
			return 0;
		}
		long all = (long) Math.pow(M + 1, K);
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int times = 1; times <= K; times++) {
			dp[times][0] = (long) Math.pow(M + 1, times);
			for (int hp = 1; hp <= N; hp++) {
				dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
				if (hp - 1 - M >= 0) {
					dp[times][hp] -= dp[times - 1][hp - 1 - M];
				} else {
					dp[times][hp] -= Math.pow(M + 1, times - 1);
				}
			}
		}
		long kill = dp[K][N];
		return (double) ((double) kill / (double) all);
	}

	// 复习
	// 11.怪兽N血量 M最大伤害 K攻击次数
	public static double killMonster(int N, int M, int K) {
		if (N <= 0 || M <= 0 || K <= 0) {
			return 0;
		}
		long res = processKM(M, N, K);
		return (double) ((double) res / (double) Math.pow(M + 1, K));
	}

	public static long processKM(int M, int restN, int restK) {
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
			return 0;
		}
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int restK = 1; restK <= K; restK++) {
			dp[restK][0] = (int) Math.pow(M + 1, restK);
			for (int restN = 1; restN <= N; restN++) {
				int res = 0;
				for (int damage = 0; damage <= M; damage++) {
					if (restN - damage <= 0) {// 补全数组外的情况 process的过程
						res += (int) Math.pow(M + 1, restK - 1);
					} else {
						res += dp[restK - 1][restN - damage];
					}
				}
				dp[restK][restN] = res;
			}
		}
		return (double) ((double) dp[K][N] / (double) Math.pow(M + 1, K));
	}

	// 11.2怪兽N血量 M最大伤害 K攻击次数DP优化
	public static double killMonsterDP1(int N, int M, int K) {
		if (N <= 0 || M <= 0 || K <= 0) {
			return 0;
		}
		long[][] dp = new long[K + 1][N + 1];
		dp[0][0] = 1;
		for (int restK = 1; restK <= K; restK++) {
			dp[restK][0] = (int) Math.pow(M + 1, restK);
			for (int restN = 1; restN <= N; restN++) {
				dp[restK][restN] = dp[restK][restN - 1] + dp[restK - 1][restN];
				if (restN - 1 - M >= 0) {// 补全数组外的情况 process的过程
					dp[restK][restN] -= dp[restK - 1][restN - 1 - M];
				} else {
					dp[restK][restN] -= (int) Math.pow(M + 1, restK - 1);
				}
			}
		}
		return (double) ((double) dp[K][N] / (double) Math.pow(M + 1, K));
	}

	public static void main(String[] args) {
		int NMax = 10;
		int MMax = 10;
		int KMax = 10;
		int testTime = 200;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int N = (int) (Math.random() * NMax);
			int M = (int) (Math.random() * MMax);
			int K = (int) (Math.random() * KMax);
			double ans1 = dp2(N, M, K);
			double ans2 = killMonster(N, M, K);
			double ans3 = killMonsterDP1(N, M, K);
			if (ans1 != ans3) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
