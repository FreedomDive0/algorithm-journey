package 字典树;

public class Problem_0211_Design_Add_and_Search_Words_Data_Structure {
	class WordDictionary {
		Node root;

		public WordDictionary() {
			root = new Node();
		}

		public void addWord(String word) {
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
			return dfs(word, 0, root);
		}

		public boolean dfs(String word, int index, Node node) {
			if (index == word.length()) {
				return node.end > 0;
			}
			if (word.charAt(index) != '.') {
				int i = word.charAt(index) - 'a';
				if (node.nexts[index] != null && dfs(word, index + 1, node.nexts[index])) {
					return true;
				}
			} else {
				for (int i = 0; i < 26; i++) {
					if (node.nexts[i] != null && dfs(word, index + 1, node.nexts[i])) {
						return true;
					}
				}
			}
			return false;
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
