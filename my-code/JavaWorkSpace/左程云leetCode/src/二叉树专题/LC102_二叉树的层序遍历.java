package 二叉树专题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LC102_二叉树的层序遍历 {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (root == null) {
			return list;
		}
		HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
		map.put(root, 0);
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = (TreeNode) queue.poll();
			int curLevel = map.get(node);
			if (curLevel >= list.size()) {
				List<Integer> tmp = new ArrayList<Integer>();
				tmp.add(node.val);
				list.add(tmp);
			} else {
				list.get(curLevel).add(node.val);
			}
			if (node.left != null) {
				queue.add(node.left);
				map.put(node.left, curLevel + 1);
			}
			if (node.right != null) {
				queue.add(node.right);
				map.put(node.right, curLevel + 1);
			}
		}
		return list;
	}

}
