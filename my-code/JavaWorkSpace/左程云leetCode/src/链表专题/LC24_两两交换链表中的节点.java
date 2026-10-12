package 链表专题;

public class LC24_两两交换链表中的节点 {
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

	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode last = swapPairs(head.next.next);
		ListNode newHead = head.next;// 记录新头
		head.next.next = head;// 交换
		head.next = last;// 连接
		return newHead;// 返回新头
	}

}
