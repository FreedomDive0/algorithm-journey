package topinterviewquestions;

public class Problem_0687_LongestUnivaluePath {
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	public static int longestUnivaluePath(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return process(root).max - 1;
	}

	public static class Info {
		int len;// 必须以X出发的最大路径
		int max;// 不必须以X出发最大路径

		public Info(int len, int max) {
			this.len = len;
			this.max = max;
		}

	}

//	???
//	public static Info process(TreeNode head) {
//		if (head == null) {
//			return new Info(0, 0);
//		}
//		Info leftInfo = process(head.left);
//		Info rightInfo = process(head.right);
//		int len = 1;
//		int max = 1;
//		if (head.left != null && head.left.val == head.val) {
//			len += leftInfo.len;
//		}
//		if (head.right != null && head.right.val == head.val) {
//			len += rightInfo.len;
//		}
//		max = Math.max(leftInfo.max, Math.max(rightInfo.max, len));
//		return new Info(Math.max(leftInfo.len, rightInfo.len) + 1, max);
//	}
	public static Info process(TreeNode head) {
		if (head == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int len = 1;
		int max = 1;
		if (head.left != null && head.left.val == head.val) {
			len = leftInfo.len + 1;
		}
		if (head.right != null && head.right.val == head.val) {
			len = Math.max(len, rightInfo.len + 1);
		}
		max = Math.max(leftInfo.max, Math.max(rightInfo.max, len));
		if (head.left != null && head.right != null && head.left.val == head.val && head.right.val == head.val) {
			max = Math.max(max, leftInfo.len + rightInfo.len + 1);
		}
		return new Info(len, max);
	}
}
