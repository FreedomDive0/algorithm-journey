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

	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.add(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
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
		System.out.println();
	}

	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();
			Stack<Node> s2 = new Stack<Node>();
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop();
				s2.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}

	public static void pos2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	// 测试
	public static void pOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.print(cur.value + " ");
			if (cur.right != null) {
				stack.add(cur.right);
			}
			if (cur.left != null) {
				stack.add(cur.left);
			}
		}
		System.out.println();
	}

	// 中序遍历
	public static void iOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		while (!stack.isEmpty() || head != null) {// head!=null这个条件为设置初始stack没有元素的情况
			if (head != null) {// 保证了左头顺序 左子树处理完毕
				stack.add(head);
				head = head.left;
			} else {
				head = stack.pop();// 处理右子树
				System.out.print(head.value + " ");
				head = head.right;
			}
		}
	}

	// (2)后序遍历
	// 双栈实现
	public static void poOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> ans = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			ans.add(cur);
			if (cur.left != null) {
				stack.add(cur.left);
			}
			if (cur.right != null) {
				stack.add(cur.right);
			}
		}
		while (!ans.isEmpty()) {
			System.out.print(ans.pop().value + " ");
		}
	}

	// 单栈实现
	public static void psOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			Node cur = stack.peek();
			if (cur.left != null && cur.left != head && cur.right != head) {// 处理左子树条件
				stack.add(cur.left);
			} else if (cur.right != null && cur.right != head) {// 处理右子树条件
				stack.add(cur.right);
			} else {// 左右子树处理完毕 处理子树头结点
				System.out.print(stack.pop().value + " ");
				head = cur;// 标记处理位置
			}
		}
	}

	// 2026测试
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
		if (head == null) {
			return;
		}
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

	// 1 2 4 5 3 6 7
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
//		recursiveTraversal(head);
		pOrder(head);
		System.out.println("========");
		preIteration(head);
		System.out.println();
		System.out.println("========");
		in(head);
		System.out.println("========");
		inIteration(head);
		System.out.println();
		System.out.println("========");
//		pos1(head);
//		poOrder(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
		posIteration1(head);
		System.out.println();
		System.out.println("========");
		posIteration2(head);
		System.out.println();
		System.out.println("========");
	}

}
