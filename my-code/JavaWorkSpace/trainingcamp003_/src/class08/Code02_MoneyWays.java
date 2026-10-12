package class08;

public class Code02_MoneyWays {
	public static int moneyWays(int[] arbitrary, int[] onlyone, int money) {
		if (money < 0) {
			return 0;
		}
		if ((arbitrary == null || arbitrary.length < 1) && (onlyone == null || onlyone.length < 1)) {
			return money == 0 ? 1 : 0;
		}
		int[][] arbitraryDP = getArbitraryDP(arbitrary, money);
		int[][] onlyoneDP = getOnlyoneDP(onlyone, money);
		if (arbitraryDP == null) {// 任意货币为空
			return onlyoneDP[onlyone.length - 1][money];
		}
		if (onlyoneDP == null) {// 纪念币为空
			return arbitraryDP[arbitraryDP.length - 1][money];
		}
		int ans = 0;
		for (int i = 0; i <= money; i++) {// 两种币都不为空
			ans += arbitraryDP[arbitraryDP.length][i] * onlyoneDP[onlyoneDP.length][money - i];
		}
		return ans;
	}

	// 任意使用货币的dp表
	public static int[][] getArbitraryDP(int[] arbitrary, int money) {
		if (arbitrary == null || arbitrary.length < 1) {
			return null;
		}
		int[][] dp = new int[arbitrary.length][money + 1];
		for (int i = 0; i < dp.length; i++) {// 第一列 0~i 搞定0元 不使用任何货币1种
			dp[i][0] = 1;
		}
		for (int i = arbitrary[0]; i <= money; i += arbitrary[0]) {
			dp[0][i] = 1;
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j <= money; j++) {
				dp[i][j] = dp[i - 1][j];
				dp[i][j] += j - arbitrary[i] >= 0 ? dp[i][j - arbitrary[i]] : 0;// 对于枚举行为的优化
			}
		}
		return dp;
	}

	// 纪念币的使用表
	public static int[][] getOnlyoneDP(int[] onlyone, int money) {
		if (onlyone == null || onlyone.length < 1) {
			return null;
		}
		int[][] dp = new int[onlyone.length][money + 1];
		for (int i = 0; i < dp.length; i++) {// 第一列 0~i 搞定0元 不使用任何货币1种
			dp[i][0] = 1;
		}
		if (onlyone[0] <= money) {
			dp[0][onlyone[0]] = 1;// 第一行 只使用1张第一种纪念币可以达到的面值
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j <= money; j++) {
				dp[i][j] = dp[i - 1][j];
				dp[i][j] += j - onlyone[i] >= 0 ? dp[i - 1][j - onlyone[i]] : 0;// 对于枚举行为的优化
			}
		}
		return dp;
	}

	public static void main(String[] args) {

	}
}
