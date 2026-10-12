package class06;

import java.util.HashMap;

public class Code04_CopyListWithRandom {
	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	// 使用HashMap
	public static Node copyWithRandomA(Node head) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> nodeMap = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			nodeMap.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			nodeMap.get(cur).next = nodeMap.get(cur.next);
			nodeMap.get(cur).rand = nodeMap.get(cur.rand);
		}
		return nodeMap.get(head);
	}

	// 在原链表赋值
	public static Node copyWithRandomB(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		while (cur != null) {// 复制节点
			Node next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		while (cur != null) {// 随机节点连接
			if (cur.rand != null) {
				cur.next.rand = cur.rand.next;
			}
			cur = cur.next.next;
		}
		Node res = head.next;
		cur = head;
		while (cur != null) {
			Node copy = cur.next;
			cur.next = copy.next;
			copy.next = cur.next == null ? null : cur.next.next;
			cur = cur.next;
		}
		return res;
	}
}
