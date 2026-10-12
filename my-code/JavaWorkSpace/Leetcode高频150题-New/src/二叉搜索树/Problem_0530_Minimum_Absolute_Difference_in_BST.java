package 二叉搜索树;

public class Problem_0530_Minimum_Absolute_Difference_in_BST {
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

	int min = Integer.MAX_VALUE;
	int pre = -1;

	public int getMinimumDifference(TreeNode root) {
		process(root);
		return min;
	}

	public void process(TreeNode cur) {
		if (cur == null) {
			return;
		}
		process(cur.left);
		if (pre == -1) {
			pre = cur.val;
		} else {
			min = Math.min(min, cur.val - pre);
			pre = cur.val;
		}
		process(cur.right);
	}
}
