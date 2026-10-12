package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Review1 {
	// 1.汉诺塔问题
	public static void hanoi(int n) {
		processH("左", "右", "中", n);
	}

	public static void processH(String from, String to, String other, int n) {
		if (n == 1) {
			System.out.println("Move" + n + "from：" + from + " to " + to);
			return;
		}
		processH(from, other, to, n - 1);
		System.out.println("Move" + n + "from：" + from + " to " + to);
		processH(other, to, from, n - 1);
	}

	// 2.只使用递归逆序栈
	// (1)弹出并返回栈底元素
	public static Integer f(Stack<Integer> stack) {
		int res = stack.pop();
		if (stack.isEmpty()) {
			return res;
		} else {
			int last = f(stack);
			stack.push(res);
			return last;
		}
	}

	// (2)逆序 反转栈
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int end = f(stack);// 得到栈底
		reverse(stack);// 反转栈
		stack.push(end);// 将栈底添加到反转后的栈中
	}

	// 3.字符串全部子序列
	public static ArrayList<String> printAllSubsquences(String s) {
		ArrayList<String> ans = new ArrayList<String>();
		processPAS(s.toCharArray(), 0, ans, "");
		return ans;
	}

	public static void processPAS(char[] chs, int index, ArrayList<String> ans, String path) {
		if (index == chs.length) {
			ans.add(path);
			return;
		}
		processPAS(chs, index + 1, ans, path);
		processPAS(chs, index + 1, ans, path + String.valueOf(chs[index]));
	}

	// 4.字符串全部子序列(不重复)
	public static HashSet<String> printAllUnSubsquences(String s) {
		HashSet<String> ans = new HashSet<String>();
		processPAUS(s.toCharArray(), 0, ans, "");
		return ans;
	}

	public static void processPAUS(char[] chs, int index, HashSet<String> ans, String path) {
		if (index == chs.length) {
			ans.add(path);
			return;
		}
		processPAUS(chs, index + 1, ans, path);
		processPAUS(chs, index + 1, ans, path + String.valueOf(chs[index]));
	}

	// 5.字符串的全排列
	public static ArrayList<String> printAllPermutations(String s) {
		ArrayList<String> ans = new ArrayList<String>();
		processPAP(s.toCharArray(), 0, ans);
		return ans;
	}

	// chs 0~index-1都已确定
	public static void processPAP(char[] chs, int index, ArrayList<String> ans) {
		if (index == chs.length) {
			ans.add(String.valueOf(chs));
			return;
		}
		for (int j = index; j < chs.length; j++) {
			swap(chs, index, j);// 尝试每个位置
			processPAP(chs, index + 1, ans);
			swap(chs, index, j);// 还原现场
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	// 6.字符串全排列(不重复)分支限界
	public static ArrayList<String> printAllUnPermutations(String s) {
		ArrayList<String> ans = new ArrayList<String>();
		processPAUP(s.toCharArray(), 0, ans);
		return ans;
	}

	// chs 0~index-1都已确定
	public static void processPAUP(char[] chs, int index, ArrayList<String> ans) {
		if (index == chs.length) {
			ans.add(String.valueOf(chs));
			return;
		}
		boolean[] visited = new boolean[26];// 每次循环开始前创建新的标志位
		for (int j = index; j < chs.length; j++) {
			if (!visited[chs[j] - 'a']) {
				visited[chs[j] - 'a'] = true;
				swap(chs, index, j);// 尝试每个位置
				processPAUP(chs, index + 1, ans);
				swap(chs, index, j);// 还原现场
			}
		}
	}

	// 7.数字对应字符串
	public static int convertToLetter(String nums) {
		return processCTL(nums.toCharArray(), 0);
	}

	public static int processCTL(char[] nums, int index) {
		if (index == nums.length) {
			return 1;
		}
		int res = 0;
		if (nums[index] == '0') {
			res = 0;
		} else {
			res = processCTL(nums, index + 1);
			if (index + 1 < nums.length && ((nums[index] == '1')
					|| (nums[index] == '2' && nums[index + 1] >= '0' && nums[index + 1] <= '6'))) {
				res += processCTL(nums, index + 2);
			}
		}
		return res;
	}

	// (2)dp
	public static int convertToLetterDP(String nums) {
		int n = nums.length();
		int[] dp = new int[n + 1];
		char[] chs = nums.toCharArray();
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (chs[i] == '0') {
				dp[i] = 0;
			} else {
				dp[i] = dp[i + 1];
				if (i + 1 < n && ((chs[i] == '1') || (chs[i] == '2' && chs[i + 1] >= '0' && chs[i + 1] <= '6'))) {
					dp[i] += dp[i + 2];
				}
			}
		}
		return dp[0];
	}

	// 8.背包问题
	public static int bag(int[] values, int[] weights, int bag) {
		return processB(values, weights, 0, bag, 0);
	}

	public static int processB(int[] values, int[] weights, int index, int bag, int curWeight) {
		if (curWeight > bag) {
			return -1;
		}
		if (index == values.length) {
			return 0;
		}
		int p1 = processB(values, weights, index + 1, bag, curWeight);
		int p2 = 0;
		int p2Next = processB(values, weights, index + 1, bag, curWeight + weights[index]);
		if (p2Next != -1) {
			p2 = values[index] + p2Next;
		}
		return Math.max(p1, p2);
	}

	// (2)使用rest参数
	public static int bag1(int[] values, int[] weights, int bag) {
		return processB1(values, weights, 0, bag);
	}

	public static int processB1(int[] values, int[] weights, int index, int rest) {
		if (rest <= 0) {
			return 0;
		}
		if (index == values.length) {
			return 0;
		}
		int p1 = processB1(values, weights, index + 1, rest);
		int p2 = 0;
		if (rest >= weights[index]) {// 每次入背包前判断
			p2 = values[index] + processB1(values, weights, index + 1, rest - weights[index]);
		}
		return Math.max(p1, p2);
	}

	// (3)dp
	public static int bag2DP(int[] values, int[] weights, int bag) {
		int[][] dp = new int[values.length + 1][bag + 1];
		for (int row = values.length - 1; row >= 0; row--) {
			for (int col = 1; col <= bag; col++) {
				dp[row][col] = dp[row + 1][col];
				if (col >= weights[row]) {
					dp[row][col] = Math.max(dp[row][col], values[row] + dp[row + 1][col - weights[row]]);
				}
			}
		}
		return dp[0][bag];
	}

	// 9.摸牌游戏
	public static int cardInLine(int[] cards) {
		return Math.max(f(cards, 0, cards.length - 1), s(cards, 0, cards.length - 1));
	}

	public static int f(int[] cards, int l, int r) {// 先手摸牌
		if (l == r) {
			return cards[l];// 只剩一张直接拿
		}
		return Math.max(cards[l] + s(cards, l + 1, r), cards[r] + s(cards, l, r - 1));// 拿左or右 后手拿牌和后续操作要大的
	}

	public static int s(int[] cards, int l, int r) {// 后手摸牌
		if (l == r) {
			return 0;// 没得选
		}
		return Math.min(f(cards, l + 1, r), f(cards, l, r - 1));// 先手摸的人 会让后手的人摸到两种结果较小的
	}

	// (2)dp
	public static int cardInLineDP(int[] cards) {
		int n = cards.length;
		int[][] fDp = new int[n][n];
		int[][] sDp = new int[n][n];
		for (int i = 0; i < n; i++) {
			fDp[i][i] = cards[i];
		}
		for (int i = 1; i < n; i++) {// 规定次数
			int l = 0;
			int r = i;// 确定开始
			while (l < n && r < n) {// r<n 可以卡主l<r
				fDp[l][r] = Math.max(cards[l] + sDp[l + 1][r], cards[r] + sDp[l][r - 1]);
				sDp[l][r] = Math.min(fDp[l + 1][r], fDp[l][r - 1]);
				l++;
				r++;
			}
		}
//			for (int r = n - 2; r >= 0; r--) {//存在问题是只能规定执行次数 r每次其实没有规定 只在一列不停循环
//				for (int l = 0; l <= r; l++) {
//					fDp[l][l + 1] = Math.max(cards[l] + sDp[l + 1][l + 1], cards[l + 1] + sDp[l][l]);
//					sDp[l][l + 1] = Math.min(fDp[l + 1][l + 1], fDp[l][l]);
//				}
//			}
//			System.out.println("s");
//			for (int i = 0; i < sDp.length; i++) {
//				for (int j = 0; j < sDp.length; j++) {
//					System.out.print(sDp[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("f");
//			for (int i = 0; i < sDp.length; i++) {
//				for (int j = 0; j < sDp.length; j++) {
//					System.out.print(fDp[i][j] + " ");
//				}
//				System.out.println();
//			}
		return Math.max(fDp[0][n - 1], sDp[0][n - 1]);
	}

	// 10.N皇后问题
	// (1)record记录
	public static int nQueens1(int N) {
		int[] record = new int[N];
		return processNQ1(0, record, N);
	}

	public static int processNQ1(int i, int[] record, int N) {
		if (i == N) {
			return 1;
		}
		int res = 0;
		for (int j = 0; j < N; j++) {
			if (isValid(record, i, j, N)) {
				record[i] = j;
				res += processNQ1(i + 1, record, N);
			}
		}
		return res;
	}

	private static boolean isValid(int[] record, int i, int j, int n) {
		for (int k = 0; k < i; k++) {
			if (record[k] == j || (Math.abs(i - k) == Math.abs(j - record[k]))) {
				return false;
			}
		}
		return true;
	}

	// (2)位运算加速 32位以内
	public static int nQueens2(int N) {
		if (N < 1 || N > 32) {
			return 0;
		}
		int limit = N == 32 ? -1 : (1 << N) - 1;// (1 << N) - 1 = 111111111111
		return processNQ2(limit, 0, 0, 0);// 1 << N - 1 = 100000000000
	}

	private static int processNQ2(int limit, int colLimit, int leftLimit, int rightLimit) {
		if (colLimit == limit) {
			return 1;
		}
		int pos = limit & ~(colLimit | leftLimit | rightLimit);// 可以用的位置
		int rightOne = 0;
		int res = 0;
		while (pos != 0) {
			rightOne = pos & (~pos + 1);
			pos -= rightOne;
			res += processNQ2(limit, colLimit | rightOne, (rightOne | leftLimit) << 1, (rightOne | rightLimit) >>> 1);// rightOne和斜线处理
		}
		return res;
	}

	public static void main(String[] args) {
//		hanoi(3);
//		Stack<Integer> stack = new Stack<Integer>();
//		stack.push(3);
//		stack.push(2);
//		stack.push(1);
//		reverse(stack);
//		while (!stack.isEmpty()) {
//			System.out.println(stack.pop());
//		}
//		HashSet<String> ans = new HashSet<String>();
//		ans = printAllUnSubsquences("aaa");
//		for (String s : ans) {
//			System.out.println(s);
//		}
//		ArrayList<String> ans = printAllUnPermutations("aaa");
//		for (String s : ans) {
//			System.out.println(s);
//		}
		int N = 12;
		System.out.println("(1 << N) - 1 = " + Integer.toBinaryString(((1 << N) - 1)));
		System.out.println("1 << N - 1 = " + Integer.toBinaryString(1 << N - 1));
	}
}
