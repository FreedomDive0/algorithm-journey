package 二叉树;

import java.util.Stack;

public class Problem_0173_Binary_Search_Tree_Iterator {
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

	class BSTIterator {
		Stack<TreeNode> stack;
		TreeNode cur;

		public BSTIterator(TreeNode root) {
			stack = new Stack<TreeNode>();
			cur = root;
		}

		public int next() {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			TreeNode ret = stack.pop();
			cur = ret.right;
			return ret.val;
		}

		public boolean hasNext() {
			return !stack.isEmpty() || cur != null;
		}
	}
}
