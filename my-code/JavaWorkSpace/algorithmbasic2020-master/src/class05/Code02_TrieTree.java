package class05;

import java.util.HashMap;

// 该程序完全正确
public class Code02_TrieTree {

	public static class Node1 {
		public int pass;
		public int end;
		public Node1[] nexts;

		public Node1() {
			pass = 0;
			end = 0;
			// 0 a
			// 1 b
			// 2 c
			// .. ..
			// 25 z
			// nexts[i] == null i方向的路不存在
			// nexts[i] != null i方向的路存在
			nexts = new Node1[26];
		}
	}

	public static class Trie1 {
		private Node1 root;

		public Trie1() {
			root = new Node1();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] str = word.toCharArray();
			Node1 node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
				path = str[i] - 'a'; // 由字符，对应成走向哪条路
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node1();
				}
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		public void delete(String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				Node1 node = root;
				node.pass--;
				int path = 0;
				for (int i = 0; i < chs.length; i++) {
					path = chs[i] - 'a';
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		// word这个单词之前加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		// 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

	public static class Node2 {
		public int pass;
		public int end;
		public HashMap<Integer, Node2> nexts;

		public Node2() {
			pass = 0;
			end = 0;
			nexts = new HashMap<>();
		}
	}

	public static class Trie2 {
		private Node2 root;

		public Trie2() {
			root = new Node2();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			Node2 node = root;
			node.pass++;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = (int) chs[i];
				if (!node.nexts.containsKey(index)) {
					node.nexts.put(index, new Node2());
				}
				node = node.nexts.get(index);
				node.pass++;
			}
			node.end++;
		}

		public void delete(String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				Node2 node = root;
				node.pass--;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = (int) chs[i];
					if (--node.nexts.get(index).pass == 0) {
						node.nexts.remove(index);
						return;
					}
					node = node.nexts.get(index);
				}
				node.end--;
			}
		}

		// word这个单词之前加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node2 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = (int) chs[i];
				if (!node.nexts.containsKey(index)) {
					return 0;
				}
				node = node.nexts.get(index);
			}
			return node.end;
		}

		// 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node2 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = (int) chs[i];
				if (!node.nexts.containsKey(index)) {
					return 0;
				}
				node = node.nexts.get(index);
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

	// 测试
	public static class Node {
		int pass;
		int end;
		Node[] nexts;

		public Node() {
			this.pass = 0;
			this.end = 0;
			this.nexts = new Node[26];
		}

	}

	public static class TrieTree {
		Node root;

		public TrieTree() {
			this.root = new Node();
		}

		public void add(String word) {
			if (word == null || word.equals("")) {
				return;
			}
			Node cur = root;
			cur.pass++;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (cur.nexts[path] == null) {
					cur.nexts[path] = new Node();
				}
				cur.nexts[path].pass++;
				cur = cur.nexts[path];
			}
			cur.end++;
		}

		public int search(String word) {
			if (word == null || word.equals("")) {
				return 0;
			}
			Node cur = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (cur.nexts[path] == null) {
					return 0;
				}
				cur = cur.nexts[path];
			}
			return cur.end;
		}

		public void delete(String word) {
			if (search(word) > 0) {
				Node cur = root;
				cur.pass--;
				char[] str = word.toCharArray();
				for (int i = 0; i < str.length; i++) {
					int path = str[i] - 'a';
					if (--cur.nexts[path].pass == 0) {
						cur.nexts[path] = null;
						return;
					}
					cur = cur.nexts[path];
				}
				cur.end--;
			}
		}

		public int pretrieWord(String word) {
			if (word == null || word.equals("")) {
				return 0;
			}
			Node cur = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (cur.nexts[path] == null) {
					return 0;
				}
				cur = cur.nexts[path];
			}
			return cur.pass;
		}
	}

	// 测试2026
	public static class TrieNode {
		public TrieNode[] nexts;
		public int pass;
		public int end;

		public TrieNode() {
			nexts = new TrieNode[26];
			pass = 0;
			end = 0;
		}

	}

	public static class Trie {
		TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void add(String str) {
			if (str == null || str.length() < 1)
				return;
			char[] chs = str.toCharArray();
			TrieNode cur = root;
			cur.pass++;
			for (char c : chs) {
				int idx = c - 'a';
				if (cur.nexts[idx] == null) {
					cur.nexts[idx] = new TrieNode();
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
			TrieNode cur = root;
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
			TrieNode cur = root;
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
				TrieNode cur = root;
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

	public static class TrieNode1 {
		public HashMap<Integer, TrieNode1> nexts;
		public int pass;
		public int end;

		public TrieNode1() {
			nexts = new HashMap<Integer, TrieNode1>();
			pass = 0;
			end = 0;
		}

	}

	public static class TrieTree2 {
		TrieNode1 root;

		public TrieTree2() {
			root = new TrieNode1();
		}

		public void insert(String str) {
			if (str == null) {
				return;
			}
			char[] chs = str.toCharArray();
			TrieNode1 cur = root;
			cur.pass++;
			for (char c : chs) {
				int idx = (int) c;
				if (!cur.nexts.containsKey(idx)) {
					cur.nexts.put(idx, new TrieNode1());
				}
				cur = cur.nexts.get(idx);
				cur.pass++;
			}
			cur.end++;
		}

		public void delete(String str) {
			if (search(str) > 0) {
				TrieNode1 cur = root;
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
			TrieNode1 cur = root;
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
			TrieNode1 cur = root;
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

	public static void main(String[] args) {
		int arrLen = 100;
		int strLen = 20;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			String[] arr = generateRandomStringArray(arrLen, strLen);
			TrieTree trie1 = new TrieTree();
			TrieTree2 trie2 = new TrieTree2();
			Right right = new Right();
			for (int j = 0; j < arr.length; j++) {
				double decide = Math.random();
				if (decide < 0.25) {
					trie1.add(arr[j]);
					trie2.insert(arr[j]);
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
					int ans2 = trie2.prefixNumber(arr[j]);
					int ans3 = right.prefixNumber(arr[j]);
					if (ans1 != ans2 || ans2 != ans3) {
						System.out.println("Oops1!");
					}
				}
			}
		}
		System.out.println("finish!");

	}

}
