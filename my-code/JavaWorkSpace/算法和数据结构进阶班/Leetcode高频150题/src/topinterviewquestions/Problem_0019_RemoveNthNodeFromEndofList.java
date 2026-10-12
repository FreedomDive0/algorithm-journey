package topinterviewquestions;

public class Problem_0019_RemoveNthNodeFromEndofList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			n--;// 先-- 后移动
			if (n == -1) {// 准备开始移动
				pre = head;
			}
			if (n < -1) {
				pre = pre.next;
			}
			cur = cur.next;
		}
		if (n > 0) { // 因为cur从head走到null 所以个数正好是长度
			return head;
		}
		if (pre == null) {
			return head.next;
		}
		pre.next = pre.next.next;
		return head;
	}

}
