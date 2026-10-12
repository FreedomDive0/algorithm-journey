package 链表;

public class Problem_0138_Copy_List_with_Random_Pointer {
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
		while (cur != null) {// 复制主链
			Node next = cur.next;
			Node copy = new Node(cur.val);
			cur.next = copy;
			copy.next = next;
			cur = next;
		}
		Node cur1 = head.next;
		cur = head;
		while (cur != null) {// 复制随机对应
			cur1.random = cur.random == null ? null : cur.random.next;
			cur = cur1.next;
			cur1 = cur == null ? null : cur.next;
		}
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
