package 链表专题;

import java.util.ArrayList;
import java.util.List;

public class LC160_相交链表 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		int n = 0;
		ListNode cur1 = headA;
		ListNode cur2 = headB;
		List<ListNode> list = new ArrayList<ListNode>();
		while (cur1.next != null) {
			list.add(cur1);
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			if (list.contains(cur2)) {
				return list.get(list.indexOf(cur2));
			}
			cur2 = cur2.next;
		}
		return null;
	}
}
