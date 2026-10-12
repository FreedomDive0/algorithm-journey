package class01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Code04_WordSearch {
	public static class TrieNode {// 前缀树
		int pass;
		int end;
		TrieNode[] nexts;

		public TrieNode() {
			this.pass = 0;
			this.end = 0;
			nexts = new TrieNode[26];
		}

	}

	public static TrieNode fullTrie(String[] words) {// 构建前缀树
		TrieNode head = new TrieNode();
		HashSet<String> set = new HashSet<>();
		for (String word : words) {// 遍历每个字符串
			TrieNode cur = head;
			if (!set.contains(word)) {// 去重
				cur.pass++;// 头节点++
				for (char c : word.toCharArray()) {
					int index = c - 'a';
					if (cur.nexts[index] == null) {// 无路建路
						cur.nexts[index] = new TrieNode();
					}
					cur = cur.nexts[index];
					cur.pass++;
				}
				cur.end++;
			}
		}
		return head;
	}

	public static List<String> wordSearch(char[][] board, String[] words) {
		if (board == null || board.length < 1 || board[0].length < 1 || words == null || words.length < 1) {
			return null;
		}
		TrieNode head = fullTrie(words);// 构建前缀树
		ArrayList<String> ans = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				process(board, i, j, head, new LinkedList<>(), ans);
			}
		}
		return ans;
	}

	// 用前缀树跑整个board
	public static int process(char[][] board, int i, int j, TrieNode cur, LinkedList<Character> path,
			ArrayList<String> ans) {
		char c = board[i][j];// 得到当前字符
		if (c == 0) {// 该字符使用过
			return 0;
		}
		int index = c - 'a';// 得到字符在nexts的下标
		if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {// 不存在这条路 或者已被添加
			return 0;
		}
		cur = cur.nexts[index];// 能来到该点
		path.addLast(c);
		int fix = 0;// 记录通过该点符合条件字符串数量
		if (cur.end > 0) {
			ans.add(getString(path));
			fix++;
			cur.end--;
		}
		board[i][j] = 0;// 进入循环前标记 防止重复进入
		if (i > 0) {
			fix += process(board, i - 1, j, cur, path, ans);
		}
		if (i < board.length - 1) {
			fix += process(board, i + 1, j, cur, path, ans);
		}
		if (j > 0) {
			fix += process(board, i, j - 1, cur, path, ans);
		}
		if (j < board[0].length - 1) {
			fix += process(board, i, j + 1, cur, path, ans);
		}
		path.pollLast();
		board[i][j] = c;// 恢复字符
		cur.pass -= fix;// 减去得到的单词
		return fix;// 帮助剪枝的关键
	}

	public static String getString(LinkedList<Character> path) {
		if (path == null || path.size() < 1) {
			return null;
		}
		String ans = "";
		for (Character c : path) {
			ans += c;
		}
		return ans;
	}

}
