package topinterviewquestions;

public class Problem_0019_RemoveNthNodeFromEndofList {
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

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = head;
		ListNode cur = head;
		while (cur != null) {
			if (n < 0) {
				pre = pre.next;
			}
			cur = cur.next;
			n--;
		}
		if (n > 0) {
			return null;
		}
		if (pre == head) {
			head = head.next;
			return head;
		}
		pre.next = pre.next.next;
		return head;
	}
}
