package 二叉树;

public class Problem_0124_Binary_Tree_Maximum_Path_Sum {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	class maxInfo {
		int headMax;
		int max;

		public maxInfo(int headMax, int max) {
			this.headMax = headMax;
			this.max = max;
		}

	}

	public int maxPathSum(TreeNode root) {
		return process(root).max;
	}

	public maxInfo process(TreeNode root) {
		if (root == null) {
			return null;
		}
		maxInfo leftInfo = process(root.left);
		maxInfo rightInfo = process(root.right);
		int headMax = root.val;
		int max = root.val;
		if (leftInfo != null) {
			headMax = Math.max(headMax, leftInfo.headMax + root.val);
			max = Math.max(max, leftInfo.max);
		}
		if (rightInfo != null) {
			headMax = Math.max(headMax, rightInfo.headMax + root.val);
			max = Math.max(max, rightInfo.max);
		}
		max = Math.max(max, headMax);
		if (leftInfo != null && rightInfo != null) {
			max = Math.max(max, leftInfo.headMax + rightInfo.headMax + root.val);
		}
		return new maxInfo(headMax, max);
	}
}
