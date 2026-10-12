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

	public static Node getSuccessorNode(Node node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {
			return getLeftMost(node.right);
		} else { // 无右子树
			Node parent = node.parent;
			while (parent != null && parent.right == node) { // 当前节点是其父亲节点右孩子
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	public static Node getLeftMost(Node node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// 复习2026
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

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;
//		1 next: 2
//		2 next: 3
//		3 next: 4
//		4 next: 5
//		5 next: 6
//		6 next: 7
//		7 next: 8
//		8 next: 9
//		9 next: 10
//		10 next: null
		Node test = head.left.left;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + successorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + successorNode(test));
	}

}
