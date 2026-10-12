package 二叉树专题;

public class LC剑指Offer54_二叉搜索树的第k大节点 {
	public int kthLargest(TreeNode root, int k) {
		process(root, k);
		return res;
	}

	int res = Integer.MIN_VALUE;
	int index = 1;

	public void process(TreeNode node, int k) {
		if (node == null) {
			return;
		}
		process(node.right, k);
		if (index == k) {
			res = node.val;
		}
		index++;
		process(node.left, k);
	}
}
