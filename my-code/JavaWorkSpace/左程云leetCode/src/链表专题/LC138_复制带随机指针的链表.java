package 链表专题;

public class LC138_复制带随机指针的链表 {
	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	public Node copyRandomList(Node head) {
		if (head == null) {
			return head;
		}
		Node cur = head;
//		复制节点形成对应关系
		while (cur != null) {
			Node copy = new Node(cur.val);
			Node next = cur.next;
			cur.next = copy;
			copy.next = next;
			cur = next;
		}
//		复制random
		cur = head;
		Node cur1 = head.next;
		while (cur != null) {
			cur1.random = cur.random != null ? cur.random.next : null;
			cur = cur1.next;
			cur1 = cur == null ? null : cur.next;
		}
//		分离
		cur = head;
		cur1 = head.next;
		Node copy = cur1;
		while (cur != null) {
			cur.next = cur.next.next;
			cur1.next = cur1.next == null ? null : cur1.next.next;
			cur = cur.next;
			cur1 = cur1.next;
		}
		return copy;
	}
}
