package 链表专题;

public class LC61_旋转链表 {
//	public ListNode rotateRight(ListNode head, int k) {
//		ListNode node = head;
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		while (node != null) {
//			list.add(node.val);
//			node = node.next;
//		}
//		node = head;
//		int index = list.size() - k;
//		for (int i = 0; i < list.size(); i++) {
//			if (index == list.size()) {
//				index %= list.size();
//			}
//			node.val = list.get(index++);
//		}
//		return head;
//	}
	public static ListNode rotateRight(ListNode head, int k) {
//		if (head == null || k == 0) {
//			return head;
//		}
//		ListNode start = head, end = head;
//		int n = 1;
//		while (start.next != null) {
//			start = start.next;
//			n++;
//		}
//		k %= n;
//		System.out.println("k=" + k + " n=" + n);
//		for (int i = 0; i < n - k - 1; i++) {
//			end = end.next;
//		}
//		start.next = head;
//		ListNode newHead = end.next;
//		end.next = null;
//		return newHead;
		if (head == null || k == 0)
			return head;
		int n = 0; // 链表的长度
		ListNode tail = null; // 尾节点
		for (ListNode p = head; p != null; p = p.next) {
			tail = p;
			n++;
		}
		k %= n;
		ListNode p = head;
		for (int i = 0; i < n - k - 1; i++)
			p = p.next; // 找到链表的第n-k个节点
		tail.next = head;// 对于长度唯1 连接顺序会影响结果
		head = p.next;
		p.next = null;
		return head; // 返回新的头节点
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(0);
//		ListNode node2 = new ListNode(1);
//		ListNode node3 = new ListNode(2);
//		node1.next = node2;
//		node2.next = node3;
		node1.next = null;
		rotateRight(node1, 1);
	}

}
