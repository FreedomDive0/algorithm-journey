package class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Review {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}

	// 1.遍历
	public static void pre(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.value + " ");
		pre(root.left);
		pre(root.right);
	}

	// (1)先序
	public static void preN(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.print(root.value + " ");
			if (node.right != null) {
				stack.add(node.right);
			}
			if (node.left != null) {
				stack.add(node.left);
			}
		}
	}

	// (2)后序
	public static void pos(Node root) {
		if (root == null) {
			return;
		}
		pos(root.left);
		pos(root.right);
		System.out.print(root.value + " ");
	}

	// (2.1)后序用栈
	public static void posN1(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		Stack<Node> help = new Stack<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			help.add(node);
			if (node.left != null) {
				stack.add(node.left);
			}
			if (node.right != null) {
				stack.add(node.right);
			}
		}
		while (!help.isEmpty()) {
			Node node = help.pop();

		}
	}

	// (2.2)后序用h,c
	public static void posN2(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		Node h = root;
		stack.add(root);
		while (!stack.isEmpty()) {
			Node node = stack.peek();
			if (node.left != null && node.left != h && node.right != h) {// 判断左边是否处理
				stack.add(node.left);
			} else if (node.right != null && node.right != h) {// 判断右边是否处理
				stack.add(node.right);
			} else {
				System.out.print(stack.pop().value + " ");
				h = node;
			}
		}
	}

	// (3)中序遍历
	public static void mid(Node root) {
		if (root == null) {
			return;
		}
		mid(root.left);
		System.out.print(root.value + " ");
		mid(root.right);
	}

	public static void midN(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			if (root != null) {// 确定了头左的顺序 先处理左头
				stack.add(root);
				root = root.left;
			} else {// 处理右
				Node node = stack.pop();
				System.out.print(node.value + " ");
				root = node.right;
			}
		}
	}

	// 4.层序遍历
	public static void level(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			System.out.print(root.value + " ");
			if (root.left != null) {
				queue.add(root.left);
			}
			if (root.right != null) {
				queue.add(root.right);
			}
		}
	}

	// 最大宽度
	public static int maxWidth(Node root) {
		if (root == null) {
			return 0;
		}
		HashMap<Node, Integer> map = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		map.put(root, 1);
		int max = 0;
		int curLevel = 1;
		int curNodes = 0;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.left != null) {
				queue.add(node.left);
				map.put(node.left, curLevel + 1);
			}
			if (node.right != null) {
				queue.add(node.right);
				map.put(node.right, curLevel + 1);
			}
			if (curLevel == map.get(node)) {
				curNodes++;
			} else {
				max = Math.max(max, curNodes);
				curNodes = 1;
				curLevel++;
			}
		}
		return max;
	}

	public static int MaxWidthN(Node root) {
		if (root == null) {
			return 0;
		}
		int max = 0;
		int levelNodes = 0;
		Node endCur = root;
		Node endNext = null;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			levelNodes++;
			if (node.left != null) {
				endNext = node.left;
				queue.add(endNext);
			}
			if (node.right != null) {
				endNext = node.right;
				queue.add(endNext);
			}
			if (node == endCur) {
				max = Math.max(max, levelNodes);
				levelNodes = 0;
				endCur = endNext;
			}
		}
		return max;
	}

	// 序列化与反序列化
	// 先序
	public static Queue<String> preS(Node root) {
		Queue<String> queue = new LinkedList<>();
		AddS(root, queue);
		return queue;
	}

	public static void AddS(Node root, Queue<String> queue) {
		if (root == null) {
			queue.add("null");
			return;
		} else {
			queue.add(String.valueOf(root.value));
			AddS(root.left, queue);
			AddS(root.right, queue);
		}
	}

	public static Node preU(Queue<String> queue) {
		if (queue == null) {
			return null;
		}
		return AddU(queue, null);
	}

	public static Node AddU(Queue<String> queue, Node root) {
		String str = queue.poll();
		root = genNode(str);
		if (root != null) {
			root.left = AddU(queue, root.left);
			root.right = AddU(queue, root.right);
		}
		return root;
	}

	public static Node genNode(String str) {
		if (str.equals("null")) {
			return null;
		} else {
			return new Node(Integer.valueOf(str));
		}
	}

	// 后序
	public static Queue<String> posS(Node root) {
		Queue<String> queue = new LinkedList<>();
		AddP(root, queue);
		return queue;
	}

	public static void AddP(Node root, Queue<String> queue) {
		if (root == null) {
			queue.add("null");
			return;
		} else {
			AddP(root.left, queue);
			AddP(root.right, queue);
			queue.add(String.valueOf(root.value));
		}
	}

	public static Node posU(Queue<String> queue) {
		if (queue == null) {
			return null;
		}
		Stack<String> stack = new Stack<>();// 左右头 -> 头右左
		while (!queue.isEmpty()) {
			stack.add(queue.poll());
		}
		return AddP(stack, null);
	}

	public static Node AddP(Stack<String> stack, Node root) {
		String str = stack.pop();
		root = genNode(str);
		if (root != null) {
			root.right = AddP(stack, root.right);
			root.left = AddP(stack, root.left);
		}
		return root;
	}

	// 层序
	public static Queue<String> levelS(Node root) {
		if (root == null) {
			return null;
		}
		Queue<Node> queue = new LinkedList<>();
		Queue<String> res = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			AddL(res, root);
			if (root != null) {
				queue.add(root.left);
				queue.add(root.right);
			}
		}
		return res;
	}

	public static void AddL(Queue<String> queue, Node root) {
		if (root == null) {
			queue.add("null");
		} else {
			queue.add(String.valueOf(root.value));
		}
	}

	public static Node levelU(Queue<String> stringQueue) {
		if (stringQueue == null || stringQueue.size() == 0) {
			return null;
		}
		Queue<Node> queue = new LinkedList<>();// 暂时存放
		Node root = genNode(stringQueue.poll());
		queue.add(root);
		Node cur = null;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			cur.left = genNode(stringQueue.poll());
			cur.right = genNode(stringQueue.poll());
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return root;
	}

	// 打印二叉树
	public static void printTree(Node head) {
		if (head == null) {
			System.out.println("null");
			return;
		}
		processP(head, 0, 17, "H");
	}

	public static void processP(Node root, int height, int size, String mark) {
		if (root == null) {
			return;
		}
		processP(root.right, height + 1, size, "v");
		String val = mark + root.value + mark;
		int len = (size - val.length()) / 2;
		System.out.println(getSpace(height * size) + getSpace(len) + val + getSpace(size - val.length() - len));
		processP(root.left, height + 1, size, "^");
	}

	public static String getSpace(int num) {
		String space = "";
		while (num-- > 0) {
			space += " ";
		}
		return space;
	}

	// 满二叉树 2^n - 1
	// 含parent节点 找到X的后继节点(中序下一个)
	public static Node follow(Node x) {
		if (x == null) {
			return null;
		}
		if (x.right != null) {
			Node right = x.right;
			while (right.left != null) {
				right = right.left;
			}
			return right;
		} else {
			Node parent = x.parent;
			while (parent != null && parent.left != x) {
				x = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	// 折纸
	public static void paper(int n) {
		if (n == 0) {
			return;
		}
		printP(1, n, true);
	}

	public static void printP(int level, int n, boolean flag) {
		if (level > n) {
			return;
		}
		printP(level + 1, n, true);
		System.out.println(flag ? "凹" : "凸");
		printP(level + 1, n, false);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
//		printTree(head);
		paper(3);
//		Queue<String> queue = levelS(head);
//		while (!queue.isEmpty()) {
//			System.out.print(queue.poll() + " ");
//		}
//		Node root = posU(queue);
//		level(root);
		System.out.println("========");
		// in(head);
		System.out.println("========");
		// pos(head);
		System.out.println("========");
	}
}
