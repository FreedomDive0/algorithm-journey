package 二叉树;

import java.util.HashMap;

public class Problem_0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
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

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	public TreeNode process(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2,
			HashMap<Integer, Integer> map) {
		if (l1 > r1) {
			return null;
		}
		TreeNode head = new TreeNode(preorder[l1]);
		if (l1 == r1) {
			return head;
		}
		int findIndex = map.get(preorder[l1]);
		head.left = process(preorder, l1 + 1, l1 + findIndex - l2, inorder, l2, findIndex - 1, map);
		head.right = process(preorder, l1 + findIndex - l2 + 1, r1, inorder, findIndex + 1, r2, map);
		return head;
	}
//	public TreeNode buildTree(int[] preorder, int[] inorder) {
//		return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
//	}
//
//	public TreeNode process(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
//		if (l1 > r1) {
//			return null;
//		}
//		TreeNode head = new TreeNode(preorder[l1]);
//		if (l1 == r1) {
//			return head;
//		}
//		int findIndex = l2;
//		for (; findIndex < r2; findIndex++) {
//			if (inorder[findIndex] == preorder[l1]) {
//				break;
//			}
//		}
//		head.left = process(preorder, l1 + 1, l1 + findIndex - l2, inorder, l2, findIndex - 1);
//		head.right = process(preorder, l1 + findIndex - l2 + 1, r1, inorder, findIndex + 1, r2);
//		return head;
//	}
}
