package 二叉树专题;

import java.util.ArrayList;

public class LC173_二叉搜索树迭代器 {
	class BSTIterator {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int index = 0;

		public BSTIterator(TreeNode root) {
			process(root);
		}

		public void process(TreeNode root) {
			if (root == null) {
				return;
			}
			process(root.left);
			list.add(root.val);
			process(root.right);
		}

		public int next() {
			return list.get(index++);
		}

		public boolean hasNext() {
			if (index < list.size()) {
				return true;
			}
			return false;
		}
	}
}
