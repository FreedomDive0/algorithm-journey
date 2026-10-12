package 链表专题;

public class LC19_删除链表的倒数第N个结点 {
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

//	public ListNode removeNthFromEnd(ListNode head, int n) {
//		int num = 0;
//		ListNode cur = head;
//		while (cur != null) {
//			num++;
//			cur = cur.next;
//		}
//		if (num == 1) {
//			return null;
//		}
//		cur = head;
//		ListNode pre = null;
//		for (int i = 0; i < num - n; i++) {
//			pre = cur;
//			cur = cur.next;
//		}
//		if (cur == head) {
//			ListNode node = cur.next;
//			cur.next = null;
//			return node;
//		} else {
//			ListNode node = cur.next;
//			pre.next = node;
//			cur.next = null;
//			return head;
//		}
//	}
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = new ListNode(0);
		pre.next = head;// 预指针 因为head可能被删除
		ListNode start = pre, end = pre;
		while (n != 0) {
			start = start.next;
			n--;
		}
		while (start.next != null) {
			start = start.next;
			end = end.next;// end会移动到被删除节点的前一个
		}
		end.next = end.next.next;
		return pre.next;// 因为head已被引用所以会被修改
	}
}
