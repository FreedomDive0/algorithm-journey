package class07;

import java.util.Stack;

public class Code02_UnRecursiveTraversalBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	// 1.2先序遍历(迭代)
	public static void preIteration(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(head);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.print(cur.value + " ");
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}

	// 2.2 后序遍历(迭代双栈)
	public static void posIteration1(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> pop = new Stack<Node>();
		stack.push(head);
		while (!stack.isEmpty()) {// 头 右 左=>左 右 头
			Node cur = stack.pop();
			pop.push(cur);
			if (cur.left != null) {
				stack.push(cur.left);
			}
			if (cur.right != null) {
				stack.push(cur.right);
			}
		}
		while (!pop.isEmpty()) {
			System.out.print(pop.pop().value + " ");
		}
	}

	// 2.3后序遍历(迭代单栈)
	public static void posIteration2(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(head);
		Node c = head;
		while (!stack.isEmpty()) {
			c = stack.peek();
			if (c.left != null && c.left != head && c.right != head) {
				stack.push(c.left);
			} else if (c.right != null && c.right != head) {
				stack.push(c.right);
			} else {
				head = stack.pop();
				System.out.print(head.value + " ");
			}
		}
	}

	// 3.2中序遍历(迭代)
	public static void inIteration(Node head) {
		Stack<Node> stack = new Stack<Node>();
		while (!stack.isEmpty() || head != null) {
			if (head != null) {
				stack.push(head);
				head = head.left;
			} else {
				head = stack.pop();
				System.out.print(head.value + " ");
				head = head.right;
			}
		}
	}
}
