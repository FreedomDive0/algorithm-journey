package 二叉树专题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC剑指Offer34二叉树中和为某一值的路径 {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	LinkedList<Integer> tmp = new LinkedList<Integer>();

	public List<List<Integer>> pathSum(TreeNode root, int target) {
		process(root, target);
		return res;
	}

	public void process(TreeNode node, int target) {
		if (node == null) {
			return;
		}
		tmp.add(node.val);
		target -= node.val;
		if (target == 0 && node.left == null && node.right == null) {
			res.add(new LinkedList(tmp));// 将tmp加入后 tmp会参与后续的操作
		}
		process(node.left, target);
		process(node.right, target);
		tmp.removeLast();// 关于该点的递归已经结束 所以要移除该点 在递归中会有栈记录相应变量
	}
}
