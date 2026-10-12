package 一维动态规划;

import java.util.List;

public class Problem_0139_Word_Break {
	class Node {
		boolean end;
		Node[] next;

		public Node() {
			end = false;
			next = new Node[26];
		}

	}

	public boolean wordBreak3(String s, List<String> wordDict) {
		Node root = new Node();
		for (String word : wordDict) {
			Node cur = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				if (cur.next[str[i] - 'a'] == null) {
					cur.next[str[i] - 'a'] = new Node();
				}
				cur = cur.next[str[i] - 'a'];
			}
			cur.end = true;
		}
		int N = s.length();
		int[] dp = new int[N + 1];
		char[] str = s.toCharArray();
		dp[N] = 1;
		for (int index = N - 1; index >= 0; index--) {
			Node cur = root;
			for (int end = index; end < N; end++) {
				if (cur.next[str[end] - 'a'] == null) {
					break;
				}
				cur = cur.next[str[end] - 'a'];
				if (cur.end) {
					dp[index] += dp[end + 1];
				}
			}
		}
		return dp[0] != 0;
	}

	public boolean wordBreak2(String s, List<String> wordDict) {
		int N = s.length();
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int index = N - 1; index >= 0; index--) {
			int ways = 0;
			for (int end = index; end < N; end++) {
				String pre = s.substring(index, end + 1);
				if (wordDict.contains(pre)) {
					ways += dp[end + 1];
				}
				dp[index] = ways;
			}
		}
		return dp[0] != 0;
	}

	public boolean wordBreak1(String s, List<String> wordDict) {
		return process1(s, 0, wordDict) != 0;
	}

	public int process1(String s, int index, List<String> wordDict) {
		if (index == s.length()) {
			return 1;
		}
		int ways = 0;
		for (int end = index; end < s.length(); end++) {
			String pre = s.substring(index, end + 1);
			if (wordDict.contains(pre)) {
				ways += process1(s, end + 1, wordDict);
			}
		}
		return ways;
	}
}
