package 链表;

public class Problem_0092_Reverse_Linked_List_II {
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

	public ListNode reverseBetween(ListNode head, int left, int right) {
		if (left == 1) {
			return reverseN(head, right);
		}
		head.next = reverseBetween(head.next, left - 1, right - 1);
		return head;
	}

	ListNode successor = null;

	public ListNode reverseN(ListNode head, int n) {
		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode newHead = reverseN(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return newHead;
	}
}
