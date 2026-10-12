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
	
//	使用HashMap实现
	public static Node copyListWithRand1(Node head) {
		Node cur = head;
		Node res;
		HashMap<Node, Node> map = new HashMap<Node,Node>();
		while(cur!=null) {
			Node copy = new Node(cur.value);
			map.put(cur, copy);
			cur = cur.next;
		}
		cur = head;
		while(cur!=null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}
//	将复制链表嵌入原链表 形成对应关系 后分离
	public static Node copyListWithRand2(Node head) {
		if(head==null) {
			return head;
		}
		Node cur = head;
//		复制节点形成对应关系
		while(cur!=null) {
			Node copy = new Node(cur.value);
			Node next = cur.next;
			cur.next = copy;
			copy.next = next;
			cur = next;
		}
//		复制random
		cur = head;
		Node cur1 = head.next; 
		while(cur!=null) {
			cur1.rand = cur.rand!=null?cur.rand.next:null;
			cur = cur1.next;
			cur1 = cur==null?null:cur.next;
		}
//		分离
		cur = head;
		cur1 = head.next;
		Node copy = cur1;
		while(cur!=null) {
			cur.next = cur.next.next;
			cur1.next = cur1.next==null?null:cur1.next.next;
			cur = cur.next;
			cur1 = cur1.next;
		}
		return copy;
	}
	
	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}
}
