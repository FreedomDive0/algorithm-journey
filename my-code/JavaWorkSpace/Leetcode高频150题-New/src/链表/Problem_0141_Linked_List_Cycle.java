package 链表;

public class Problem_0141_Linked_List_Cycle {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return false;
		}
		ListNode fast = head.next.next;
		ListNode slow = head.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		return true;
	}
}
