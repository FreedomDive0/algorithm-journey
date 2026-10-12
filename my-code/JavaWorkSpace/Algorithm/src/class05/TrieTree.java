package class05;

import java.util.HashMap;

public class TrieTree {
	// 数组实现
	public static class Node1 {
		public int pass;
		public int end;
		public Node1[] next;

		public Node1() {
			pass = 0;
			end = 0;
			next = new Node1[26];// path
		}
	}

	public static class trieTree {
		public Node1 root;

		public trieTree() {
			root = new Node1();
		}

		public void add(String word) {
			if (word == null) {
				return;
			}
			Node1 node = root;
			node.pass++;// 给根加
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (node.next[path] == null) {
					node.next[path] = new Node1();
				}
				node.next[path].pass++;// 先给next++
				node = node.next[path];// 再去往next
			}
			node.end++;
		}

		public int search(String word) {
			if (word == null) {
				return 0;
			}
			Node1 node = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (node.next[path] == null) {
					return 0;
				}
				node = node.next[path];
			}
			return node.end;
		}

		public void delete(String word) {
			if (word == null) {
				return;
			}
			if (search(word) != 0) {
				Node1 node = root;
				node.pass--;
				char[] str = word.toCharArray();
				for (int i = 0; i < str.length; i++) {
					int path = str[i] - 'a';
					if (--node.next[path].pass == 0) {
						node.next[path] = null;
						return;
					}
					node = node.next[path];
				}
				node.end--;
			}
		}

		// 有多少个单词用这个前缀
		public int pretrieWord(String word) {
			if (word == null) {
				return 0;
			}
			Node1 node = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (node.next[path] == null) {
					return 0;
				}
				node = node.next[path];
			}
			return node.pass;
		}
	}

//	HashMap实现
	public static class Node2 {
		public int pass;
		public int end;
		public HashMap<Integer, Node2> next;

		public Node2() {
			pass = 0;
			end = 0;
			next = new HashMap<Integer, Node2>();
		}
	}

	public static class trieTree2 {
		Node2 root;

		public trieTree2() {
			root = new Node2();
		}

		public void add(String word) {
			if (word == null) {
				return;
			}
			Node2 node = root;
			node.pass++;
			char[] str = word.toCharArray();
			int path = 0;
			for (int i = 0; i < str.length; i++) {
				path = (int) str[i];
				if (!node.next.containsKey(path)) {
					node.next.put(path, new Node2());
				}
				node.next.get(path).pass++;
				node = node.next.get(path);
			}
			node.end++;
		}

		public int search(String word) {
			if (word == null) {
				return 0;
			}
			Node2 node = root;
			char[] str = word.toCharArray();
			int path = 0;
			for (int i = 0; i < str.length; i++) {
				path = (int) str[i];
				if (!node.next.containsKey(path)) {
					return 0;
				}
				node = node.next.get(path);
			}
			return node.end;
		}

		public void delete(String word) {
			if (word == null) {
				return;
			}
			if (search(word) != 0) {
				Node2 node = root;
				node.pass--;
				char[] str = word.toCharArray();
				int path = 0;
				for (int i = 0; i < str.length; i++) {
					path = (int) str[i];
					if (!node.next.containsKey(path)) {
						return;
					}
					node.next.get(path).pass--;
					node = node.next.get(path);
				}
				node.end--;
			}
		}

		public int pretrieWord(String word) {
			if (word == null) {
				return 0;
			}
			Node2 node = root;
			char[] str = word.toCharArray();
			int path = 0;
			for (int i = 0; i < str.length; i++) {
				path = (int) str[i];
				if (!node.next.containsKey(path)) {
					return 0;
				}
				node = node.next.get(path);
			}
			return node.pass;
		}
	}

	public static class Right {

		private HashMap<String, Integer> box;

		public Right() {
			box = new HashMap<>();
		}

		public void insert(String word) {
			if (!box.containsKey(word)) {
				box.put(word, 1);
			} else {
				box.put(word, box.get(word) + 1);
			}
		}

		public void delete(String word) {
			if (box.containsKey(word)) {
				if (box.get(word) == 1) {
					box.remove(word);
				} else {
					box.put(word, box.get(word) - 1);
				}
			}
		}

		public int search(String word) {
			if (!box.containsKey(word)) {
				return 0;
			} else {
				return box.get(word);
			}
		}

		public int prefixNumber(String pre) {
			int count = 0;
			for (String cur : box.keySet()) {
				if (cur.startsWith(pre)) {
					count += box.get(cur);
				}
			}
			return count;
		}
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 6);
			ans[i] = (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	public static void main(String[] args) {
		int arrLen = 100;
		int strLen = 20;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			String[] arr = generateRandomStringArray(arrLen, strLen);
			trieTree trie1 = new trieTree();
			trieTree2 trie2 = new trieTree2();
			Right right = new Right();
			for (int j = 0; j < arr.length; j++) {
				double decide = Math.random();
				if (decide < 0.25) {
					trie1.add(arr[j]);
					trie2.add(arr[j]);
					right.insert(arr[j]);
				} else if (decide < 0.5) {
					trie1.delete(arr[j]);
					trie2.delete(arr[j]);
					right.delete(arr[j]);
				} else if (decide < 0.75) {
					int ans1 = trie1.search(arr[j]);
					int ans2 = trie2.search(arr[j]);
					int ans3 = right.search(arr[j]);
					if (ans1 != ans2 || ans2 != ans3) {
						System.out.println("Oops!");
					}
				} else {
					int ans1 = trie1.pretrieWord(arr[j]);
					int ans2 = trie2.pretrieWord(arr[j]);
					int ans3 = right.prefixNumber(arr[j]);
					if (ans1 != ans2 || ans2 != ans3) {
						System.out.println("Oops!");
					}
				}
			}
		}
		System.out.println("finish!");

	}
}
