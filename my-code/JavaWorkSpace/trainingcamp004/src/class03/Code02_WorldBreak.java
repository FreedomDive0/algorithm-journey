package class03;

public class Code02_WorldBreak {
	public static class TrieNode {
		boolean end;
		TrieNode[] nexts;

		public TrieNode() {
			this.end = false;
			this.nexts = new TrieNode[26];
		}

	}

	public static TrieNode fillTrie(String[] words) {// 建立前缀树
		TrieNode head = new TrieNode();
		for (String word : words) {
			TrieNode cur = head;
			for (char c : word.toCharArray()) {
				int index = c - 'a';
				if (cur.nexts[index] == null) {
					cur.nexts[index] = new TrieNode();
				}
				cur = cur.nexts[index];
			}
			cur.end = true;
		}
		return head;
	}

	public static int worldBreak(String str, String[] words) {
		if (str == null || str.length() < 1 || words == null || words.length < 1) {
			return 0;
		}
		TrieNode head = fillTrie(words);
		return process(str.toCharArray(), 0, head);
	}

	public static int process(char[] str, int index, TrieNode head) {
		if (index == str.length) {
			return 1;
		}
		TrieNode cur = head;
		int ways = 0;
		for (int end = index; end < str.length; end++) {
			int path = str[end] - 'a';// 注意下标
			if (cur.nexts[path] == null) {// 只是选择分支
				break;
			}
			cur = cur.nexts[path];// 到达该点才能查看是否是结尾
			if (cur.end) {
				ways += process(str, end + 1, head);
			}
		}
		return ways;
	}

	// 动态规划
	public static int dp(String str, String[] words) {
		if (str == null || str.length() < 1 || words == null || words.length < 1) {
			return 0;
		}
		TrieNode head = fillTrie(words);
		int N = str.length();
		char[] chs = str.toCharArray();
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			TrieNode cur = head;
			for (int end = i; end < N; end++) {
				int path = chs[end] - 'a';// 注意下标
				if (cur.nexts[path] == null) {// 只是选择分支
					break;
				}
				cur = cur.nexts[path];// 到达该点才能查看是否是结尾
				if (cur.end) {
					dp[i] += dp[end + 1];
				}
			}
		}
		return dp[0];
	}
}
