package class08;

import java.util.LinkedList;
import java.util.Queue;

public class Code06_isCBT {
	public static class Node extends class07.Code05_PrintTree.Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			super(data);
		}
//		public Node(int data) {
//			this.value = data;
//		}
	}

	public static class Infor {
		public boolean isFull;
		public boolean isCBT;
		public int height;

		public Infor(boolean isFull, boolean isCBT, int height) {
			this.height = height;
			this.isCBT = isCBT;
			this.isFull = isFull;
		}
	}

	public static boolean isCBT(Node head) {
		return process(head).isCBT;
	}

	public static Infor process(Node node) {
		if (node == null) {
			return new Infor(true, true, 0);
		}
		Infor leftInfor = process(node.left);
		Infor rightInfor = process(node.right);
		int height = Math.max(leftInfor.height, rightInfor.height) + 1;
		boolean isFull = leftInfor.isFull && rightInfor.isFull && leftInfor.height == rightInfor.height;
		boolean isCBT = false;
		if (isFull || (leftInfor.isCBT && rightInfor.isFull && leftInfor.height == rightInfor.height + 1)
				|| (leftInfor.isFull && rightInfor.isCBT && leftInfor.height == rightInfor.height)
				|| (leftInfor.isFull && rightInfor.isCBT && leftInfor.height == rightInfor.height)) {
			isCBT = true;
		}
		return new Infor(isFull, isCBT, height);
	}

//	for print
	public static void printTree(Node head) {
		print(head, 1, 17, "H");
	}

	public static void print(Node node, int level, int len, String sign) {
		if (node == null) {
			return;
		}
		print(node.right, level + 1, len, "v");
		String value = sign + node.value + sign;
		int left = (len - value.length()) / 2;
		int right = len - left - value.length();
		value = getSpace((level - 1) * len) + getSpace(left) + value + getSpace(right);
		System.out.println(value);
		print(node.left, level + 1, len, "^");
	}

	public static String getSpace(int len) {
		String space = " ";
		for (int i = 0; i < len - 1; i++) {
			space = space + " ";
		}
		return space;
//		String space = " ";
//		StringBuffer buf = new StringBuffer("");
//		for (int i = 0; i < len; i++) {
//			buf.append(space);
//		}
//		return buf.toString();
	}

//	for test
	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Node l;
		Node r;
		boolean noCompNode = false;
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			l = cur.left;
			r = cur.right;
			if (noCompNode && (l != null || r != null) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				noCompNode = true;
			}
		}
		return true;
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		Node head = null;
		for (int i = 0; i < testTimes; i++) {
			head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT(head)) {
				System.out.println("Oops!");
				printTree(head);
				break;
			}
		}
		System.out.println("finish!");
	}
}
