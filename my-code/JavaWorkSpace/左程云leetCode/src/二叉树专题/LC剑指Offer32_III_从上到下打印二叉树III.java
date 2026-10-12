package 二叉树专题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC剑指Offer32_III_从上到下打印二叉树III {
	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			LinkedList<Integer> temp = new LinkedList<Integer>();
			for (int i = queue.size(); i > 0; i--) {
				TreeNode node = queue.poll();
				if (res.size() % 2 == 0) {
					temp.addLast(node.val);
				} else {
					temp.addFirst(node.val);
				}
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			res.add(temp);
		}
		return res;
	}
}
