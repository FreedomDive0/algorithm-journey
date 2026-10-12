package 二叉树;

import java.util.HashMap;

public class Problem_0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
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

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return process(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
	}

	public TreeNode process(int[] inorder, int l1, int r1, int[] postorder, int l2, int r2,
			HashMap<Integer, Integer> map) {
		if (l2 > r2) {
			return null;
		}
		TreeNode head = new TreeNode(postorder[r2]);
		if (l2 == r2) {
			return head;
		}
		int findIndex = map.get(postorder[r2]);
		head.left = process(inorder, l1, findIndex - 1, postorder, l2, l2 + findIndex - l1 - 1, map);
		head.right = process(inorder, findIndex + 1, r1, postorder, l2 + findIndex - l1, r2 - 1, map);
		return head;
	}

}
