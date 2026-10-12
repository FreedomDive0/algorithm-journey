package class04;

import java.util.LinkedList;
import java.util.Queue;

public class Code02_BSTtoDoubleLinkedList {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node convert1(Node head) {
		Queue<Node> queue = new LinkedList<Node>();
		inOrderToQueue(head, queue);
		if (queue.isEmpty()) {
			return head;
		}
		head = queue.poll();
		Node pre = head;
		pre.left = null;
		Node cur = null;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			pre.right = cur;
			cur.left = pre;
			pre = cur;
		}
		pre.right = null;
		return head;
	}

	public static void inOrderToQueue(Node head, Queue<Node> queue) {
		if (head == null) {
			return;
		}
		inOrderToQueue(head.left, queue);
		queue.offer(head);
		inOrderToQueue(head.right, queue);
	}

	public static Node convert2(Node head) {
		if (head == null) {
			return null;
		}
		return process(head).start;
	}

	// 整棵树，串成双向链表，返回头、尾
	public static class Info {
		public Node start;
		public Node end;

		public Info(Node start, Node end) {
			this.start = start;
			this.end = end;
		}
	}

	// 以x为头的整棵搜索二叉树，请全部以有序双向链表的方式，连好
	// 并且返回，整个有序双向链表的头节点和尾节点
	public static Info process(Node X) {
		if (X == null) {
			return new Info(null, null);
		}
		Info leftInfo = process(X.left);
		Info rightInfo = process(X.right);
		if (leftInfo.end != null) {
			leftInfo.end.right = X;
		}
		X.left = leftInfo.end;
		X.right = rightInfo.start;
		if (rightInfo.start != null) {
			rightInfo.start.left = X;
		}
		return new Info(
				// 整棵树的头，
				leftInfo.start != null ? leftInfo.start : X,
				// 整棵树的尾
				rightInfo.end != null ? rightInfo.end : X);
	}

	public static void printBSTInOrder(Node head) {
		System.out.print("BST in-order: ");
		if (head != null) {
			inOrderPrint(head);
		}
		System.out.println();
	}

	public static void inOrderPrint(Node head) {
		if (head == null) {
			return;
		}
		inOrderPrint(head.left);
		System.out.print(head.value + " ");
		inOrderPrint(head.right);
	}

	public static void printDoubleLinkedList(Node head) {
		System.out.print("Double Linked List: ");
		Node end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.right;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.left;
		}
		System.out.println();
	}

	// 测试
	public static Node BTStoDLL(Node head) {
		if (head == null) {
			return null;
		}
		return process1(head).start;
	}

	// 给定头节点 返回以该节点为头的树 转换为双向链表的头尾节点
	public static Info process1(Node node) {
		if (node == null) {
			return new Info(null, null);
		}
		Info leftInfo = process1(node.left);
		Info rightInfo = process1(node.right);
		// 得到左右树的信息开始连接
		if (leftInfo.end != null) {// 如果存在 将左树最后一个节点与node相连 next
			leftInfo.end.right = node;
		}
		node.left = leftInfo.end;
		if (rightInfo.start != null) {// 如果存在 将右树第一个节点与node相连 last
			rightInfo.start.left = node;
		}
		node.right = rightInfo.start;
		Node head = leftInfo.start != null ? leftInfo.start : node;
		Node tail = rightInfo.end != null ? rightInfo.end : node;
		return new Info(head, tail);
	}

	// 测试
	// 2.搜索二叉树==>双向链表
//	public static class Node1 {
//		public int value;
//		public Node1 left;
//		public Node1 right;
//
//		public Node1(int data) {
//			this.value = data;
//		}
//	}
//
	public static Node BSTToDoublieLinkedList(Node head) {
		if (head == null) {
			return null;
		}
		return processBSTTDL(head).start;
	}

	public static class BSTTDLLInfo {
		Node start;
		Node end;

		public BSTTDLLInfo(Node start, Node end) {
			this.start = start;
			this.end = end;
		}

	}

	public static BSTTDLLInfo processBSTTDL(Node node) {
		if (node == null) {
			return new BSTTDLLInfo(null, null);
		}
		BSTTDLLInfo leftInfo = processBSTTDL(node.left);
		BSTTDLLInfo rightInfo = processBSTTDL(node.right);
		if (leftInfo.end != null) {
			leftInfo.end.right = node;
		}
		node.left = leftInfo.end;
		node.right = rightInfo.start;
		if (rightInfo.start != null) {
			rightInfo.start.left = node;
		}
		Node start = leftInfo.start == null ? node : leftInfo.start;
		Node end = rightInfo.end == null ? node : rightInfo.end;
		return new BSTTDLLInfo(start, end);
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(2);
		head.right = new Node(9);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.left.right.right = new Node(4);
		head.right.left = new Node(7);
		head.right.right = new Node(10);
		head.left.left = new Node(1);
		head.right.left.left = new Node(6);
		head.right.left.right = new Node(8);

		printBSTInOrder(head);
		head = convert1(head);
		printDoubleLinkedList(head);

		head = new Node(5);
		head.left = new Node(2);
		head.right = new Node(9);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.left.right.right = new Node(4);
		head.right.left = new Node(7);
		head.right.right = new Node(10);
		head.left.left = new Node(1);
		head.right.left.left = new Node(6);
		head.right.left.right = new Node(8);

		printBSTInOrder(head);
//		head = convert2(head);
		head = BSTToDoublieLinkedList(head);
		printDoubleLinkedList(head);

	}

}