package 二叉树层次遍历;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_0103_Binary_Tree_Zigzag_Level_Order_Traversal {
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

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		boolean isHead = true;
		while (!queue.isEmpty()) {
			int n = queue.size();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				TreeNode cur = isHead ? queue.pollFirst() : queue.pollLast();
				list.add(cur.val);
				if (isHead) {
					if (cur.left != null) {
						queue.addLast(cur.left);
					}
					if (cur.right != null) {
						queue.addLast(cur.right);
					}
				} else {
					if (cur.right != null) {
						queue.addFirst(cur.right);
					}
					if (cur.left != null) {
						queue.addFirst(cur.left);
					}
				}
			}
			isHead = !isHead;
			ans.add(list);
		}
		return ans;
	}
}
