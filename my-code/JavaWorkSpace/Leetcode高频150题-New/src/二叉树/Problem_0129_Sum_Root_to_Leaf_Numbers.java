package 二叉树;

public class Problem_0129_Sum_Root_to_Leaf_Numbers {
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

	int sum = 0;

	public int sumNumbers(TreeNode root) {
		process(root, 0);
		return sum;
	}

	public void process(TreeNode cur, int num) {
		if (cur == null) {
			return;
		}
		if (cur.left == null && cur.right == null) {
			sum += num * 10 + cur.val;
		}
		process(cur.left, num * 10 + cur.val);
		process(cur.right, num * 10 + cur.val);
	}
}
