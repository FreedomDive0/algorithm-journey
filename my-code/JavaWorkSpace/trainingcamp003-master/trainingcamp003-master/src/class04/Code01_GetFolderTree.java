package class04;

import java.util.TreeMap;

public class Code01_GetFolderTree {

	public static class Node {
		// 上一个节点是通过哪条路，到我的
		public String path;
		// key : node下级的路 value：node在key这条路上对应的节点是什么
		public TreeMap<String, Node> nextMap;

		public Node(String p) {
			this.path = p;
			nextMap = new TreeMap<>();
		}
	}

	// folderPaths -> [ "a\b\c","a\b\s" , "a\d\e" ,"e\f\sty" ]
	public static void print(String[] folderPaths) {
		if (folderPaths == null || folderPaths.length == 0) {
			return;
		}
		// 根据所有字符串，把前缀树建立好，头节点为head
		Node head = generateFolderTree(folderPaths);

		// 打印
		printProcess(head, 0);
	}

	public static Node generateFolderTree(String[] folderPaths) {
		Node head = new Node(""); // 系统根目录, 前缀树头节点
		for (String foldPath : folderPaths) { // 拿出每一个绝对路径
			String[] paths = foldPath.split("\\\\"); // java 特性，用一个"\"做分割的意思
			Node cur = head;
			for (int i = 0; i < paths.length; i++) { // "a" , "b" ,"c"
				if (!cur.nextMap.containsKey(paths[i])) {
					cur.nextMap.put(paths[i], new Node(paths[i]));
				}
				cur = cur.nextMap.get(paths[i]);
			}
		}
		return head;
	}

	// head节点，当前在level层
	public static void printProcess(Node node, int level) {
		if (level != 0) {
			// 2 * (level - 1)
			System.out.println(get4nSpace(level) + node.path);
		}
		for (Node next : node.nextMap.values()) {
			printProcess(next, level + 1);
		}
	}

	public static String get4nSpace(int n) {
		String res = "";
		for (int i = 1; i < n; i++) {
			res += "    ";
		}
		return res;
	}

	// 测试
	// 1.String[] strs 打印蕴含目录 同一级字母顺序排列不能乱
	public static class Node1 {
		String path;
		TreeMap<String, Node1> nexts;

		public Node1(String path) {
			nexts = new TreeMap<String, Node1>();
			this.path = path;
		}

	}

	public static void printFolder(String[] folders) {
		if (folders == null || folders.length < 1) {
			return;
		}
		Node1 head = generateFolderTree1(folders);
		printFolderTree(head, 0);
	}

	public static Node1 generateFolderTree1(String[] strs) {
		Node1 head = new Node1("");
		for (int i = 0; i < strs.length; i++) {
			Node1 cur = head;
			String[] path = strs[i].split("\\\\");
			for (String str : path) {
				if (!cur.nexts.containsKey(str)) {
					cur.nexts.put(str, new Node1(str));
					cur = cur.nexts.get(str);
				} else {
					cur = cur.nexts.get(str);
				}
			}
		}
		return head;
	}

	public static void printFolderTree(Node1 head, int level) {
		if (level != 0) {
			System.out.println(generateSpace(level) + head.path);
		}
		for (Node1 node : head.nexts.values()) {
			printFolderTree(node, level + 1);
		}
	}

	public static String generateSpace(int num) {
		String space = "";
		for (int i = 1; i < num; i++) {
			space += "    ";
		}
		return space;
	}

//	a
//    b
//        c
//        d
//        e
//	c
//	d
	public static void main(String[] args) {
		// "a\b\c" '\' a,b,c
		String test = "a\\b\\cd";
		// "a\b\c" "\" a,b,c
		String[] arr = test.split("\\\\"); // \\\\ \\ \
		String[] strs = { "a\\b\\c", "a\\b\\d", "a\\b\\e", "d\\", "c\\" };
//		for (String str : arr) {
//			System.out.println(str);
//		}
		print(strs);
		printFolder(strs);
	}

}
