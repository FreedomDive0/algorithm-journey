package 链表;

public class Problem_0061_Rotate_List {
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}
		int n = 1;
		ListNode tail = head;
		while (tail.next != null) {
			tail = tail.next;
			n++;
		}
		k = k % n;
		ListNode start = head;
		for (int i = 0; i < n - k - 1; i++) {
			start = start.next;
		}
		tail.next = head;
		head = start.next;
		start.next = null;
		return head;
	}
}
