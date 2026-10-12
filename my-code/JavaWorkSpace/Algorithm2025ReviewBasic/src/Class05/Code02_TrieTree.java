package class05;

import java.util.HashMap;

public class Code02_TrieTree {
	public static class TrieNode1 {
		public int pass;
		public int end;
		public HashMap<Integer, TrieNode1> nexts;

		public TrieNode1() {
			pass = 0;
			end = 0;
			nexts = new HashMap<Integer, TrieNode1>();
		}

	}

	public static class TrieTree1 {
		private TrieNode1 root;

		public TrieTree1() {
			root = new TrieNode1();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			TrieNode1 cur = root;
			cur.pass++;
			char[] str = word.toCharArray();
			for (char c : str) {
				int idx = (int) c;
				if (!cur.nexts.containsKey(idx)) {
					cur.nexts.put(idx, new TrieNode1());
				}
				cur.nexts.get(idx).pass++;
				cur = cur.nexts.get(idx);
			}
			cur.end++;
		}

		public void delete(String word) {
			if (search(word) != 0) {
				TrieNode1 cur = root;
				char[] str = word.toCharArray();
				cur.pass--;
				for (char c : str) {
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

		public int search(String word) {
			if (word == null) {
				return 0;
			}
			TrieNode1 cur = root;
			char[] str = word.toCharArray();
			for (char c : str) {
				int idx = (int) c;
				if (!cur.nexts.containsKey(idx)) {
					return 0;
				}
				cur = cur.nexts.get(idx);
			}
			return cur.end;
		}

		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			TrieNode1 cur = root;
			char[] str = pre.toCharArray();
			for (char c : str) {
				int idx = (int) c;
				if (!cur.nexts.containsKey(idx)) {
					return 0;
				}
				cur = cur.nexts.get(idx);
			}
			return cur.pass;
		}
	}
}
