package HW;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树 {
//	1.从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

//
//	public List<List<Integer>> levelOrder(TreeNode root) {
//		Queue<TreeNode> queue = new LinkedList<>();
//		List<List<Integer>> res = new ArrayList<List<Integer>>();
//		if (root != null) {
//			queue.add(root);
//		}
//		while (!queue.isEmpty()) {
//			ArrayList<Integer> temp = new ArrayList<Integer>();
//			for (int i = queue.size(); i > 0; i--) {
//				TreeNode node = queue.poll();
//				temp.add(node.val);
//				if (node.left != null) {
//					queue.add(node.left);
//				}
//				if (node.right != null) {
//					queue.add(node.right);
//				}
//			}
//			res.add(temp);
//		}
//		return res;
//	}
//	2.请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
