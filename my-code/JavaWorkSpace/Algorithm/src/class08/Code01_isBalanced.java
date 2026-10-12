package class08;

public class Code01_isBalanced {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class Infor {
		public boolean balanced;
		public int height;

		public Infor(boolean balanced, int height) {
			this.balanced = balanced;
			this.height = height;
		}
	}

	public static boolean isBalanced(Node head) {
		return isB(head).balanced;
	}

	public static Infor isB(Node node) {
		if (node == null) {
			return new Infor(true, 0);
		}
		Infor inforLeft = isB(node.left);
		Infor inforRight = isB(node.right);
		return new Infor(
				inforLeft.balanced && inforRight.balanced && Math.abs(inforLeft.height - inforRight.height) < 2,
				Math.max(inforLeft.height, inforRight.height) + 1);
	}

	// for test

	public static boolean isBalanced1(Node head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	public static int process1(Node head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}

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
	public static class isBSTInfor {
		boolean isbst;
		int max;

		public isBSTInfor(boolean isbst, int max) {
			this.isbst = isbst;
			this.max = max;
		}

	}

	public static boolean isBST(Node root) {
		return processIsBST(root).isbst;
	}

	public static isBSTInfor processIsBST(Node root) {
		if (root == null) {
			return new isBSTInfor(true, 0);
		}
		isBSTInfor leftInfo = processIsBST(root.left);
		isBSTInfor rightInfo = processIsBST(root.right);
		if (leftInfo.isbst && rightInfo.isbst && Math.abs(leftInfo.max - rightInfo.max) < 2) {
			return new isBSTInfor(true, Math.max(leftInfo.max, rightInfo.max) + 1);
		}
		return new isBSTInfor(false, Math.max(leftInfo.max, rightInfo.max) + 1);
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isBST(head) != isBalanced(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
