package class02;

public class Code02_DeleteGivenValue {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node deleteGivenValue(Node head, int value) {
		while (head != null && head.value == value) {
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == value) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

	public static void main(String[] args) {
		Node n1 = new Node(0);
		Node n2 = new Node(1);
		Node n3 = new Node(2);
		Node n4 = new Node(3);
		Node n5 = new Node(4);
		Node n6 = new Node(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		Node cur = deleteGivenValue(n1, 4);
		while (cur != null) {
			System.out.println(cur.value);
			cur = cur.next;
		}
	}
}
