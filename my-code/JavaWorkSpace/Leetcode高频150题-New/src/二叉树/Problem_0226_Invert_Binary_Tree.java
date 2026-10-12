package 二叉树;

public class Problem_0226_Invert_Binary_Tree {
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

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode right = root.right;
		root.right = root.left;
		root.left = right;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
}
