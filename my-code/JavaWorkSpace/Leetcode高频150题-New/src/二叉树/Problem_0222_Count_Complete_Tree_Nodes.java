package 二叉树;

public class Problem_0222_Count_Complete_Tree_Nodes {
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

	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return bs(root, 1, mostLeftLevel(root, 1));
	}

	public int bs(TreeNode cur, int level, int h) {
		if (level == h) {
			return 1;
		}
		if (mostLeftLevel(cur.right, level + 1) == h) {
			return (1 << (h - level)) + bs(cur.right, level + 1, h);
		} else {
			return (1 << (h - level - 1)) + bs(cur.left, level + 1, h);
		}
	}

	public int mostLeftLevel(TreeNode cur, int level) {
		while (cur != null) {
			cur = cur.left;
			level++;
		}
		return level - 1;
	}
}
