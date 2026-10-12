package class06;

public class Code01_LinkedListMid {
	public static class Node {
		public int value;
		public Node next;

		public Node(int v) {
			value = v;
		}
	}

	// 1.返回奇数链表中点 偶数链表上中点
	public static Node oddMidEvenUpMid(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node fast = head;
		Node slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// 2.返回奇数链表中点 偶数链表下中点
	public static Node oddMidEvenDownMid(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node fast = head.next;
		Node slow = head.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// 3.返回奇数链表中点前一个 偶数链表上中点前一个
	public static Node oddMidLastEvenUpMidLast(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node fast = head.next.next;
		Node slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// 4.返回奇数链表中点前一个 偶数链表下中点前一个
	public static Node oddMidLastEvenMidDownLast(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		Node fast = head.next;
		Node slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
}
