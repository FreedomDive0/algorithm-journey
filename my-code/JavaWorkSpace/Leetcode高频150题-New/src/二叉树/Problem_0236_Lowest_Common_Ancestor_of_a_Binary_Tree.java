package 二叉树;

public class Problem_0236_Lowest_Common_Ancestor_of_a_Binary_Tree {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class AncestorInfo {
		boolean findP;
		boolean findQ;
		TreeNode ans;

		public AncestorInfo(boolean findP, boolean findQ, TreeNode ans) {
			this.findP = findP;
			this.findQ = findQ;
			this.ans = ans;
		}

	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return process(root, p, q).ans;
	}

	public AncestorInfo process(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return new AncestorInfo(false, false, null);
		}
		AncestorInfo leftInfo = process(root.left, p, q);
		AncestorInfo rightInfo = process(root.right, p, q);
		TreeNode ans = null;
		boolean findP = leftInfo.findP || rightInfo.findP || root == p;
		boolean findQ = leftInfo.findQ || rightInfo.findQ || root == q;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		}
		if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		}
		if (ans == null && findP && findQ) {
			ans = root;
		}
		return new AncestorInfo(findP, findQ, ans);
	}
}
