package topinterviewquestions;

public class Problem_0002_AddTwoNumbers {
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

//	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//		ListNode res = new ListNode();
//		ListNode cur = res;
//		int sum = 0;
//		int offset = 0;
//		while (l1 != null && l2 != null) {
//			cur.next = new ListNode();
//			cur = cur.next;
//			sum = l1.val + l2.val + offset;
//			cur.val = sum % 10;
//			offset = sum / 10;
//			l1 = l1.next;
//			l2 = l2.next;
//		}
//		while (l1 != null) {
//			cur.next = new ListNode();
//			cur = cur.next;
//			cur.val = (l1.val + offset) % 10;
//			offset = (l1.val + offset) / 10;
//			l1 = l1.next;
//		}
//		while (l2 != null) {
//			cur.next = new ListNode();
//			cur = cur.next;
//			cur.val = (l2.val + offset) % 10;
//			offset = (l2.val + offset) / 10;
//			l2 = l2.next;
//		}
//		if (offset != 0) {
//			cur.next = new ListNode();
//			cur = cur.next;
//			cur.val = 1;
//		}
//		return res.next;
//	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode();
		ListNode cur = res;
		int sum = 0;
		int offset = 0;
		int v1 = 0;
		int v2 = 0;
		while (l1 != null || l2 != null) {
			cur.next = new ListNode();
			cur = cur.next;
			v1 = l1 != null ? l1.val : 0;
			v2 = l2 != null ? l2.val : 0;
			sum = v1 + v2 + offset;
			cur.val = sum % 10;
			offset = sum / 10;
			l1 = l1 != null ? l1.next : null;
			l2 = l2 != null ? l2.next : null;
		}
		if (offset != 0) {
			cur.next = new ListNode();
			cur = cur.next;
			cur.val = 1;
		}
		return res.next;
	}
}
