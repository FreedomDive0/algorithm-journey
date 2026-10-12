package class07;

public class Code07_SuccessorNode {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	// 后继节点
	public static Node successorNode(Node node) {
		if (node == null) {
			return null;
		}
		if (node.right != null) {// 中序遍历处于某个树的头
			return getMostLeft(node.right);
		} else {// 中序遍历处于左树最右或者右树最右
			Node parent = node.parent;
			while (parent != null && parent.left != node) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	public static Node getMostLeft(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
}
