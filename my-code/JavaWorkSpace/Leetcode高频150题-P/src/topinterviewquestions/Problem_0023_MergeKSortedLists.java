package topinterviewquestions;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0023_MergeKSortedLists {
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static class ListNodeCom implements Comparator<ListNode> {

		@Override
		public int compare(ListNode o1, ListNode o2) {
			return o1.val - o2.val;
		}

	}

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length < 1) {
			return null;
		}
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(new ListNodeCom());
		for (ListNode cur : lists) {
			if (cur != null)
				heap.add(cur);
		}
		if (heap.isEmpty()) {
			return null;
		}
		ListNode head = heap.poll();
		ListNode pre = head;
		if (head.next != null) {
			heap.add(head.next);
		}
		while (!heap.isEmpty()) {
			ListNode cur = heap.poll();
			pre.next = cur;
			if (cur.next != null) {
				heap.add(cur.next);
			}
			pre = pre.next;
		}
		return head;
	}
}
