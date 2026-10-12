package 二叉树专题;

public class LC98_验证二叉搜索树 {
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

	public class Infor {
		boolean isBST;
		int max;
		int min;

		public Infor(boolean isBST, int max, int min) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
		}

	}

	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		return process(root).isBST;
	}

	public Infor process(TreeNode node) {
		if (node == null) {
			return null;
		}
		int min = node.val;
		int max = node.val;
		boolean isBST = false;
		Infor lefInfor = process(node.left);
		Infor rightInfor = process(node.right);
		if (lefInfor != null) {
//			min = Math.min(min, lefInfor.min);
			max = Math.max(max, lefInfor.max);
		}
		if (rightInfor != null) {
			min = Math.min(min, rightInfor.min);
//			max = Math.max(max, rightInfor.max);
		}
		if ((lefInfor == null ? true : lefInfor.isBST && lefInfor.max < node.val)
				&& (rightInfor == null ? true : rightInfor.isBST && rightInfor.min > node.val)) {
			isBST = true;
		}
		return new Infor(isBST, max, min);
	}

}
