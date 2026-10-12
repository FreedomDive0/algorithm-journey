package class12;

import java.util.HashMap;

public class Review {
	// 1.斐波那契数列
	public static int F(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return F(n - 1) + F(n - 2);
	}

	// 2.机器人行走问题
	public static int RobotW(int N, int M, int P, int K) {
		if (K < 1 || P > N || P < 1 || M > N || M < 1 || N < 2) {
			return 0;
		}
		return processRW(N, P, M, K);
	}

	public static int processRW(int N, int P, int cur, int rest) {
		if (rest == 0) {
			return cur == P ? 1 : 0;
		}
		if (cur == 1) {
			return processRW(N, P, 2, rest - 1);
		}
		if (cur == N) {
			return processRW(N, P, N - 1, rest - 1);
		}
		return processRW(N, P, cur - 1, rest - 1) + processRW(N, P, cur + 1, rest - 1);
	}

	// 2.1动态规划1 记忆化搜索
	public static int RobotDP1(int N, int M, int P, int K) {
		if (K < 1 || P > N || P < 1 || M > N || M < 1 || N < 2) {
			return 0;
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		processRW1(N, P, M, K, dp);
		return dp[M][K];
	}

	public static int processRW1(int N, int P, int cur, int rest, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		if (rest == 0) {
			dp[cur][rest] = cur == P ? 1 : 0;
			return dp[cur][rest];
		}
		if (cur == 1) {
			dp[cur][rest] = processRW(N, P, 2, rest - 1);
			return dp[cur][rest];
		}
		if (cur == N) {
			dp[cur][rest] = processRW(N, P, N - 1, rest - 1);
			return dp[cur][rest];
		}
		dp[cur][rest] = processRW(N, P, cur - 1, rest - 1) + processRW(N, P, cur + 1, rest - 1);
		return dp[cur][rest];
	}

	// 2.2动态规划2 建立结构化结果
	public static int RobotDP2(int N, int M, int P, int K) {
		if (K < 1 || P > N || P < 1 || M > N || M < 1 || N < 2) {
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
					dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
				}
			}
		}
		return dp[M][K];
	}

	// 3.贴纸问题
	public static int Stickers(String[] stricker, String target) {
		if (stricker == null || target == null) {
			return 0;
		}
		int[][] map = new int[stricker.length][26];
		for (int i = 0; i < map.length; i++) {// 将贴纸变为数组形式方便使用
			for (char c : stricker[i].toCharArray()) {
				map[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();// 因为参数为字符串形式数组不好表达
		dp.put("", 0);
		processST(map, target, dp);
		return dp.get(target);
	}

	public static int processST(int[][] map, String rest, HashMap<String, Integer> dp) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		int res = Integer.MAX_VALUE;
		int[] target = new int[26];
		for (int c : rest.toCharArray()) {
			target[c - 'a']++;
		}
		for (int[] stricker : map) {
			if (stricker[rest.toCharArray()[0] - 'a'] == 0) {// 防止遇到无效贴纸陷入死循环
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < target.length; k++) {
				if (target[k] > 0) {// 有需求
					for (int i = 0; i < Math.max(target[k] - stricker[k], 0); i++) {// 将剩余字符添加进rest
						sb.append((char) ('a' + k));
					}
				}
			}
			String s = sb.toString();
			int tmp = processST(map, s, dp);
			if (tmp != -1) {
				res = Math.min(tmp + 1, res);
			}
		}
		dp.put(rest, res == Integer.MAX_VALUE ? -1 : res);
		return res;
	}

	// 4.最长公共子序列
	public static int SubSequence(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		int N1 = s1.length();
		int N2 = s2.length();
		int[][] dp = new int[N1][N2];
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		dp[0][0] = c1[0] == c2[0] ? 1 : 0;
		for (int i = 1; i < N1; i++) {
			dp[i][0] = c1[i] == c2[0] ? 1 : Math.max(dp[i - 1][0], 0);
		}
		for (int i = 1; i < N2; i++) {
			dp[0][i] = c1[0] == c2[i] ? 1 : Math.max(dp[0][i - 1], 0);
		}
		for (int i = 1; i < N1; i++) {
			for (int j = 1; j < N2; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);// 包含了dp[i-1][j-1]
				if (c1[i] == c2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[N1 - 1][N2 - 1];
	}

	// 4.1最长回文子序列 正向反向最长共子序列
	public static int PalindromeSubsequence(String str) {
		if (str == null) {
			return 0;
		}
		StringBuilder sb = new StringBuilder();
		char[] chs = str.toCharArray();
		for (int i = chs.length - 1; i >= 0; i--) {
			sb.append(chs[i]);
		}
		return SubSequence(str, sb.toString());
	}

	// 5.洗咖啡杯
	public static int Wash(int[] drinks, int a, int b) {
		if (a >= b) {
			return drinks[drinks.length - 1] + b;
		}
		return processWH(drinks, a, b, 0, 0);
	}

	public static int processWH(int[] drinks, int a, int b, int index, int washLine) {
		if (index == drinks.length - 1) {
			return Math.max(washLine, drinks[index]) + a;
		}
		int washTime = Math.max(washLine, drinks[index]) + a;// 获得洗杯时间
		int washNext = Math.max(washTime, processWH(drinks, a, b, index + 1, washTime));// 洗完该杯和后序杯子取大的
		int dry = Math.max(processWH(drinks, a, b, index + 1, washLine), drinks[index] + b);
		return Math.min(washNext, dry);
	}

	// 5.1洗咖啡杯dp
	public static int WashDP(int[] drinks, int a, int b) {
		if (a >= b) {
			return drinks[drinks.length - 1] + b;
		}
		int maxWashTime = 0;
		for (int i = 0; i < drinks.length; i++) {
			maxWashTime = Math.max(maxWashTime, drinks[i]) + a;
		}
		int N = drinks.length;
		int[][] dp = new int[N][maxWashTime + 1];
		for (int i = 0; i <= maxWashTime; i++) {
			dp[N - 1][i] = Math.max(i, drinks[N - 1]) + a;
		}
		for (int index = N - 2; index >= 0; index--) {
			for (int washLine = 0; washLine <= maxWashTime; washLine++) {
				int washTime = Math.max(washLine, drinks[index]) + a;// 获得洗杯时间
				if (washTime <= maxWashTime) {// 注意washTime可能越界
					washTime = Math.max(washTime, dp[index + 1][washTime]);// 洗完该杯和后序杯子取大的
				}
				int dry = Math.max(dp[index + 1][washLine], drinks[index] + b);
				dp[index][washLine] = Math.min(washTime, dry);
			}
		}
		return dp[0][0];
	}

	// 6.分数组
	public static int SplitNum(int[] arr) {// 参数过多不合适dp
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return processSN(arr, 0, 0, 0, sum);
	}

	public static int processSN(int[] arr, int index, int n1, int n2, int sum) {
		if (index == arr.length) {
			return n1 > n2 ? n2 : n1;
		}
		int r1 = processSN(arr, index + 1, n1 + arr[index], n2, sum);
		int r2 = processSN(arr, index + 1, n1, n2 + arr[index], sum);
		return Math.abs(sum - r1 * 2) > Math.abs(sum - r2 * 2) ? r2 : r1;
	}

	// 6.1分数组 根据业务逻辑分析设计参数
	public static int SplitNumNew(int[] arr) {// 参数过多不合适dp
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return processSN1(arr, 0, 0, sum / 2);
	}

	public static int processSN1(int[] arr, int index, int r1, int sum) {
		if (index == arr.length) {
			return r1 <= sum ? r1 : -1;
		}
		int no = processSN1(arr, index + 1, r1, sum);
		int yes = processSN1(arr, index + 1, r1 + arr[index], sum);
		return Math.max(no, yes);
	}

	// 6.2分数组DP 根据业务逻辑分析设计参数
	public static int SplitNumNewDP(int[] arr) {// 参数过多不合适dp
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;
		int N = arr.length;
		int[][] dp = new int[N + 1][sum + 1];
		for (int i = 0; i < sum + 1; i++) {
			dp[N][i] = i <= sum ? i : -1;
		}
		for (int index = N - 1; index >= 0; index--) {
			for (int r1 = sum; r1 >= 0; r1--) {
				dp[index][r1] = Math.max(dp[index + 1][r1], arr[index] + r1 > sum ? 0 : dp[index + 1][r1 + arr[index]]);
			}
		}
//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < sum + 1; j++) {
//				System.out.print(dp[i][j] + "\t");
//			}
//			System.out.println();
//		}
		return dp[0][0];
	}

	// 7.分数组长度也平分
	public static int SplitEqualNum(int[] arr) {
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;
		int n = arr.length / 2;
		return processSEN(arr, 0, sum, 0, n, sum);
	}

	public static int processSEN(int[] arr, int index, int rest, int size, int n, int sum) {
		if (index == arr.length) {// 将处理情况放到递归外处理更节省性能
			if (arr.length % 2 == 1) {
				return size == n || size == n + 1 ? rest < 0 ? 0 : sum - rest : 0;
			} else {
				return size == n ? rest < 0 ? 0 : sum - rest : 0;
			}
		}
		int no = processSEN(arr, index + 1, rest, size, n, sum);
		int yes = processSEN(arr, index + 1, rest - arr[index], size + 1, n, sum);
		return Math.max(no, yes);
	}

	// 7.1分数组长度也平分DP
	public static int SplitEqualNumDP(int[] arr) {
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;
		int n = arr.length;
		int size = (n + 1) / 2;
		int[][][] dp = new int[n + 1][size + 1][sum + 1];
		for (int i = 0; i < dp.length; i++) {// 初始化
			for (int j = 0; j < dp[0].length; j++) {
				for (int k = 0; k < dp[0][0].length; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int rest = 0; rest <= sum; rest++) {// base case
			dp[n][0][rest] = 0;
		}
		for (int index = n - 1; index >= 0; index--) {
			for (int sizes = 0; sizes <= size; sizes++) {
				for (int rest = 0; rest <= sum; rest++) {
					int yes = -1;
					int yesNext = -1;
					if (sizes - 1 >= 0 && arr[index] <= rest) {
						yesNext = dp[index + 1][sizes - 1][rest - arr[index]];
					}
					if (yesNext != -1) {
						yes = yesNext + arr[index];
					}
					dp[index][sizes][rest] = Math.max(dp[index + 1][sizes][rest], yes);
				}
			}
		}
//			System.out.println("数组长度：" + n + " sum：" + sum + " 数组长度half：" + size);
//			for (int i = 0; i < dp.length; i++) {
//				System.out.println("level：" + i);
//				for (int j = 0; j < dp[0].length; j++) {
//					for (int k = 0; k < dp[0][0].length; k++) {
//						System.out.print(dp[i][j][k] + " ");
//					}
//					System.out.println();
//				}
//			}
		return (n & 1) == 0 ? dp[0][size][sum] : Math.max(dp[0][size][sum], dp[0][size + 1][sum]);
	}

	// 8.马走日
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

	public static int pick(int rest, int curX, int curY, int[][][] dp) {// 使用额外方法处理数据化结构
		if (curX > 8 || curX < 0 || curY > 9 || curY < 0) {
			return 0;
		}
		return dp[curX][curY][rest];
	}

	// 9.最小路径和
	public static int MinPath(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		return processMP(matrix, matrix.length - 1, matrix[0].length - 1, 0, 0);
	}

	public static int processMP(int[][] matrix, int x, int y, int curX, int curY) {
		if (curX == matrix.length - 1 && curY == matrix[0].length - 1) {
			return matrix[x][y];
		}
		int rightNext = Integer.MAX_VALUE;
		if (curX + 1 <= x) {
			rightNext = processMP(matrix, x, y, curX + 1, curY) + matrix[curX][curY];
		}
		int downNext = Integer.MAX_VALUE;
		if (curY + 1 <= y) {
			downNext = processMP(matrix, x, y, curX, curY + 1) + matrix[curX][curY];
		}
		return Math.min(rightNext, downNext);
	}

	// 9.1最小路径和DP
	public static int MinPathDP(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		int x = matrix.length - 1;
		int y = matrix[0].length - 1;
		int[][] dp = new int[x + 1][y + 1];
		dp[x][y] = matrix[x][y];
		for (int curY = y - 1; curY >= 0; curY--) {
			dp[x][curY] = dp[x][curY + 1] + matrix[x][curY];
		}
		for (int curX = x - 1; curX >= 0; curX--) {
			dp[curX][y] = dp[curX + 1][y] + matrix[curX][y];
		}
		for (int curX = x - 1; curX >= 0; curX--) {
			for (int curY = y - 1; curY >= 0; curY--) {
				int rightNext = Integer.MAX_VALUE;
				if (curX + 1 <= x) {
					rightNext = dp[curX + 1][curY] + matrix[curX][curY];
				}
				int downNext = Integer.MAX_VALUE;
				if (curY + 1 <= y) {
					downNext = dp[curX][curY + 1] + matrix[curX][curY];
				}
				dp[curX][curY] = Math.min(rightNext, downNext);
			}
		}
//			测试
//			for (int i = 0; i < dp.length; i++) {
//				System.out.println();
//				for (int j = 0; j < dp[0].length; j++) {
//					System.out.print(dp[i][j] + "\t");
//				}
//			}
		return dp[0][0];
	}

	// 9.2最小路径和DP 空间优化
	public static int MinPathDP1(int[][] matrix) {
		if (matrix == null || matrix.length < 1) {
			return 0;
		}
		int x = matrix.length - 1;
		int y = matrix[0].length - 1;
		int[] dp = new int[y + 1];
		dp[y] = matrix[x][y];// 因为只依赖上 左单个位置 所以可以只是用一行dp
		for (int curY = y - 1; curY >= 0; curY--) {
			dp[curY] = dp[curY + 1] + matrix[x][curY];
		}
		for (int curX = x - 1; curX >= 0; curX--) {
			for (int curY = y; curY >= 0; curY--) {
				dp[curY] = Math.min(dp[curY], curY + 1 > y ? Integer.MAX_VALUE : dp[curY + 1]) + matrix[curX][curY];
			}
		}
		return dp[0];
	}

	// 10.硬币凑钱
	public static int Coin(int[] coin, int target) {
		if (coin == null || coin.length < 1 || target < 0) {
			return 0;
		}
		return processCW(coin, 0, target);
	}

	public static int processCW(int[] coin, int index, int rest) {
		if (index == coin.length) {
			return rest == 0 ? 1 : 0;
		}
		if (rest < 0) {
			return 0;
		}
		int ways = 0;
		for (int n = 0; n * coin[index] <= rest; n++) {
			ways += processCW(coin, index + 1, rest - n * coin[index]);
		}
		return ways;
	}

	// 10.1硬币凑钱DP
	public static int CoinDP(int[] coin, int target) {
		if (coin == null || coin.length < 1 || target < 0) {
			return 0;
		}
		int N = coin.length;
		int[][] dp = new int[N + 1][target + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= target; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - coin[index] >= 0) {
					dp[index][rest] += dp[index][rest - coin[index]];
				}
				// 观察图形结构优化
//					for (int n = 0; n * coin[index] <= rest; n++) {
//						dp[index][rest] += dp[index + 1][rest - n * coin[index]];
//					}
			}
		}
		return dp[0][target];
	}

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

	// 11.1怪兽N血量 M最大伤害 K攻击次数DP
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
				if (restN - 1 - M >= 0) {// 额外增加的部分在数组中直接减掉
					dp[restK][restN] -= dp[restK - 1][restN - 1 - M];
				} else {
					dp[restK][restN] -= (int) Math.pow(M + 1, restK - 1);// 计算所有血量<0的部分
				}
			}
		}
		return (double) ((double) dp[K][N] / (double) Math.pow(M + 1, K));
	}

}
