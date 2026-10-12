package class05;

public class Code01_TrieTree {
	public static class TrieNode {
		public int pass;
		public int end;
		public TrieNode[] nexts;

		public TrieNode() {
			pass = 0;
			end = 0;
			nexts = new TrieNode[26];
		}

	}

	public static class TrieTree {
		private TrieNode root;

		public TrieTree() {
			root = new TrieNode();
		}

		public void insert(String word) {
			if (word == null)
				return;
			TrieNode cur = root;
			char[] str = word.toCharArray();
			cur.pass++;
			for (char c : str) {
				if (cur.nexts[c - 'a'] == null) {
					cur.nexts[c - 'a'] = new TrieNode();
				}
				cur.nexts[c - 'a'].pass++;
				cur = cur.nexts[c - 'a'];
			}
			cur.end++;
		}

		public void delete(String word) {
			if (search(word) != 0) {
				char[] str = word.toCharArray();
				TrieNode cur = root;
				cur.pass--;
				for (char c : str) {
					if (--cur.nexts[c - 'a'].pass == 0) {
						cur.nexts[c - 'a'] = null;
						return;
					}
					cur = cur.nexts[c - 'a'];
				}
				cur.end--;
			}
		}

		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] str = word.toCharArray();
			TrieNode cur = root;
			for (char c : str) {
				if (cur.nexts[c - 'a'] == null) {
					return 0;
				}
				cur = cur.nexts[c - 'a'];
			}
			return cur.end;
		}

		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] str = pre.toCharArray();
			TrieNode cur = root;
			for (char c : str) {
				if (cur.nexts[c - 'a'] == null) {
					return 0;
				}
				cur = cur.nexts[c - 'a'];
			}
			return cur.pass;
		}

	}
}
