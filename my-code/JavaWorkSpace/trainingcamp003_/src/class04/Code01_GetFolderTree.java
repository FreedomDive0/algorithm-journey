package class04;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Code01_GetFolderTree {
	public static void printFolder(String[] strs) {
		if (strs == null || strs.length < 1) {
			return;
		}
		Node head = generateNodeTree(strs);
		print(head, 0);
	}

	public static class Node {
		String value;
		TreeMap<String, Node> nexts;

		public Node(String value) {
			this.value = value;
			this.nexts = new TreeMap<String, Node>();
		}

	}

	public static Node generateNodeTree(String[] strs) {
		Node head = new Node("");
		for (String path : strs) {// 遍历多个路径
			String[] catalog = path.split("\\\\");// 将路径拆分为文件夹
			Node cur = head;
			for (String folder : catalog) {// 不存在该路径新建
				if (!cur.nexts.containsKey(folder)) {
					cur.nexts.put(folder, new Node(folder));
				}
				cur = cur.nexts.get(folder);// 前缀树向下移动
			}
		}
		return head;
	}

	public static void print(Node head, int level) {
		if (head == null) {
			return;
		}
		for (Entry<String, Node> entry : head.nexts.entrySet()) {
			System.out.println(generateSpace(level) + entry.getKey());
			print(entry.getValue(), level + 1);
		}
	}

	public static String generateSpace(int level) {
		if (level == 0) {
			return "";
		}
		String ans = "";
		for (int i = 0; i < level; i++) {
			ans += "    ";
		}
		return ans;
	}

	public static void main(String[] args) {
		String[] strs = { "a\\b\\c", "a\\b\\d", "a\\b\\e", "d\\", "c\\" };
		printFolder(strs);
	}
}
