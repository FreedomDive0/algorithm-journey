package 分治;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0023_Merge_K_Sorted_Lists {
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

	public class NodeCom implements Comparator<ListNode> {

		@Override
		public int compare(ListNode o1, ListNode o2) {
			// TODO Auto-generated method stub
			return o1.val - o2.val;
		}

	}

	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new NodeCom());
		for (ListNode node : lists) {
			if (node != null) {
				queue.add(node);
			}
		}
		ListNode head = new ListNode();
		ListNode cur = head;
		while (!queue.isEmpty()) {
			ListNode node = queue.poll();
			cur.next = node;
			cur = cur.next;
			if (node.next != null) {
				queue.add(node.next);
			}
		}
		return head.next;
	}
}
