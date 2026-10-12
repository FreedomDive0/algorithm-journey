package 二叉树专题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC199_二叉树的右视图 {
//	ArrayList<Integer> list = new ArrayList<Integer>();
//	int index = 0;
//
//	public List<Integer> rightSideView(TreeNode root) {
//		if (root == null) {
//			return list;
//		}
//		HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
//		map.put(root, 1);
//		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
//		queue.add(root);
//		while (!queue.isEmpty()) {
//			TreeNode node = (TreeNode) queue.poll();
//			int curLevel = map.get(node);
//			if (curLevel != index) {
//				list.add(node.val);
//				index++;
//			}
//			if (node.right != null) {
//				queue.add(node.right);
//				map.put(node.right, curLevel + 1);
//			}
//			if (node.left != null) {
//				queue.add(node.left);
//				map.put(node.left, curLevel + 1);
//			}
//		}
//		return list;
//	}
	// 优化
	List<Integer> list = new ArrayList<Integer>();

	public List<Integer> rightSideView(TreeNode root) {

		if (root == null) {
			return list;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = (TreeNode) queue.poll();
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
				if (i == size - 1) {
					list.add(node.val);
				}
			}
		}
		return list;
	}
}
