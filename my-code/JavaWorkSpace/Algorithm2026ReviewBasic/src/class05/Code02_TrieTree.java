package class05;

import java.util.HashMap;

public class Code02_TrieTree {
	public static class TrieNode {
		public HashMap<Integer, TrieNode> nexts;
		public int pass;
		public int end;

		public TrieNode() {
			nexts = new HashMap<Integer, TrieNode>();
			pass = 0;
			end = 0;
		}

	}

	public static class TrieTree {
		TrieNode root;

		public TrieTree() {
			root = new TrieNode();
		}

		public void insert(String str) {
			if (str == null) {
				return;
			}
			char[] chs = str.toCharArray();
			TrieNode cur = root;
			cur.pass++;
			for (char c : chs) {
				int idx = (int) c;
				if (!cur.nexts.containsKey(idx)) {
					cur.nexts.put(idx, new TrieNode());
				}
				cur = cur.nexts.get(idx);
				cur.pass++;
			}
			cur.end++;
		}

		public void delete(String str) {
			if (search(str) > 0) {
				TrieNode cur = root;
				cur.pass--;
				char[] chs = str.toCharArray();
				for (char c : chs) {
					int idx = (int) c;
					if (--cur.nexts.get(idx).pass == 0) {
						cur.nexts.remove(idx);
						return;
					}
					cur = cur.nexts.get(idx);
				}
				cur.end--;
			}
		}

		public int search(String str) {
			if (str == null) {
				return 0;
			}
			char[] chs = str.toCharArray();
			TrieNode cur = root;
			for (char c : chs) {
				int idx = (int) c;
				if (cur.nexts.get(idx) == null) {
					return 0;
				}
				cur = cur.nexts.get(idx);
			}
			return cur.end;
		}

		public int prefixNumber(String str) {
			if (str == null) {
				return 0;
			}
			char[] chs = str.toCharArray();
			TrieNode cur = root;
			for (char c : chs) {
				int idx = (int) c;
				if (cur.nexts.get(idx) == null) {
					return 0;
				}
				cur = cur.nexts.get(idx);
			}
			return cur.pass;
		}
	}
}
