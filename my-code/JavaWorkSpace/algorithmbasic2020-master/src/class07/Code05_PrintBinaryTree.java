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

	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	// 测试2026
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

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);
		printTree1(head);
		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);
		printTree1(head);
		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);
		printTree1(head);
	}

}
