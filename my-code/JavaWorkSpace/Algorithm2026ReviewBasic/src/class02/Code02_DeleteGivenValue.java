package class02;

public class Code02_DeleteGivenValue {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 删除给定数字
	public static Node removeGivenNum(Node head, int value) {
		while (head != null) {// 删除头部数字
			if (head.value != value)
				break;
			head = head.next;
		}
		Node cur = head;
		Node pre = head;
		while (cur != null) {
			if (cur.value != value) {
				pre = cur;
			} else {
				pre.next = cur.next;
			}
			cur = cur.next;
		}
		return head;
	}
}
