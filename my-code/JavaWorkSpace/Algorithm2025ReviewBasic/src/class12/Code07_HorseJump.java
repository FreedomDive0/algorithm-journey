package class12;

public class Code07_HorseJump {
	// 马走日
	// 1.暴力递归
	public static int horseJumpDP(int a, int b, int step) {
		if (step < 1 || a < 0 || a >= 9 || b < 0 || b >= 10) {
			return 0;
		}
		return processHJ(0, 0, a, b, step);
	}

	public static int processHJ(int x, int y, int a, int b, int rest) {
		if (rest == 0) {
			return x == a && y == b ? 1 : 0;
		}
		if (x < 0 || x >= 9 || y < 0 || y >= 10) {
			return 0;
		}
		int ways = processHJ(x + 1, y + 2, a, b, rest - 1);
		ways += processHJ(x + 1, y - 2, a, b, rest - 1);
		ways += processHJ(x + 2, y + 1, a, b, rest - 1);
		ways += processHJ(x + 2, y - 1, a, b, rest - 1);
		ways += processHJ(x - 1, y + 2, a, b, rest - 1);
		ways += processHJ(x - 1, y - 2, a, b, rest - 1);
		ways += processHJ(x - 2, y + 1, a, b, rest - 1);
		ways += processHJ(x - 2, y - 1, a, b, rest - 1);
		return ways;
	}

	// 2.动态规划
	public static int waysDP(int a, int b, int step) {
		if (step < 1 || a < 0 || a >= 9 || b < 0 || b >= 10) {
			return 0;
		}
		int[][][] dp = new int[9][10][step + 1];
		dp[a][b][0] = 1;
		for (int rest = 1; rest <= step; rest++) {
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 10; y++) {
					dp[x][y][rest] = getDPValue(dp, x + 1, y + 2, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x + 1, y - 2, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x + 2, y + 1, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x + 2, y - 1, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x - 1, y + 2, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x - 1, y - 2, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x - 2, y + 1, rest - 1);
					dp[x][y][rest] += getDPValue(dp, x - 2, y - 1, rest - 1);
				}
			}
		}
		return dp[0][0][step];
	}

	// 515813
	public static int getDPValue(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x >= 9 || y < 0 || y >= 10) {
			return 0;
		}
		return dp[x][y][rest];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(horseJumpDP(x, y, step));
		System.out.println(waysDP(x, y, step));
	}
}
