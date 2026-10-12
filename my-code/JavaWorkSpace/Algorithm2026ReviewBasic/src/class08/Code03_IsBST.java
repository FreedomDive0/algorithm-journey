package class08;

public class Code03_IsBST {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 是否为搜索二叉树
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		return bSTProcess(head).isBST;
	}

	public static class BSTInfo {
		public boolean isBST;
		public int min;
		public int max;

		public BSTInfo(boolean isBST, int min, int max) {
			this.isBST = isBST;
			this.min = min;
			this.max = max;
		}

	}

	public static BSTInfo bSTProcess(Node node) {
		if (node == null) {
			return null;
		}
		BSTInfo leftInfo = bSTProcess(node.left);
		BSTInfo rightInfo = bSTProcess(node.right);
		int min = node.value;
		int max = node.value;
		boolean isBST = true;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			isBST = leftInfo.max < node.value && leftInfo.isBST;// 子树也需要是搜索二叉树
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			if (isBST)
				isBST = rightInfo.min > node.value && rightInfo.isBST;
		}
		return new BSTInfo(isBST, min, max);
	}

}
