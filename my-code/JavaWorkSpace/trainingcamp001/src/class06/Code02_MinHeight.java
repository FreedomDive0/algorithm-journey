package class06;

public class Code02_MinHeight {
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

	public static int minHeight2(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int min = Integer.MAX_VALUE;
		int curLevel = 0;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightBoardSize = 1;// 右子树高度
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;// 找到左树最右孩子
					rightBoardSize++;
				}
				if (mostRight.right == null) {// 第一次找到
					mostRight.right = cur;
					cur = cur.left;
					curLevel++;
					continue;// 跳过该回合
				} else {// R!=null -> R == cur
					mostRight.right = null;
					if (mostRight.left == null) {
						min = Math.min(min, curLevel);
					}
					curLevel -= rightBoardSize;
				}
			} else {
				curLevel++;
			}
			cur = cur.right;// 1.没有左孩子 or 2.第二遍到达自身
		}
		// 检测最右侧
		int nodeRight = 1;
		cur = head;
		while (cur.right != null) {
			nodeRight++;
			cur = cur.right;
		}
		if (cur.left == null && cur.right == null) {// 最右节点也为叶节点才能比较
			min = Math.min(min, nodeRight);
		}
		return min;
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

	// 复习
	// 4.二叉树的minHeight
	public static int minHeight(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int curLevel = 0;
		int min = Integer.MAX_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightEdge = 1;// ∵cur.left!=null ∴初始值为1
				while (mostRight.right != null && mostRight.right != cur) {
					rightEdge++;// 每次从孩子节点返回会重新计算高度
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					cur = cur.left;
					curLevel++;// 无论向左还是向右都是向下移动 需要层数++
					continue;
				} else {// 从右孩子返回时结算高度 判断mostRight是不是叶节点
					if (mostRight.left == null) {
						min = Math.min(min, curLevel);
					}
					curLevel -= rightEdge;
					mostRight.right = null;
				}
			} else {
				curLevel++;// 无论向左还是向右都是向下移动 需要层数++
			}
			cur = cur.right;
		}
		// 检测最右边界
		int rightEdge = 1;
		cur = head;
		while (cur.right != null) {
			cur = cur.right;
			rightEdge++;
		}
		if (cur.left == null && cur.right == null) {
			min = Math.min(min, rightEdge);
		}
		return min;
	}

	public static void main(String[] args) {
		int treeLevel = 7;
		int nodeMaxValue = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(treeLevel, nodeMaxValue);
			int ans1 = minHeight(head);
			int ans2 = minHeight2(head);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}
}
