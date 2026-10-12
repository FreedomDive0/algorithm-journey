package 链表专题;

public class LC25_K个一组翻转链表 {
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

//	public ListNode reverseKGroup(ListNode head, int k) {
//		if (head == null || head.next == null) {
//			return head;
//		}
//		ListNode h = new ListNode(0);
//		ListNode res = h;
//		ListNode[] node = { head, head, head };
//		while (node[0] != null && node[1] != null) {
//			node = reverse(node[0], k);
//			h.next = node[0];
//			h = node[1];
//			node[0] = node[2];
//		}
//		return res.next;
//	}
//
//	public ListNode[] reverse(ListNode head, int k) {
//		Stack<ListNode> stack = new Stack<ListNode>();
//		ListNode h = head;
//		while (head != null && k > 0) {
//			stack.add(head);
//			head = head.next;
//			k--;
//		}
//		if (k > 0) {
//			return new ListNode[] { h, null, head };
//		}
//		h = stack.pop();
//		ListNode cur = h;
//		ListNode next = null;
//		while (!stack.isEmpty()) {
//			next = stack.pop();
//			next.next = null;
//			cur.next = next;
//			cur = cur.next;
//		}
//		return new ListNode[] { h, next, head };
//	}
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode tail = head;
		for (int i = 0; i < k; i++) {// 得到反转区间 [head,tail)需要在head到tail-1翻转 tail是下次的开始
			if (tail == null) {
				return head;
			}
			tail = tail.next;
		}
		// 记录反转后的头节点
		ListNode newHead = reverse(head, tail);
		// 将反转链表与下一段连接
		head.next = reverseKGroup(tail, k);
		return newHead;
	}

	public static ListNode reverse(ListNode head, ListNode tail) {
		ListNode pre = null;
		ListNode next = null;
		while (head != tail) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;// head会移动到反转的末尾节点
		}
		return pre;// pre会移动到反转的头节点
	}
}
