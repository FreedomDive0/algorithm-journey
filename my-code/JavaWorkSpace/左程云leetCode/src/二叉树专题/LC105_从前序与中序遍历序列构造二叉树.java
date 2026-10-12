package 二叉树专题;

import java.util.HashMap;

public class LC105_从前序与中序遍历序列构造二叉树 {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return process(preorder, 0, preorder.length, inorder, 0, inorder.length);
	}

	// 每次顺位拿p 取头
	public TreeNode process(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
		if (ps == pe) {
			return null;
		}
		int val = preorder[ps];
		TreeNode node = new TreeNode(val);
		int index = map.get(val);
		int leftNum = index - is;// 左子树元素个数
		node.left = process(preorder, ps + 1, ps + leftNum + 1, inorder, is + leftNum, index);
		node.right = process(preorder, ps + leftNum + 1, pe, inorder, index + 1, ie);
		return node;
	}
}
