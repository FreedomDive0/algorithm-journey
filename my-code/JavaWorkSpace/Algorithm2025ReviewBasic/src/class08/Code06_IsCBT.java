package class08;

import java.util.LinkedList;
import java.util.Queue;

public class Code06_IsCBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isCBT(Node root) {
		if (root == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		boolean nonFull = false;
		boolean isCBT = true;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				if (nonFull) {
					isCBT = false;
					break;
				}
				queue.add(cur.left);
			} else {
				nonFull = true;
				if (cur.right != null) {
					isCBT = false;
					break;
				}
			}
			if (cur.right != null) {
				if (nonFull) {
					isCBT = false;
					break;
				}
				queue.add(cur.right);
			} else {
				nonFull = true;
			}
		}
		return isCBT;
	}

	// 二叉树递归套路
	public static class BCTInfo {
		public boolean isCBT;
		public boolean isFull;
		public int height;

		public BCTInfo(boolean isCBT, boolean isFull, int height) {
			this.isCBT = isCBT;
			this.isFull = isFull;
			this.height = height;
		}

	}

	public static BCTInfo processBCT(Node node) {
		if (node == null) {
			return new BCTInfo(true, true, 0);
		}
		BCTInfo leftInfo = processBCT(node.left);
		BCTInfo rightInfo = processBCT(node.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		boolean isCBT = isFull;
		if (!isFull) {
			if (leftInfo.isFull) {
				isCBT = rightInfo.isCBT
						&& (leftInfo.height == rightInfo.height || leftInfo.height == rightInfo.height + 1);// <=可能出现负数情况
			} else {
				isCBT = leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1;
			}
		}
		return new BCTInfo(isCBT, isFull, height);
	}

	public static boolean isBCT2(Node root) {
		return processBCT(root).isCBT;
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n7 = new Node(7);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.right = n7;
		System.out.println(isBCT2(n1));
	}
}
