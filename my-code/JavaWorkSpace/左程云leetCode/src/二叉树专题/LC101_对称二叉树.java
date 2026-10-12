package 二叉树专题;

public class LC101_对称二叉树 {
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		dfs(root.left, root.right);
		return true;
	}

	public boolean dfs(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		return dfs(left.left, right.right) && dfs(left.right, right.left);
	}
}
