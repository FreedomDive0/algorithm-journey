package 二叉搜索树;

public class Problem_0230_Kth_Smallest_Element_in_a_BST {
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

	public int kthSmallest(TreeNode root, int k) {
		if (root == null) {
			return -1;
		}
		TreeNode cur = root;
		TreeNode mostRight = null;
		int index = 1;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (index++ == k) {
				return cur.val;
			}
			cur = cur.right;
		}
		return -1;
	}
//	int index = 1;
//	int num = -1;
//
//	public int kthSmallest(TreeNode root, int k) {
//		process(root, k);
//		return num;
//	}
//
//	public void process(TreeNode cur, int k) {
//		if (cur == null) {
//			return;
//		}
//		process(cur.left, k);
//		if (index == k) {
//			num = cur.val;
//		}
//		index++;
//		process(cur.right, k);
//	}
}
