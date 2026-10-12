package class02;

public class Code01_ReverseList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}

	}

	public static class DoubleNode {
		public int value;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int value) {
			this.value = value;
		}
	}

	// 1.反转单链表
	public static Node reverseLinkedList1(Node head) {
		if (head == null || head.next == null)
			return head;
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

	// 2.反转双向链表
	public static DoubleNode reverseDoubleLinkedList1(DoubleNode head) {
		if (head == null || head.next == null)
			return head;
		DoubleNode last = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.last = next;
			head.next = last;
			last = head;
			head = next;
		}
		return last;
	}
}
