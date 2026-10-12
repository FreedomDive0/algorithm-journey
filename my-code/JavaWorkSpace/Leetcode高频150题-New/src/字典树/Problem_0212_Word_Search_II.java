package 字典树;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Problem_0212_Word_Search_II {
	public List<String> findWords(char[][] board, String[] words) {
		Node root = new Node();
		List<String> res = new ArrayList<String>();
		fillWords(words, root);
		LinkedList<Character> path = new LinkedList<Character>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				process(board, i, j, root, res, path);
			}
		}
		return res;
	}

	private int process(char[][] board, int i, int j, Node node, List<String> res, LinkedList<Character> path) {
		if (board[i][j] == 0) {
			return 0;
		}
		char c = board[i][j];
		node = node.nexts[c - 'a'];
		if (node == null || node.pass == 0) {
			return 0;
		}
		path.addLast(c);
		int fix = 0;
		if (node.end > 0) {
			res.add(generate(path));
			node.end--;
			fix++;
		}
		board[i][j] = 0;
		if (i > 0) {
			fix += process(board, i - 1, j, node, res, path);
		}
		if (i < board.length - 1) {
			fix += process(board, i + 1, j, node, res, path);
		}
		if (j > 0) {
			fix += process(board, i, j - 1, node, res, path);
		}
		if (j < board[0].length - 1) {
			fix += process(board, i, j + 1, node, res, path);
		}
		node.pass -= fix;
		board[i][j] = c;
		path.pollLast();
		return fix;
	}

	private String generate(LinkedList<Character> path) {
		char[] chs = new char[path.size()];
		for (int i = 0; i < chs.length; i++) {
			chs[i] = path.get(i);
		}
		return String.valueOf(chs);
	}

	private void fillWords(String[] words, Node root) {
		HashSet<String> set = new HashSet<String>();
		for (String word : words) {
			if (!set.contains(word)) {
				Node cur = root;
				cur.pass++;
				for (char c : word.toCharArray()) {
					if (cur.nexts[c - 'a'] == null) {
						cur.nexts[c - 'a'] = new Node();
					}
					cur.nexts[c - 'a'].pass++;
					cur = cur.nexts[c - 'a'];
				}
				cur.end++;
				set.add(word);
			}
		}

	}

	class Node {
		public int pass;
		public int end;
		public Node[] nexts;

		public Node() {
			pass = 0;
			end = 0;
			nexts = new Node[26];
		}
	}
}
