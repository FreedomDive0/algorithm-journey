package 链表专题;

public class LC234_回文链表 {
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

	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode start = slow.next;
		ListNode pre = null;
		ListNode next = null;
		while (start != null) {
			next = start.next;
			start.next = pre;
			pre = start;
			start = next;
		}
		start = pre;
		next = head;
		while (next != null && start != null) {
			if (start.val != next.val) {
				return false;
			}
			start = start.next;
			next = next.next;
		}
		start = pre;
		pre = null;
		while (start != null) {
			next = start.next;
			start.next = pre;
			pre = start;
			start = next;
		}
		return true;
	}
}
