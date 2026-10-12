package class04;

public class Code04_JosephusProblem {
	// 约瑟夫环问题
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node JosephusProblem(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node last = head.next;
		int count = 1;
		while (last != head) {
			last = last.next;
			count++;
		}
		int live = getLive(count, m);
		while (--live > 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}

	// 获得i个节点 数到m就杀掉 返回最后活着的节点 在i个节点中的编号
	public static int getLive(int i, int m) {
		if (i == 1) {
			return i;
		}
		return (getLive(i - 1, m) + m - 1) % i + 1;// getLive(i - 1, m)新
	}
}
