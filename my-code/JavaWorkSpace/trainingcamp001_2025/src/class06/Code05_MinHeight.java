package class06;

public class Code05_MinHeight {
	public static class Node {
		public int val;
		public Node left;
		public Node right;

		public Node(int x) {
			val = x;
		}
	}

	// 1.二叉树的递归套路
	public static int minHeight1(Node head) {
		if (head == null) {
			return 0;
		}
		return minProcess(head);
	}

	public static int minProcess(Node cur) {
		if (cur.left == null && cur.right == null) {// 不能使用空节点的返回值 最底层得是叶节点 向上返回才有意义
			return 1;
		}
		int leftInfo = Integer.MAX_VALUE;
		if (cur.left != null) {
			leftInfo = minProcess(cur.left);
		}
		int rightInfo = Integer.MAX_VALUE;
		if (cur.right != null) {
			rightInfo = minProcess(cur.right);
		}
		int minHeight = Math.min(leftInfo, rightInfo) + 1;
		return minHeight;
	}

	// 2.morris遍历解决
	public static int minHeight2(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int curLevel = 0;
		int minHeight = Integer.MAX_VALUE;
		while (cur != null) {
			if (cur.left != null) {
				mostRight = cur.left;
				int mostRightHeight = 1;
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
					mostRightHeight++;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					curLevel++;
					continue;
				} else {
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= mostRightHeight;
					mostRight.right = null;
				}
			} else {
				curLevel++;
			}
			cur = cur.right;
		}
		cur = head;
		curLevel = 1;
		while (cur.right != null) {
			cur = cur.right;
			curLevel++;
		}
		if (cur.left == null) {
			minHeight = Math.min(minHeight, curLevel);
		}
		return minHeight;
	}
}
