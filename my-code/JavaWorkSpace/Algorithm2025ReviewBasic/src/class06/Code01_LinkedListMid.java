package class06;

public class Code01_LinkedListMid {
	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
			next = null;
		}
	}

	// 1.奇数中点 偶数上中点
	public static Node oddMidEvenUpMid(Node head) {// 都从开头出发默认是奇中点和偶上中点
		if (head == null || head.next == null || head.next.next == null) {// 开头判断
			return head;
		}
		Node fast = head.next.next;// 后续跳一步再开始
		Node slow = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// 2.奇数中点 偶数下中点
	public static Node oddMidEvenDownMid(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node fast = head.next;
		Node slow = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// 3.奇数中点前一个 偶数上中点前一个
	public static Node oddMidLastEvenUpMidLast(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// 4.奇数中点前一个 偶数下中点前一个
	public static Node oddMidLastEvenDownMidLast(Node head) {
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
