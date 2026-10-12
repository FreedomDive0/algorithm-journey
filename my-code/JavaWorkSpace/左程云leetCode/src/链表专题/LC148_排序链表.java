package 链表专题;

public class LC148_排序链表 {
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

	// 链表归并排序 递归实现
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {// 找到中点
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode tmp = slow.next;
		slow.next = null;// 拆链
		ListNode left = sortList(head);
		ListNode right = sortList(tmp);
		ListNode h = new ListNode(0);
		ListNode res = h;// 记录拼接后的头结点
		while (left != null && right != null) {
			if (left.val < right.val) {
				h.next = left;
				left = left.next;
			} else {
				h.next = right;
				right = right.next;
			}
			h = h.next;
		}
		h.next = left != null ? left : right;// 剩余部分补接
		return res.next;
	}
}
