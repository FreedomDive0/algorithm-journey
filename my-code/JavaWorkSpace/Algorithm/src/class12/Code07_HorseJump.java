package class12;

public class Code07_HorseJump {
	public static int jump(int a, int b, int k) {
		return process(0, 0, k, a, b);
	}

	public static int process(int x, int y, int rest, int a, int b) {
		if (x < 0 || x > 8 || y < 0 || y > 9) {
			return 0;
		}
		if (rest == 0) {
			return (x == a && y == b ? 1 : 0);
		}
		int ways = process(x + 1, y + 2, rest - 1, a, b);
		ways += process(x + 2, y + 1, rest - 1, a, b);
		ways += process(x + 1, y - 2, rest - 1, a, b);
		ways += process(x + 2, y - 1, rest - 1, a, b);
		ways += process(x - 1, y + 2, rest - 1, a, b);
		ways += process(x - 2, y + 1, rest - 1, a, b);
		ways += process(x - 1, y - 2, rest - 1, a, b);
		ways += process(x - 2, y - 1, rest - 1, a, b);
		return ways;
	}

	public static int dp(int a, int b, int k) {
		int[][][] dp = new int[9][10][k + 1];
		dp[a][b][0] = 1;
		for (int rest = 1; rest <= k; rest++) {
			for (int x = 0; x <= 8; x++) {
				for (int y = 0; y <= 9; y++) {
					dp[x][y][rest] = pickUp(dp, x + 1, y + 2, rest - 1);
					dp[x][y][rest] += pickUp(dp, x + 2, y + 1, rest - 1);
					dp[x][y][rest] += pickUp(dp, x + 1, y - 2, rest - 1);
					dp[x][y][rest] += pickUp(dp, x + 2, y - 1, rest - 1);
					dp[x][y][rest] += pickUp(dp, x - 1, y + 2, rest - 1);
					dp[x][y][rest] += pickUp(dp, x - 2, y + 1, rest - 1);
					dp[x][y][rest] += pickUp(dp, x - 1, y - 2, rest - 1);
					dp[x][y][rest] += pickUp(dp, x - 2, y - 1, rest - 1);
				}
			}
		}
		return dp[0][0][k];
	}

	public static int pickUp(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > 8 || y < 0 || y > 9) {
			return 0;
		}
		return dp[x][y][rest];
	}

	// 复习
	public static int Horse(int x, int y, int k) {
		if (x > 8 || x < 0 || y > 9 || y < 0 || k < 0) {
			return 0;
		}
		return processHJ(x, y, k, 0, 0);
	}

	public static int processHJ(int x, int y, int rest, int curX, int curY) {
		if (rest == 0) {
			return curX == x && curY == y ? 1 : 0;
		}
		if (curX > 8 || curX < 0 || curY > 9 || curY < 0) {
			return 0;
		}
		int res = 0;
		res += processHJ(x, y, rest - 1, curX + 1, curY + 2);
		res += processHJ(x, y, rest - 1, curX + 2, curY + 1);
		res += processHJ(x, y, rest - 1, curX + 1, curY - 2);
		res += processHJ(x, y, rest - 1, curX + 2, curY - 1);
		res += processHJ(x, y, rest - 1, curX - 1, curY + 2);
		res += processHJ(x, y, rest - 1, curX - 2, curY + 1);
		res += processHJ(x, y, rest - 1, curX - 1, curY - 2);
		res += processHJ(x, y, rest - 1, curX - 2, curY - 1);
		return res;
	}

	// 8.1马走日DP
	public static int HorseDP(int x, int y, int k) {
		if (x > 8 || x < 0 || y > 9 || y < 0 || k < 0) {
			return 0;
		}
		int[][][] dp = new int[9][10][k + 1];
		dp[x][y][0] = 1;
		for (int rest = 1; rest <= k; rest++) {
			for (int curX = 0; curX < 9; curX++) {
				for (int curY = 0; curY < 10; curY++) {
					dp[curX][curY][rest] += pick(rest - 1, curX + 1, curY + 2, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX + 2, curY + 1, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX + 1, curY - 2, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX + 2, curY - 1, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX - 1, curY + 2, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX - 2, curY + 1, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX - 1, curY - 2, dp);
					dp[curX][curY][rest] += pick(rest - 1, curX - 2, curY - 1, dp);
				}
			}
		}
		return dp[0][0][k];
	}

	public static int pick(int rest, int curX, int curY, int[][][] dp) {
		if (curX > 8 || curX < 0 || curY > 9 || curY < 0) {
			return 0;
		}
		return dp[curX][curY][rest];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(HorseDP(x, y, step));
		System.out.println(dp(x, y, step));
	}
}
