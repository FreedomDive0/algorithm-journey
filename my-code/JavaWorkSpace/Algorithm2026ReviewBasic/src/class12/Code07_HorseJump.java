package class12;

public class Code07_HorseJump {
	// 10*9
	// 0~9 y
	// 0~8 x 9列10行
	// 马走日 暴力递归
	public static int ways(int a, int b, int step) {
		return hJProcess(a, b, step, 0, 0);
	}

	public static int hJProcess(int a, int b, int step, int x, int y) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		if (step == 0) {
			return x == a && y == b ? 1 : 0;
		}
		int ways = 0;
		ways += hJProcess(a, b, step - 1, x + 1, y + 2);
		ways += hJProcess(a, b, step - 1, x + 2, y + 1);
		ways += hJProcess(a, b, step - 1, x + 1, y - 2);
		ways += hJProcess(a, b, step - 1, x + 2, y - 1);
		ways += hJProcess(a, b, step - 1, x - 1, y - 2);
		ways += hJProcess(a, b, step - 1, x - 2, y - 1);
		ways += hJProcess(a, b, step - 1, x - 1, y + 2);
		ways += hJProcess(a, b, step - 1, x - 2, y + 1);
		return ways;
	}

	// 动态规划
	public static int horseJumpDP(int a, int b, int step) {
		int[][][] dp = new int[10][9][step + 1];
		dp[a][b][0] = 1;
		for (int rest = 1; rest <= step; rest++) {
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 9; y++) {
					dp[x][y][rest] = getDpValue(dp, x + 1, y + 2, rest - 1) + getDpValue(dp, x + 2, y + 1, rest - 1)
							+ getDpValue(dp, x + 1, y - 2, rest - 1) + getDpValue(dp, x + 2, y - 1, rest - 1)
							+ getDpValue(dp, x - 1, y - 2, rest - 1) + getDpValue(dp, x - 2, y - 1, rest - 1)
							+ getDpValue(dp, x - 1, y + 2, rest - 1) + getDpValue(dp, x - 2, y + 1, rest - 1);
				}
			}
		}
		return dp[0][0][step];
	}

	public static int getDpValue(int[][][] dp, int x, int y, int step) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		return dp[x][y][step];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(ways(x, y, step));
		System.out.println(horseJumpDP(x, y, step));
	}
}
