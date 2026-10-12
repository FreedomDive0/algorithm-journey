package 二叉树专题;

import java.util.ArrayList;
import java.util.List;

public class LC94_二叉树的中序遍历 {
	ArrayList<Integer> list = new ArrayList<Integer>();

	public List<Integer> inorderTraversal(TreeNode root) {
		if (root == null) {
			return list;
		}
		inorderTraversal(root.left);
		list.add(root.val);
		inorderTraversal(root.right);
		return list;
	}
}
