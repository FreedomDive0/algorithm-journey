package 链表专题;

public class LC142_环形链表II {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

//	public ListNode detectCycle(ListNode head) {
//		if (head == null || head.next == null) {
//			return null;
//		}
//		List<ListNode> list = new ArrayList<ListNode>();
//		ListNode node = head;
//		while (node.next != null) {
//			if (list.contains(node)) {
//				return list.get(list.indexOf(node));
//			}
//			list.add(node);
//			node = node.next;
//		}
//		return null;
//	}
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		fast = head;
		while (slow != fast)

		{
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
}
