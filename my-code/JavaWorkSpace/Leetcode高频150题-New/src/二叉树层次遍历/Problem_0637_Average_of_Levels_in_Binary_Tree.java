package 二叉树层次遍历;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_0637_Average_of_Levels_in_Binary_Tree {
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

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> ans = new ArrayList<Double>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			double average = 0;
			for (int i = 0; i < n; i++) {
				TreeNode cur = queue.poll();
				average += cur.val;
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			ans.add(average / n);
		}
		return ans;
	}
}
