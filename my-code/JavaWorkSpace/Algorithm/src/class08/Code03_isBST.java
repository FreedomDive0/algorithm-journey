package class08;

import java.util.ArrayList;

public class Code03_isBST {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class Infor {
		public boolean isBST;
		public int min;
		public int max;

		public Infor(boolean isBST, int min, int max) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
		}
	}

	public static boolean isBst(Node head) {
		if (head == null) {
			return true;
		}
		return process(head).isBST;
	}

	public static Infor process(Node node) {
		if (node == null) {
			return null;
		}
		Infor leftInfor = process(node.left);
		Infor rightInfor = process(node.right);
		int min = node.value;
		int max = node.value;
		boolean isBST = false;
		if (leftInfor != null) {
			min = Math.min(min, leftInfor.min);
			max = Math.max(max, leftInfor.max);
		}
		if (rightInfor != null) {
			min = Math.min(min, rightInfor.min);
			max = Math.max(max, rightInfor.max);
		}
		if ((leftInfor == null ? true : leftInfor.isBST && leftInfor.max < node.value)
				&& (rightInfor == null ? true : rightInfor.isBST && rightInfor.min > node.value)) {
			isBST = true;
		}
		return new Infor(isBST, min, max);
	}

//	for test
	public static boolean isBST1(Node head) {
		if (head == null) {
			return true;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return false;
			}
		}
		return true;
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
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
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isBST1(head) != isBst(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
