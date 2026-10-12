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

	// 二叉树递归套路
	public static boolean isCBT(Node head) {
		return cBTProcess(head).isCBT;
	}

	public static class CBTInfo {
		public int height;
		public boolean isFull;
		public boolean isCBT;

		public CBTInfo(int height, boolean isFull, boolean isCBT) {
			this.height = height;
			this.isFull = isFull;
			this.isCBT = isCBT;
		}

	}

	public static CBTInfo cBTProcess(Node node) {
		if (node == null) {
			return new CBTInfo(0, true, true);
		}
		CBTInfo leftInfo = cBTProcess(node.left);
		CBTInfo rightInfo = cBTProcess(node.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		// 1.满二叉树 2.左满右满左高一 3.完全二叉树在左边 右满左高一 4.完全二叉树在右边 等高
		boolean isCBT = isFull || leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1
				|| rightInfo.isFull && leftInfo.isCBT && leftInfo.height == rightInfo.height + 1
				|| leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height;
		return new CBTInfo(height, isFull, isCBT);
	}

	// 层序遍历
	public static boolean isCBTLevel(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		boolean isNotBoth = false;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (isNotBoth && (cur.left != null || cur.right != null) || cur.left == null && cur.right != null) {
				return false;
			}
			if (cur.left != null) {
				queue.add(cur.left);
			} else {
				isNotBoth = true;
			}
			if (cur.right != null) {
				queue.add(cur.right);
			} else {
				isNotBoth = true;
			}
		}
		return true;
	}
}
