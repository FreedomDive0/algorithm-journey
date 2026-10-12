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

	// 有parent节点的二叉树的给定节点的后继节点
	public static Node successorNode(Node node) {
		if (node.right != null) {
			return getMostLeft(node.right);
		}
		while (node != null) {
			Node parent = node.parent;
			if (parent != null && node == parent.left) {
				node = node.parent;
				break;
			}
			node = node.parent;
		}
		return node;
	}

	private static Node getMostLeft(Node node) {
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
}
