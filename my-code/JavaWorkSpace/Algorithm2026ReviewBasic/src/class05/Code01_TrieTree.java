package class05;

public class Code01_TrieTree {
	public static class Node {
		public Node[] nexts;
		public int pass;
		public int end;

		public Node() {
			nexts = new Node[26];
			pass = 0;
			end = 0;
		}

	}

	public static class Trie {
		Node root;

		public Trie() {
			root = new Node();
		}

		public void add(String str) {
			if (str == null || str.length() < 1)
				return;
			char[] chs = str.toCharArray();
			Node cur = root;
			cur.pass++;
			for (char c : chs) {
				int idx = c - 'a';
				if (cur.nexts[idx] == null) {
					cur.nexts[idx] = new Node();
				}
				cur = cur.nexts[idx];
				cur.pass++;
			}
			cur.end++;
		}

		public int search(String str) {
			if (str == null || str.length() < 1)
				return 0;
			char[] chs = str.toCharArray();
			Node cur = root;
			for (char c : chs) {
				int idx = c - 'a';
				if (cur.nexts[idx] == null) {
					return 0;
				}
				cur = cur.nexts[idx];
			}
			return cur.end;
		}

		public int prefix(String str) {
			if (str == null || str.length() < 1)
				return 0;
			char[] chs = str.toCharArray();
			Node cur = root;
			for (char c : chs) {
				int idx = c - 'a';
				if (cur.nexts[idx] == null) {
					return 0;
				}
				cur = cur.nexts[idx];
			}
			return cur.pass;
		}

		public void delete(String str) {
			if (search(str) > 0) {
				Node cur = root;
				cur.pass--;// 头节点--
				char[] chs = str.toCharArray();
				for (char c : chs) {
					int idx = c - 'a';
					if (--cur.nexts[idx].pass == 0) {
						cur.nexts[idx] = null;
						return;
					}
					cur = cur.nexts[idx];
				}
				cur.end--;
			}
		}
	}
}
