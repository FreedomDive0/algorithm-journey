package class02;

public class Code01_ReverseList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}

	}

	// 1.单列表反转
	public static Node reverseLinkedList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static class DoubleNode {
		public int value;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int value) {
			this.value = value;
		}

	}

	// 2.反转双向链表
	public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}
}
