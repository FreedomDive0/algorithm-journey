package class08;

public class Code05_MaxSubBSTHead {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class BSTInfo {
		public int min;
		public int max;
		public boolean isBST;
		public int maxSize;
		public Node maxHead;

		public BSTInfo(int min, int max, boolean isBST, int maxSize, Node maxHead) {
			this.min = min;
			this.max = max;
			this.isBST = isBST;
			this.maxSize = maxSize;
			this.maxHead = maxHead;
		}

	}

	public static Node maxBSTHead(Node root) {
		if (root == null) {
			return root;
		}
		return processBST(root).maxHead;
	}

	public static BSTInfo processBST(Node node) {
		if (node == null) {
			return null;
		}
		BSTInfo leftInfo = processBST(node.left);
		BSTInfo rightInfo = processBST(node.right);
		int min = node.value;
		int max = node.value;
		boolean isBST = true;
		int maxSize = 0;
		Node maxHead = null;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			isBST = leftInfo.isBST && node.value > leftInfo.max;
			maxSize = leftInfo.maxSize;
			maxHead = leftInfo.maxHead;
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			isBST = isBST && rightInfo.isBST && node.value < rightInfo.min;
			maxHead = maxSize < rightInfo.maxSize ? rightInfo.maxHead : maxHead;
			maxSize = Math.max(maxSize, rightInfo.maxSize);
		}
		if (isBST) {
			maxSize = (leftInfo != null ? leftInfo.maxSize : 0) + (rightInfo != null ? rightInfo.maxSize : 0) + 1;
			maxHead = node;
		}
		return new BSTInfo(min, max, isBST, maxSize, maxHead);
	}
}
