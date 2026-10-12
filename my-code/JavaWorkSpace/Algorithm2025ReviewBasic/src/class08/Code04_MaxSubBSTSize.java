package class08;

public class Code04_MaxSubBSTSize {
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int data) {
			this.val = data;
		}
	}

	public static class BSTInfo {
		public int min;
		public int max;
		public int maxSize;
		public boolean isBST;

		public BSTInfo(int min, int max, int maxSize, boolean isBST) {
			this.min = min;
			this.max = max;
			this.maxSize = maxSize;
			this.isBST = isBST;
		}

	}

	public static int largestBSTSubtree(TreeNode root) {
		if (root == null)
			return 0;
		return processBST(root).maxSize;
	}

	public static BSTInfo processBST(TreeNode node) {
		if (node == null) {
			return null;
		}
		BSTInfo leftInfo = processBST(node.left);
		BSTInfo rightInfo = processBST(node.right);
		int min = node.val;
		int max = node.val;
		int maxSize = 0;
		boolean isBST = true;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			isBST = leftInfo.isBST && node.val > leftInfo.max;
			maxSize = leftInfo.maxSize;// 注意maxSize
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			isBST = isBST && rightInfo.isBST && node.val < rightInfo.min;
			maxSize = Math.max(maxSize, rightInfo.maxSize);
		}
		maxSize = isBST ? (leftInfo != null ? leftInfo.maxSize : 0) + (rightInfo != null ? rightInfo.maxSize : 0) + 1
				: maxSize;
		return new BSTInfo(min, max, maxSize, isBST);
	}
}
