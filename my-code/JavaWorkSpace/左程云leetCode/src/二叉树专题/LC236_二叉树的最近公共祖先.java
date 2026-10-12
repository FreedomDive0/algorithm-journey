package 二叉树专题;

public class LC236_二叉树的最近公共祖先 {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return process(root, p, q).firstNode;
	}

	public class Infor {
		boolean findP;
		boolean findQ;
		TreeNode firstNode;

		public Infor(boolean findP, boolean findQ, TreeNode firstNode) {
			this.findP = findP;
			this.findQ = findQ;
			this.firstNode = firstNode;
		}
	}

	public Infor process(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return new Infor(false, false, null);
		}
		Infor inforLeft = process(root.left, p, q);
		Infor inforRight = process(root.right, p, q);
		boolean findP = root == p ? true : inforLeft.findP || inforRight.findP;
		boolean findQ = root == q ? true : inforLeft.findQ || inforRight.findQ;
		TreeNode firstNode = null;
		if (inforLeft.firstNode != null) {// 如果已找到赋值即可
			firstNode = inforLeft.firstNode;
		}
		if (inforRight.firstNode != null) {
			firstNode = inforRight.firstNode;
		}
		if (firstNode == null && (findP && findQ)) {
			firstNode = root;
		}
		return new Infor(findP, findQ, firstNode);
	}
}
