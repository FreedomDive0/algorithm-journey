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
		System.out.println("Binary Tree1:");
		printProcess(head, "H", 0, 17);
		System.out.println();
	}

	public static void printProcess(Node node, String sign, int height, int len) {
		if (node == null) {
			return;
		}
		printProcess(node.right, "v", height + 1, len);
		String cur = sign + node.value + sign;
		int leftSpace = (len - cur.length()) >> 1;
		int rightSpace = len - leftSpace - cur.length();
		System.out.println(generateSpace(len * height) + generateSpace(leftSpace) + cur + generateSpace(rightSpace));
		printProcess(node.left, "^", height + 1, len);
	}

	public static String generateSpace(int len) {
		String space = " ";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(space);
		}
		return sb.toString();
	}
}
