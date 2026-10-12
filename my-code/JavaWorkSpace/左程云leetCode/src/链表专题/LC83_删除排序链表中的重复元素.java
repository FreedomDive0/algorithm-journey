package 链表专题;

public class LC83_删除排序链表中的重复元素 {
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

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode cur = head;
		ListNode next = head.next;
		while (next != null) {
			if (cur.val == next.val) {
				next = next.next;
				cur.next = next;
			} else {
				cur = next;
				next = next.next;
			}
		}
		return head;
	}
}
