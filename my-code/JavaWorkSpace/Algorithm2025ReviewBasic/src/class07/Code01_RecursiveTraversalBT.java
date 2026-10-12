package class07;

import java.util.Stack;

public class Code01_RecursiveTraversalBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}

	}

	// 1.递归序
	public static void recursiveTraversal(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value + " ");
		recursiveTraversal(head.left);
		System.out.println(head.value + " ");
		recursiveTraversal(head.right);
		System.out.println(head.value + " ");
	}

	// 2.1 先序遍历递归
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value + " ");
		pre(head.left);
		pre(head.right);
	}

	// 2.2 先序遍历非递归
	public static void preNonrecursive(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.value + " ");
			if (cur.right != null) {
				stack.add(cur.right);
			}
			if (cur.left != null) {
				stack.add(cur.left);
			}
		}
	}

	// 3.1 中序遍历递归
	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.println(head.value + " ");
		in(head.right);
	}

	// 3.2 中序遍历非递归
	public static void inNonrecursive(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		while (!stack.isEmpty() || head != null) {// 在自身节点考虑问题 巧妙解决判空
			if (head != null) {// 按左边界划分
				stack.add(head);
				head = head.left;
			} else {
				head = stack.pop();
				System.out.println(head.value + " ");
				head = head.right;
			}
		}
//		stack.add(head);
//		Node cur = head;
//		while (!stack.isEmpty() || cur != null) {//在父节点考虑问题
//			if (cur != null && cur.left != null) {
//				stack.add(cur.left);
//				cur = cur.left;
//			} else {
//				cur = stack.pop();
//				System.out.println(cur.value + " ");
//				if (cur.right != null) {
//					stack.add(cur.right);
//				}
//				cur = cur.right;
//			}
//		}
	}

	// 4.1 后序遍历递归
	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.println(head.value + " ");
	}

	// 4.2 后序遍历非递归
	public static void posNonrecursive(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> help = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			head = stack.pop();
			help.add(head);
			if (head.left != null) {
				stack.add(head.left);
			}
			if (head.right != null) {
				stack.add(head.right);
			}
		}
		while (!help.isEmpty()) {
			System.out.println(help.pop().value + " ");
		}
	}

	// 4.3 后序遍历单栈炫技版
	public static void posNonrecursive1(Node h) {
		if (h == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Node c = h;
		stack.add(c);
		while (!stack.isEmpty()) {// 以头结点视角看待问题 左右头
			c = stack.peek();
			if (c.left != null && c.left != h && c.right != h) {// 左树未处理
				stack.add(c.left);
			} else if (c.right != null && c.right != h) {// 右树未处理
				stack.add(c.right);
			} else {// 左右子树处理完毕
				h = stack.pop();// 标记
				System.out.println(h.value + " ");
			}
		}
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		n1.right = n2;
		n2.left = n3;
		in(n1);
		System.out.println("----------------------------------");
		inNonrecursive(n1);

	}
}
