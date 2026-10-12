package 链表;

public class Problem_0002_Add_Two_Numbers {
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

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode();
		ListNode cur = res;
		int offset = 0;
		int v1 = 0;
		int v2 = 0;
		int sum = 0;
		while (l1 != null || l2 != null) {
			cur.next = new ListNode();
			cur = cur.next;
			v1 = l1 != null ? l1.val : 0;
			v2 = l2 != null ? l2.val : 0;
			sum = v1 + v2 + offset;
			offset = sum / 10;
			cur.val = sum % 10;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		if (offset == 1) {
			cur.next = new ListNode();
			cur = cur.next;
			cur.val = 1;
		}
		return res.next;
	}
}
