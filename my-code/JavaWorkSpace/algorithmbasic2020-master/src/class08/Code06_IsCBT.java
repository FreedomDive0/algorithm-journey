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

	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
			(leaf && (l != null || r != null)) || (l == null && r != null)

			) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	public static boolean isCBT2(Node head) {
		if (head == null) {
			return true;
		}
		return process(head).isCBT;
	}

	// 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;

		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
	}

	public static Info process(Node X) {
		if (X == null) {
			return new Info(true, true, 0);
		}
		Info leftInfo = process(X.left);
		Info rightInfo = process(X.right);

		int height = Math.max(leftInfo.height, rightInfo.height) + 1;

		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

		boolean isCBT = false;
		if (isFull) {
			isCBT = true;
		} else { // 以x为头整棵树，不满
			if (leftInfo.isCBT && rightInfo.isCBT) {

				if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
					isCBT = true;
				}

			}
		}
		return new Info(isFull, isCBT, height);
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

	// 测试2026
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

	// 二叉树递归套路
	public static boolean isCBT3(Node head) {
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
		boolean isCBT = isFull || leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1
				|| rightInfo.isFull && leftInfo.isCBT && leftInfo.height == rightInfo.height + 1
				|| leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height;
		return new CBTInfo(height, isFull, isCBT);
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBTLevel(head) != isCBT3(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
