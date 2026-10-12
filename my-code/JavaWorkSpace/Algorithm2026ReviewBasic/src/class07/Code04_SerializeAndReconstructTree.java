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

	// 1.1先序序列化
	public static Queue<String> preSerialize(Node head) {
		Queue<String> queue = new LinkedList<String>();
		preSProcess(head, queue);
		return queue;
	}

	public static void preSProcess(Node head, Queue<String> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			queue.add(String.valueOf(head.value));
			preSProcess(head.left, queue);
			preSProcess(head.right, queue);
		}
	}

	// 1.2先序反序列化
	public static Node preReconstruct(Queue<String> queue) {
		if (queue == null)
			return null;
		return preRProcess(queue);
	}

	public static Node preRProcess(Queue<String> queue) {
		if (queue.isEmpty())
			return null;
		String value = queue.poll();
		if (value == null) {
			return null;
		} else {
			Node cur = new Node(Integer.valueOf(value));
			cur.left = preReconstruct(queue);
			cur.right = preReconstruct(queue);
			return cur;
		}
	}

	// 2.1 后序序列化
	public static Queue<String> posSerialize(Node head) {
		Queue<String> queue = new LinkedList<String>();
		posSProcess(head, queue);
		return queue;
	}

	public static void posSProcess(Node head, Queue<String> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			posSProcess(head.left, queue);
			posSProcess(head.right, queue);
			queue.add(String.valueOf(head.value));
		}
	}

	// 2.2后序反序列化
	public static Node posReconstruct(Queue<String> queue) {
		if (queue == null) {
			return null;
		}
		Stack<String> stack = new Stack<String>();// 使用栈反装 头右左
		while (!queue.isEmpty()) {
			stack.push(queue.poll());
		}
		return posRProcess(stack);
	}

	public static Node posRProcess(Stack<String> stack) {
		if (stack.isEmpty()) {
			return null;
		}
		String value = stack.pop();
		if (value == null) {
			return null;
		} else {
			Node cur = new Node(Integer.valueOf(value));
			cur.right = posRProcess(stack);
			cur.left = posRProcess(stack);
			return cur;
		}
	}

	// 3.1 层序序列化
	public static Queue<String> levelSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		if (head == null) {
			return res;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		res.add(String.valueOf(head.value));
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left == null) {
				res.add(null);
			} else {
				queue.add(cur.left);
				res.add(String.valueOf(cur.left.value));// 与空同步加入
			}
			if (cur.right == null) {
				res.add(null);
			} else {
				queue.add(cur.right);
				res.add(String.valueOf(cur.right.value));
			}
		}
		return res;
	}

	// 3.2 反层序序列化
	public static Node levelReconstruct(Queue<String> res) {
		if (res == null || res.isEmpty() || res.peek() == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(res.poll()));
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!res.isEmpty()) {
			Node cur = queue.poll();
			String value = res.poll();
			if (value != null) {
				cur.left = new Node(Integer.valueOf(value));
				queue.add(cur.left);
			}
			value = res.poll();
			if (value != null) {
				cur.right = new Node(Integer.valueOf(value));
				queue.add(cur.right);
			}
		}
		return head;
	}
}
