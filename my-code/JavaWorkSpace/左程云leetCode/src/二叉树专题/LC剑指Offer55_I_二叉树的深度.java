package 二叉树专题;

public class LC剑指Offer55_I_二叉树的深度 {
	public int maxDepth(TreeNode root) {
		return height(root);
	}

	public int height(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}
}
