package 二叉树;

public class Problem_0114_Flatten_Binary_Tree_to_Linked_List {
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

	public void flatten(TreeNode root) {// 从过程分析
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left != null) {
				TreeNode predecessor = cur.left;
				while (predecessor.right != null) {
					predecessor = predecessor.right;
				}
				predecessor.right = cur.right;
				cur.right = cur.left;
				cur.left = null;
			}
			cur = cur.right;
		}
	}

}
