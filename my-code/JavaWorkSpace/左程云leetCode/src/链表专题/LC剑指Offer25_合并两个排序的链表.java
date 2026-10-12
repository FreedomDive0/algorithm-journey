package 链表专题;

public class LC剑指Offer25_合并两个排序的链表 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode h = new ListNode(0);
		ListNode res = h;
		while (l1 != null & l2 != null) {
			if (l1.val < l2.val) {
				h.next = l1;
				l1 = l1.next;
			} else {
				h.next = l2;
				l2 = l2.next;
			}
			h = h.next;
		}
		h.next = l1 == null ? l2 : l1;
		return res.next;
	}
}
