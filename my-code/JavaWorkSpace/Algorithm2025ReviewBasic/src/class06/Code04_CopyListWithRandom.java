package class06;

import java.util.HashMap;

public class Code04_CopyListWithRandom {
	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int value) {
			this.value = value;
			next = null;
			rand = null;
		}

	}

	// 1.笔试
	public static Node copyListWithRandom1(Node head) {
		if (head == null) {
			return head;
		}
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			Node copy = map.get(cur);
			copy.next = map.get(cur.next);
			copy.rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}

	// 2.面试
	public static Node copyListWithRandom2(Node head) {
		if (head == null) {
			return head;
		}
		Node cur = head;
		Node next = null;
		while (cur != null) {// 复制链表
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node copy = null;
		while (cur != null) {// 连接随机节点
			copy = cur.next;
			copy.rand = cur.rand != null ? cur.rand.next : null;
			cur = copy.next;
		}
		cur = head;
		Node copyHead = head.next;
		while (cur != null) {
			copy = cur.next;
			next = cur.next.next;
			cur.next = next;
			cur = next;
			copy.next = next == null ? null : next.next;
			copy = copy.next;
		}
		return copyHead;
	}
}
