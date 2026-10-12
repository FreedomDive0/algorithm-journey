package class12;

import java.util.HashMap;

public class Review1 {
	// 1.机器人移动
	// (1)暴力递归
	public static int robotWalk(int N, int M, int P, int K) {
		return processRW(N, M, P, K);
	}

	public static int processRW(int N, int cur, int P, int rest) {
		if (rest == 0) {
			return cur == P ? 1 : 0;
		}
		if (cur == 1) {
			return processRW(N, 2, P, rest - 1);
		}
		if (cur == N) {
			return processRW(N, N - 1, P, rest - 1);
		}
		return processRW(N, cur + 1, P, rest - 1) + processRW(N, cur - 1, P, rest - 1);
	}

	// (2)动态规划
	public static int robotWalkDP(int N, int M, int P, int K) {
		int[][] dp = new int[N + 1][K + 1];
		dp[P][0] = 1;
		for (int col = 1; col <= K; col++) {
			for (int row = 1; row <= N; row++) {
				if (row == 1) {
					dp[row][col] = dp[row + 1][col - 1];
				} else if (row == N) {
					dp[row][col] = dp[N - 1][col - 1];
				} else {
					dp[row][col] = dp[row + 1][col - 1] + dp[row - 1][col - 1];
				}
			}
		}
		return dp[M][K];
	}

	// 测试
	// 2.货币凑钱
	public static int coinsWay(int[] coins, int aim) {
		return processCW(coins, aim, 0);
	}

	public static int processCW(int[] coins, int rest, int index) {
		if (rest <= 0) {
			return 1;
		}
		if (index == coins.length) {
			return 0;
		}
		int res = 0;
		for (int i = 0; coins[index] * i <= rest; i++) {
			res += processCW(coins, rest - coins[index] * i, index + 1);
		}
		return res;
	}

	// (2)dp
	public static int coinsWayDP(int[] coins, int aim) {
		int n = coins.length;
		int[][] dp = new int[n + 1][aim + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int row = n - 1; row >= 0; row--) {
			for (int col = 1; col <= aim; col++) {
				dp[row][col] = dp[row + 1][col];// 肯定存在的
				if (coins[row] <= col) {// 可能有的
					dp[row][col] += dp[row][col - coins[row]];
				}
			}
		}
		return dp[0][aim];
	}

	// 3.贴纸问题
	public static int stickersToWord(String[] stickers, String word) {
		int n = stickers.length;
		int[][] strickerMap = new int[n][26];
		for (int i = 0; i < n; i++) {
			for (char c : stickers[i].toCharArray()) {
				strickerMap[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<String, Integer>();
		dp.put("", 0);
		processSTW(strickerMap, word, dp);
		return dp.get(word);
	}

	public static int processSTW(int[][] strickerMap, String rest, HashMap<String, Integer> dp) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		int[] tmap = new int[26];
		char[] target = rest.toCharArray();
		for (char c : target) {
			tmap[c - 'a']++;
		}
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < strickerMap.length; i++) {
			if (strickerMap[i][target[0] - 'a'] == 0) {// 因为贴纸可以剪开 所以先处理谁都无所谓 为了防止出现无效贴纸导致的栈溢出 每次选取贴纸先让贴纸处理目标字符串的第一个字符
														// 再去处理剩下的部分
				continue;
			}
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < tmap.length; j++) {
				for (int k = 0; k < Math.max(0, tmap[j] - strickerMap[i][j]); k++) {
					sb.append((char) ('a' + j));// 注意添加类型转换 低转高自动进行
				}
			}
			int next = processSTW(strickerMap, sb.toString(), dp);
			if (next != -1) {
				res = Math.min(res, next + 1);
			}
		}
		dp.put(rest, res == Integer.MAX_VALUE ? -1 : res);
		return dp.get(rest);
	}

	// 4.最常公子序列长度
	public static int PalindromeSubsequenceDP(String s1, String s2) {
		int[][] dp = new int[s1.length()][s2.length()];
		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();
		dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
		for (int i = 1; i < s1.length(); i++) {
			dp[i][0] = Math.max(dp[i - 1][0], ch1[i] == ch2[0] ? 1 : 0);
		}
		for (int i = 1; i < s2.length(); i++) {
			dp[0][i] = Math.max(dp[0][i - 1], ch1[0] == ch2[i] ? 1 : 0);
		}
		for (int i = 1; i < ch1.length; i++) {
			for (int j = 1; j < ch2.length; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);// 上次判断中包含了dp[i-1][j-1]
				if (ch1[i] == ch2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[s1.length() - 1][s2.length() - 1];
	}

	// 5.咖啡机问题
	public static int coffee(int[] drinks, int a, int b) {// a晾干的时间 b洗干净时间
		if (a <= b) {
			return drinks[drinks.length - 1] + a;
		}
		return processCF(drinks, 0, 0, a, b);
	}

	public static int processCF(int[] drinks, int washLine, int index, int a, int b) {
		if (index == drinks.length - 1) {
			return Math.min(Math.max(washLine, drinks[index]) + b, drinks[index] + a);// 清洗所有咖啡杯最长的时间不会超过最后一杯晾干
		}
		int p1 = drinks[index] + a;
		int p1Next = processCF(drinks, washLine, index + 1, a, b);
		p1 = Math.max(p1, p1Next);
		int p2 = processCF(drinks, Math.max(washLine, drinks[index]) + b, index + 1, a, b);
		return Math.min(p1, p2);
	}

	// 5.1 DP
	public static int coffeeDP(int[] drinks, int a, int b) {
		if (a <= b) {
			return drinks[drinks.length - 1] + a;
		}
		int washLimit = 0;
		int n = drinks.length;
		for (int i = 0; i < drinks.length; i++) {
			washLimit = Math.max(washLimit, drinks[i]) + b;
		}
		int[][] dp = new int[n][washLimit + 1];
		for (int washLine = 0; washLine <= washLimit; washLine++) {
			dp[n - 1][washLine] = Math.min(Math.max(washLine, drinks[n - 1]) + b, drinks[n - 1] + a);
		}
		for (int index = n - 2; index >= 0; index--) {
			for (int washLine = 0; washLine < washLimit; washLine++) {
				int p1 = Math.max(drinks[index] + a, dp[index + 1][washLine]);
				int p2 = Integer.MAX_VALUE;
				int washTime = Math.max(washLine, drinks[index]) + b;
				if (washTime <= washLimit) {// washTime因为细粒度的划分有可能出现超过washLimit的限制
					p2 = dp[index + 1][washTime];
				}
				dp[index][washLine] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	// 6.马走日
	public static int horseJump(int i, int j, int k) {
		if (i < 0 || i > 8 || j < 0 || j > 9 || k < 0) {
			return 0;
		}
		return processHJ(i, j, 0, 0, k);
	}

	public static int processHJ(int i, int j, int curI, int curJ, int rest) {
		if (rest == 0) {
			return curI == i && curJ == j ? 1 : 0;
		}
		if (curI < 0 || curI > 8 || curJ < 0 || curJ > 9) {
			return 0;
		}
		int res = 0;
		res += processHJ(i, j, curI + 1, curJ + 2, rest - 1);
		res += processHJ(i, j, curI + 2, curJ + 1, rest - 1);
		res += processHJ(i, j, curI + 1, curJ - 2, rest - 1);
		res += processHJ(i, j, curI + 2, curJ - 1, rest - 1);
		res += processHJ(i, j, curI - 1, curJ + 2, rest - 1);
		res += processHJ(i, j, curI - 2, curJ + 1, rest - 1);
		res += processHJ(i, j, curI - 1, curJ - 2, rest - 1);
		res += processHJ(i, j, curI - 2, curJ - 1, rest - 1);
		return res;
	}

	// 6.1 马走日DP
	public static int horseJumpDP(int i, int j, int k) {
		if (i < 0 || i > 8 || j < 0 || j > 9 || k < 0) {
			return 0;
		}
		int[][][] dp = new int[k + 1][9][10];
		dp[0][i][j] = 1;
		for (int rest = 1; rest <= k; rest++) {
			for (int curI = 0; curI < 9; curI++) {
				for (int curJ = 0; curJ < 10; curJ++) {
					dp[rest][curI][curJ] = getValue1(dp, curI + 1, curJ + 2, rest - 1)
							+ getValue1(dp, curI + 2, curJ + 1, rest - 1) + getValue1(dp, curI + 1, curJ - 2, rest - 1)
							+ getValue1(dp, curI + 2, curJ - 1, rest - 1) + getValue1(dp, curI - 1, curJ + 2, rest - 1)
							+ getValue1(dp, curI - 2, curJ + 1, rest - 1) + getValue1(dp, curI - 1, curJ - 2, rest - 1)
							+ getValue1(dp, curI - 2, curJ - 1, rest - 1);
				}
			}
		}
		return dp[k][0][0];
	}

	public static int getValue1(int[][][] dp, int i, int j, int rest) {
		if (i < 0 || i > 8 || j < 0 || j > 9) {
			return 0;
		}
		return dp[rest][i][j];
	}
}
