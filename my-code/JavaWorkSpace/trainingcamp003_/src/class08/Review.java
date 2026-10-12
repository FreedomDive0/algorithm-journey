package class08;

import java.util.HashMap;
import java.util.HashSet;

public class Review {
	// 1.设计打印结构
	public static class Node {
		String info;
		Node next;

		public Node(String info) {
			this.info = info;
			this.next = null;
		}

	}

	public static class MessageBox {
		static HashMap<Integer, Node> head;
		static HashMap<Integer, Node> tail;
		static int waitPrint = 1;

		public MessageBox() {
			head = new HashMap<Integer, Node>();
			tail = new HashMap<Integer, Node>();
			waitPrint = 1;
		}

		public static void receive(int index, String value) {
			if (index < 0) {
				return;
			}
			Node cur = new Node(value);
			head.put(index, cur);
			tail.put(index, cur);
			if (tail.containsKey(index - 1)) {// cur头能不能连
				tail.get(index - 1).next = cur;
				tail.remove(index - 1);
				head.remove(index);
			}
			if (head.containsKey(index + 1)) {// cur尾能不能连
				cur.next = head.get(index + 1);
				head.remove(index + 1);
				tail.remove(index);
			}
			if (index == waitPrint) {
				print();
			}
		}

		private static void print() {
			Node cur = head.get(waitPrint);
			head.remove(waitPrint);
			while (cur != null) {
				System.out.print(cur.info + " ");
				waitPrint++;
				cur = cur.next;
			}
			System.out.println();
			tail.remove(waitPrint - 1);
		}
	}

	// 2.n1和n2两种面值的硬币 n1为普通币可无限次使用 n2为纪念币每种只能使用一个 给定一个目标 求有多少种组合方式
	public static int MoneyWays(int[] coin1, int[] coin2, int aim) {
		if (aim < 0) {
			return 0;
		}
		if ((coin1 == null || coin1.length < 1) && (coin2 == null || coin2.length < 1)) {
			return aim == 0 ? 1 : 0;
		}
		int[][] dp1 = getDPCoin1(coin1, aim);
		int[][] dp2 = getDPCoin2(coin2, aim);
		if (dp1 == null) {
			return dp2[coin2.length - 1][aim];
		}
		if (dp2 == null) {
			return dp1[coin1.length - 1][aim];
		}
		int ans = 0;
		for (int i = 0; i < aim; i++) {
			ans += dp1[coin1.length - 1][i] * dp2[coin2.length - 1][aim - i];
		}
		return ans;
	}

	// 得到第一种币的情况
	public static int[][] getDPCoin1(int[] coin1, int aim) {
		int[][] dp1 = new int[coin1.length][aim + 1];
		for (int i = 0; i < dp1.length; i++) {
			dp1[i][0] = 1;
		}
		for (int i = 0; i * coin1[0] <= aim; i++) {
			dp1[0][i * coin1[0]] = 1;
		}
		for (int i = 1; i < dp1.length; i++) {
			for (int j = 1; j < aim; j++) {
				dp1[i][j] = dp1[i - 1][j];
				dp1[i][j] += j - coin1[i] >= 0 ? dp1[i][j - coin1[i]] : 0;
			}
		}
		return dp1;
	}

	// 得到第二种币的情况
	public static int[][] getDPCoin2(int[] coin2, int aim) {
		int[][] dp2 = new int[coin2.length][aim + 1];
		for (int i = 0; i < dp2.length; i++) {
			dp2[i][0] = 1;
		}
		if (coin2[0] <= aim) {
			dp2[0][coin2[0]] = 1;
		}
		for (int i = 1; i < dp2.length; i++) {
			for (int j = 1; j < aim; j++) {
				dp2[i][j] = dp2[i - 1][j];
				dp2[i][j] += j - coin2[i] >= 0 ? dp2[i][j - coin2[i]] : 0;
			}
		}
		return dp2;
	}

	// 3.给定一个正数N 在纸上写下1~N所有数字 return写了多少个1
	public static int OneNumber(int N) {
		if (N < 1) {
			return 0;
		}
		int len = getLen(N);
		if (len == 1) {
			return 1;
		}
		int base = (int) Math.pow(10, len - 1);
		int first = N / base;
		int firstOne = first == 1 ? N % base + 1 : base;
		int otherOne = first * (len - 1) * base / 10;
		return firstOne + otherOne + OneNumber(N % base);
	}

	public static int getLen(int N) {
		int len = 0;
		while (N > 0) {
			len++;
			N /= 10;
		}
		return len;
	}

	// 4.
	// 5.可整合数组
	public static int LongestIntegratedLength(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		int len = 0;
		for (int L = 0; L < arr.length; L++) {
			set.clear();
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int R = L; R < arr.length; R++) {
				if (set.contains(arr[R])) {
					break;
				}
				set.add(arr[R]);
				min = Math.min(min, arr[R]);
				max = Math.max(max, arr[R]);
				if (max - min == R - L) {
					len = Math.max(len, R - L + 1);
				}
			}
		}
		return len;
	}
	// 6.字符串任意位置添加字符 最少添加几个字符让整个字符串是回文
}
