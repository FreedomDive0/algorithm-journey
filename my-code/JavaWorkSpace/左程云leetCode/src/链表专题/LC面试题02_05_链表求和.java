package 链表专题;

public class LC面试题02_05_链表求和 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode h = new ListNode(0);
		ListNode res = h;
		while (l1 != null && l2 != null) {
			ListNode tmp = new ListNode(l1.val + l2.val);
			h.next = tmp;
			h = h.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		h.next = l1 == null ? l2 : l1;
		h = res.next;
		while (h != null) {
			if (h.val >= 10) {
				int rest = h.val % 10;
				h.val = rest;
				if (h.next == null) {
					ListNode last = new ListNode(1);
					h.next = last;
					last.next = null;
				} else {
					h.next.val += 1;
				}
			}
			h = h.next;
		}
		return res.next;
	}
}
