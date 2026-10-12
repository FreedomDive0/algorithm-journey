package 链表;

public class Problem_0086_Partition_List {
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

	public ListNode partition(ListNode head, int x) {
		if (head == null) {
			return head;
		}
		ListNode sH = new ListNode(0);
		ListNode sE = sH;
		ListNode lH = new ListNode(0);
		ListNode lE = lH;
		while (head != null) {
			if (head.val < x) {
				sE.next = head;
				sE = sE.next;
			} else {
				lE.next = head;
				lE = lE.next;
			}
			head = head.next;
		}
		lE.next = null;
		sE.next = lH.next;
		return sH.next;
	}
}
