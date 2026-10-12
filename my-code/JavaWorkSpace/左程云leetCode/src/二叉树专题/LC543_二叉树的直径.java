package 二叉树专题;

public class LC543_二叉树的直径 {
	// 直径 就是某个节点左右树高度之和
	public int diameterOfBinaryTree(TreeNode root) {
		height(root);
		return ans;
	}

	int ans = 0;

	public int height(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		ans = Math.max(ans, leftHeight + rightHeight);
		// 返回该节点的高度
		return Math.max(leftHeight, rightHeight) + 1;
	}
}
