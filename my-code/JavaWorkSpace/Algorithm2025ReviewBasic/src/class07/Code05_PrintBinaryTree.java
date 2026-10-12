package class07;

public class Code05_PrintBinaryTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 打印二叉树
	public static void printTree1(Node head) {
		inProcess(head, 0, "H", 17);
	}

	public static void inProcess(Node cur, int i, String to, int len) {
		if (cur == null) {
			return;
		}
		inProcess(cur.right, i + 1, "v", len);
		String value = to + cur.value + to;
		int lLen = (len - value.length()) / 2;// 统一处理空格
		int rLen = len - lLen - value.length();
		value = generateSpace(lLen) + value + generateSpace(rLen);
		System.out.println(generateSpace(i * 17) + value);
		inProcess(cur.left, i + 1, "^", len);
	}

	public static String generateSpace(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
}
