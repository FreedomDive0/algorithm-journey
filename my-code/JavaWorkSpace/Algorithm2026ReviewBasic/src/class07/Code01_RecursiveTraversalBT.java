package class07;

import java.util.Stack;

public class Code01_RecursiveTraversalBT {
	// 递归遍历二叉树
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	// 1.1先序遍历(递归)
	public static void preRecursive(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preRecursive(head.left);
		preRecursive(head.right);
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

	// 2.1 后序遍历(递归)
	public static void posRecursive(Node head) {
		if (head == null) {
			return;
		}
		posRecursive(head.left);
		posRecursive(head.right);
		System.out.print(head.value + " ");
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

	// 3.1 中序遍历(递归)
	public static void inRecursive(Node head) {
		if (head == null) {
			return;
		}
		inRecursive(head.left);
		System.out.print(head.value + " ");
		inRecursive(head.right);
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

	// 4.递归序
	public static void recursive(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		recursive(head.left);
		System.out.print(head.value + " ");
		recursive(head.right);
		System.out.print(head.value + " ");
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		preRecursive(head);
		System.out.println("========");
		preIteration(head);
		System.out.println("========");
		inRecursive(head);
		System.out.println("========");
		inIteration(head);
		System.out.println("========");
		posRecursive(head);
		System.out.println("========");
		posIteration1(head);
		System.out.println("========");
		posIteration2(head);
		System.out.println("========");
		recursive(head);
		System.out.println("========");

	}
}
