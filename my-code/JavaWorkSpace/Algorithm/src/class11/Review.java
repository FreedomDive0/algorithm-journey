package class11;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Review {
	// 1.汉诺塔
	public static void Hanoi(int N) {
		processH(N, "左", "右", "中");
	}

	public static void processH(int N, String from, String to, String other) {
		if (N == 1) {
			System.out.println("Move 1 " + from + " " + to);
			return;
		}
		processH(N - 1, from, other, to);
		System.out.println("Move " + N + " " + from + " " + to);
		processH(N - 1, other, to, from);
	}

	public static void main(String[] args) {
		Hanoi(3);
	}

	// 2.不使用额外空间 逆序栈
	public static int deleAndLast(Stack<Integer> stack) {
		int cur = stack.pop();// 保留当前值
		if (stack.isEmpty()) {
			return cur;
		} else {
			int last = deleAndLast(stack);// 寻找栈底值
			stack.add(cur);// 重新加入
			return last;// 返回最底值
		}
	}

	public static void ReverseStack(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		} else {
			int last = deleAndLast(stack);// 保留原有结构 删除并返回栈底值
			ReverseStack(stack);// 反转剩余结构
			stack.push(last);// 将删除值加入新结构
		}
	}

	// 3.打印String全部子序列(不包含重复使用HashSet)
	public static List<String> SubString(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> res = new ArrayList<>();
		processSS(str.toCharArray(), 0, res, "");
		return res;
	}

	public static void processSS(char[] arr, int index, ArrayList<String> res, String cur) {
		if (index == arr.length) {
			res.add(cur);
			return;
		}
		processSS(arr, index + 1, res, cur);
		processSS(arr, index + 1, res, cur + String.valueOf(arr[index]));
	}

	// 5.全排列
	public static List<String> AllPer(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> res = new ArrayList<>();
		processAP(str.toCharArray(), 0, res);
		return res;
	}

	public static void processAP(char[] arr, int index, ArrayList<String> res) {
		if (index == arr.length) {
			res.add(String.valueOf(arr));
			return;
		}
		for (int i = index; i < arr.length; i++) {
			swap(arr, index, i);
			processAP(arr, index + 1, res);
			swap(arr, index, i);
		}
	}

	public static void swap(char[] arr, int i, int j) {
		if (arr == null || i == j) {
			return;
		}
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 6.全排列无重复
	public static List<String> AllPerNoRepeat(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> res = new ArrayList<>();
		processAPNP(str.toCharArray(), 0, res);
		return res;
	}

	public static void processAPNP(char[] arr, int index, ArrayList<String> res) {
		if (index == arr.length) {
			res.add(String.valueOf(arr));
			return;
		}
		boolean[] visit = new boolean[26];// 保证每个位置不出现重复值
		for (int i = index; i < arr.length; i++) {
			if (!visit[arr[i] - 'a']) {
				visit[arr[i] - 'a'] = true;
				swap(arr, index, i);
				processAPNP(arr, index + 1, res);
				swap(arr, index, i);
			}
		}
	}

	// 7.数字对应问题
	public static int ConvertNumTOString(String str) {
		if (str == null) {
			return 0;
		}
		return processCNS(str.toCharArray(), 0);
	}

	public static int processCNS(char[] arr, int index) {
		if (index == arr.length) {
			return 1;
		}
		if (arr[index] == '0') {// 0
			return 0;
		}
		if (arr[index] == '1') {// 1 or 10~19
			int res = processCNS(arr, index + 1);
			if (index + 1 < arr.length) {
				res += processCNS(arr, index + 2);
			}
			return res;
		}
		if (arr[index] == '2') {
			int res = processCNS(arr, index + 1);
			if (index + 1 < arr.length && arr[index + 1] < '7') {
				res += processCNS(arr, index + 2);
			}
			return res;
		}
		return processCNS(arr, index + 1);// 3~9
	}

	// 7.1数字对应问题 动态规划
	public static int ConvertNumTOStringDP(String str) {
		if (str == null) {
			return 0;
		}
		int N = str.length();
		char[] chs = str.toCharArray();
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (chs[i] == '0') {
				dp[i] = 0;
				continue;
			} else if (chs[i] == '1') {
				if (i + 1 < N) {
					dp[i] += dp[i + 2];
				}
			} else if (chs[i] == '2') {
				if (i + 1 < N && chs[i + 1] < '7') {
					dp[i] += dp[i + 2];
				}
			}
			dp[i] += dp[i + 1];

		}
		return dp[0];
	}

	// 8.背包问题
	public static int bag(int[] values, int[] weights, int bag) {
		return processB(values, weights, 0, bag);
	}

	public static int processB(int[] values, int[] weights, int index, int rest) {
		if (rest <= 0) {
			return 0;// 代表方案无效
		}
		if (index == values.length) {
			return 0;
		}
		int no = processB(values, weights, index + 1, rest);
		int yes = Integer.MIN_VALUE;
		if (rest >= weights[index]) {
			yes = values[index] + processB(values, weights, index + 1, rest - weights[index]);
		}
		return Math.max(no, yes);
	}

	// 8.背包问题 动态规划
	public static int bagDP(int[] values, int[] weights, int bag) {
		int N = values.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= bag; j++) {
				int no = dp[i + 1][j];
				int yes = j >= weights[i] ? values[i] + dp[i + 1][j - weights[i]] : Integer.MIN_VALUE;
				dp[i][j] = Math.max(no, yes);
			}
		}
		return dp[0][bag];
	}

	// 9.摸牌问题
	public static int cardWinner(int[] card) {
		return Math.max(first(card, 0, card.length - 1), second(card, 0, card.length - 1));
	}

	public static int first(int[] card, int L, int R) {
		if (L == R) {
			return card[L];
		}
		return Math.max(card[L] + second(card, L + 1, R), card[R] + second(card, L, R - 1));
	}

	public static int second(int[] card, int L, int R) {
		if (L == R) {
			return 0;
		}
		return Math.min(first(card, L + 1, R), first(card, L, R - 1));
	}

	// 9.摸牌问题 动态规划
	public static int cardWinnerDP(int[] card) {
		int N = card.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = card[i];
		}
		for (int i = 1; i < N; i++) {
			int R = i;
			int L = 0;// 注意分析位置
			while (L < N && R < N) {
				f[R][L] = Math.max(card[L] + s[R][L + 1], card[R] + s[R - 1][L]);
				s[R][L] = Math.min(f[R][L + 1], f[R - 1][L]);
				L++;
				R++;
			}
		}
		return Math.max(f[N - 1][0], s[N - 1][0]);
	}

	// 10.N皇后问题
	public static int NQueen(int N) {
		if (N < 1) {
			return 0;
		}
		int[] pos = new int[N];
		return processNQ(0, N, pos);
	}

	public static int processNQ(int index, int N, int[] pos) {
		if (index == N) {
			return 1;
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			if (isVaild(pos, index, i)) {
				pos[index] = i;
				res += processNQ(index + 1, N, pos);
			}
		}
		return res;
	}

	public static boolean isVaild(int[] pos, int i, int j) {
		for (int k = 0; k < i; k++) {// 检查到i-1行
			if (pos[k] == j || (Math.abs(k - i) == Math.abs(pos[k] - j))) {
				return false;
			}
		}
		return true;
	}

	// 10.1 N皇后优化
	public static int NQueenNB(int N) {
		if (N < 1 || N > 32) {// 超过int位数不够使用
			return 0;
		}
		int limit = N == 32 ? -1 : (1 << N) - 1;// 获得N位个1
		return processNQNB(limit, 0, 0, 0);
	}

	public static int processNQNB(int limits, int colLim, int leftLim, int rightLim) {// 从限制中获得开始位置
		if (colLim == limits) {// 列限制又能代表皇后所放的位置
			return 1;
		}
		int pos = limits & ~(colLim | leftLim | rightLim);// 获得可以放1的位置
		int mostRightOne = 0;
		int res = 0;
		while (pos != 0) {
			mostRightOne = pos & ((~pos) + 1);// 提取1
			pos -= mostRightOne;
			res += processNQNB(limits, colLim | mostRightOne, (leftLim | mostRightOne) << 1,
					(rightLim | mostRightOne) >>> 1);
		}
		return res;
	}
}
