package 字典树;

public class Problem_0208_Implement_Trie_Prefix_Tree {
	public class Trie {
		Node root;

		public Trie() {
			root = new Node();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			Node cur = root;
			cur.pass++;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (cur.nexts[index] == null) {
					cur.nexts[index] = new Node();
				}
				cur.nexts[index].pass++;
				cur = cur.nexts[index];
			}
			cur.end++;
		}

		public boolean search(String word) {
			if (word == null) {
				return false;
			}
			Node cur = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (cur.nexts[index] == null) {
					return false;
				}
				cur = cur.nexts[index];
			}
			return cur.end > 0;
		}

		public boolean startsWith(String prefix) {
			if (prefix == null) {
				return false;
			}
			Node cur = root;
			for (int i = 0; i < prefix.length(); i++) {
				int index = prefix.charAt(i) - 'a';
				if (cur.nexts[index] == null) {
					return false;
				}
				cur = cur.nexts[index];
			}
			return cur.pass > 0;
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
