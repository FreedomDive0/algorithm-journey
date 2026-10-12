package 字符串专题;

import java.util.ArrayList;

public class LC208_实现Trie前缀树 {
	class Trie {
		ArrayList<String> list;

		public Trie() {
			list = new ArrayList<String>();
		}

		public void insert(String word) {
			list.add(word);
		}

		public boolean search(String word) {
			for (String s : list) {
				if (s.equals(word)) {
					return true;
				}
			}
			return false;
		}

		public boolean startsWith(String prefix) {
			for (String s : list) {
				if (s.startsWith(prefix)) {
					return true;
				}
			}
			return false;
		}
	}
}
