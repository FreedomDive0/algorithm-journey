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

	public static int minHeight1(Node head) {
		if (head == null) {
			return 0;
		}
		return p(head);
	}

	public static int p(Node x) {
		if (x.left == null && x.right == null) {
			return 1;
		}
		// 左右子树起码有一个不为空
		int leftH = Integer.MAX_VALUE;
		if (x.left != null) {
			leftH = p(x.left);
		}
		int rightH = Integer.MAX_VALUE;
		if (x.right != null) {
			rightH = p(x.right);
		}
		return 1 + Math.min(leftH, rightH);
	}

	// 根据morris遍历改写
	public static int minHeight2(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int curLevel = 0;
		int minHeight = Integer.MAX_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightBoardSize = 1;
				while (mostRight.right != null && mostRight.right != cur) {
					rightBoardSize++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) { // 第一次到达
					curLevel++;
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else { // 第二次到达
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.right = null;
				}
			} else { // 只有一次到达
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null && cur.right == null) {
			minHeight = Math.min(minHeight, finalRight);
		}
		return minHeight;
	}

	public static int maxHeight(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int curLevel = 0;
		int maxHeight = Integer.MIN_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightBoardSize = 1;
				while (mostRight.right != null && mostRight.right != cur) {
					rightBoardSize++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) { // 第一次到达
					curLevel++;
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else { // 第二次到达
					if (mostRight.left == null) {
						maxHeight = Math.max(maxHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.right = null;
				}
			} else { // 只有一次到达
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null && cur.right == null) {
			maxHeight = Math.max(maxHeight, finalRight);
		}
		return maxHeight;
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

	// 测试
	// 2.树的最小高度
	public static int minHeight(Node head) {// 将树按右边界划分
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int minHeight = Integer.MAX_VALUE;
		int curLevel = 0;// 不是普通点的高度 是从mostRight返回cur后 如果mostRight(会包含除了整棵树最右节点所有叶节点)是叶节点则比较高度
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				int leftHeight = 1;
				while (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					leftHeight++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					cur = cur.left;
					curLevel++;
					continue;
				} else {// (2)不为空等于cur第二次到来
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= leftHeight;
					mostRight.right = null;
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			else {
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null) {
			minHeight = Math.min(finalRight, minHeight);
		}
		return minHeight;
	}

	public static void main(String[] args) {
		int treeLevel = 7;
		int nodeMaxValue = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(treeLevel, nodeMaxValue);
			int ans1 = minHeight1(head);
			int ans2 = minHeight(head);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
//		Node head = new Node(1);
//		head.left = new Node(2);
//		head.left.left = new Node(3);
//		head.left.left.left = new Node(4);
//		head.left.left.left.left = new Node(5);
//		minHeight2(head);
		System.out.println("test finish!");

	}

}
