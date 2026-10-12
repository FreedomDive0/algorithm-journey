package 链表;

public class Problem_0025_Reverse_Nodes_in_kGroup {
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

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode tail = head;
		for (int i = 0; i < k; i++) {
			if (tail == null) {
				return head;
			}
			tail = tail.next;
		}
		ListNode newHead = reverseFHTT(head, tail);
		head.next = reverseKGroup(tail, k);
		return newHead;
	}

	public ListNode reverseFHTT(ListNode head, ListNode tail) {
		ListNode pre = null;
		while (head != tail) {
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
}
