package 链表专题;

public class LC237_删除链表中的节点 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public void deleteNode(ListNode node) {
		ListNode tmp = node;
		ListNode pre = null;
		while (tmp.next != null) {
			tmp.val = tmp.next.val;
			pre = tmp;
			tmp = tmp.next;
		}
		pre.next = tmp.next;
		tmp.next = null;
	}
}
