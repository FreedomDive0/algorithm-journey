package class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code04_SerializeAndReconstructTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 序列化与反序列化
	// (1)先序序列化
	// 1.1先序序列化
	// 1.1先序序列化
	public static Queue<String> preSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		preProcess(head, res);
		return res;
	}

	public static void preProcess(Node cur, Queue<String> res) {
		if (cur == null) {
			res.add(null);
		} else {
			res.add(String.valueOf(cur.value));
			preProcess(cur.left, res);
			preProcess(cur.right, res);
		}
	}

	// 1.2先序反序列化
	public static Node preReconstruct(Queue<String> arr) {
		if (arr == null || arr.size() < 1) {
			return null;
		}
		return preRProcess(arr);
	}

	public static Node preRProcess(Queue<String> arr) {
		String value = arr.poll();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = preRProcess(arr);
		head.right = preRProcess(arr);
		return head;
	}

	// 2.1后序序列化
	public static Queue<String> posSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		posProcess(head, res);
		return res;
	}

	public static void posProcess(Node cur, Queue<String> res) {
		if (cur == null) {
			res.add(null);
		} else {
			posProcess(cur.left, res);
			posProcess(cur.right, res);
			res.add(String.valueOf(cur.value));
		}
	}

	// 2.2 后序反序列化
	public static Node posReconstruct(Queue<String> arr) {
		if (arr == null || arr.size() < 1) {
			return null;
		}
		Stack<String> stack = new Stack<String>();
		while (!arr.isEmpty()) {
			stack.add(arr.poll());
		}
		return posRProcess(stack);
	}

	public static Node posRProcess(Stack<String> stack) {
		String value = stack.pop();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.right = posRProcess(stack);
		head.left = posRProcess(stack);
		return head;
	}

	// 3.1层序序列化
	public static Queue<String> levelSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		if (head == null) {
			return res;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur != null) {
				res.add(String.valueOf(cur.value));
				queue.add(cur.left);
				queue.add(cur.right);
			} else {
				res.add(null);
			}
		}
		return res;
	}

	// 3.2层序遍历反序列化
	public static Node levelReconstruct(Queue<String> queueS) {
		if (queueS == null || queueS.isEmpty()) {
			return null;
		}
		Node head = createNode(queueS.poll());
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			Node left = createNode(queueS.poll());
			Node right = createNode(queueS.poll());
			cur.left = left;
			cur.right = right;
			if (left != null) {
				queue.add(left);
			}
			if (right != null) {
				queue.add(right);
			}
		}
		return head;
	}

	public static Node createNode(String val) {
		if (val == null) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		Queue<String> queue = levelSerialize(head);
		for (String str : queue) {
			System.out.println(str);
		}
	}
}
