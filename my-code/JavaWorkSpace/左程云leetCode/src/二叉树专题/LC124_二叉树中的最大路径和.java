package 二叉树专题;

public class LC124_二叉树中的最大路径和 {
//	public int maxPathSum(TreeNode root) {
//		process(root);
//		return max;
//	}
//
//	int max = Integer.MIN_VALUE;
//
//	public Infor process(TreeNode node) {
//		if (node == null) {
//			return new Infor(0, 0);
//		}
//		int leftMax = 0;
//		int rightMax = 0;
//		Infor leftInfor = process(node.left);
//		Infor rightInfor = process(node.right);
//		leftMax = Math.max(leftInfor.leftMax<0?0:leftInfor.leftMax, leftInfor.rightMax<0?0:leftInfor.rightMax) + node.val;
//		rightMax = Math.max(rightInfor.leftMax<0?0:rightInfor.leftMax, rightInfor.rightMax<0?0:rightInfor.rightMax) + node.val;
//		max = Math.max(leftMax + rightMax - node.val, max);
//		return new Infor(leftMax, rightMax);
//	}
//
//	public class Infor {
//		int leftMax;
//		int rightMax;
//
//		public Infor(int leftmax, int rightmax) {
//			this.leftMax = leftmax;
//			this.rightMax = rightmax;
//		}
//	}
	int res = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		dfs(root);
		return res;
	}

	// 核心是选择路径
	public int dfs(TreeNode root) {
		if (root == null)
			return 0;
		int left = Math.max(0, dfs(root.left));// 排除小于0的路
		int right = Math.max(0, dfs(root.right));
		res = Math.max(res, root.val + left + right);
		return root.val + Math.max(left, right);
	}

}
