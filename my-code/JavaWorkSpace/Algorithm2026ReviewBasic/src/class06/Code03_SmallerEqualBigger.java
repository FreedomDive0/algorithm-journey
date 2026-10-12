package class06;

public class Code03_SmallerEqualBigger {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node sebNodeA(Node head, int num) {
		if (head == null || head.next == null) {
			return head;
		}
		int count = 0;
		Node cur = head;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		Node[] nodes = new Node[count];
		int less = -1;
		int more = count;
		int idx = 0;
		while (idx < more) {
			if (nodes[idx].value < num) {
				swap(nodes, idx++, ++less);
			} else if (nodes[idx].value > num) {
				swap(nodes, idx, --more);
			} else {
				idx++;
			}
		}
		cur = nodes[0];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i - 1].next = nodes[i];
		}
		nodes[count - 1].next = null;
		return nodes[0];
	}

	public static void swap(Node[] nodes, int i, int j) {
		Node tmp = nodes[i];
		nodes[i] = nodes[j];
		nodes[j] = tmp;
	}

	public static Node sebNodeB(Node head, int num) {
		if (head == null || head.next == null) {
			return head;
		}
		Node LH = null;
		Node LT = null;
		Node EH = null;
		Node ET = null;
		Node MH = null;
		Node MT = null;
		while (head != null) {
			if (head.value < num) {
				if (LT == null) {
					LH = head;
					LT = head;
				} else {
					LT.next = head;
					LT = head;
				}
			} else if (head.value > num) {
				if (MT == null) {
					MH = head;
					MT = head;
				} else {
					MT.next = head;
					MT = head;
				}
			} else {
				if (ET == null) {
					EH = head;
					ET = head;
				} else {
					ET.next = head;
					ET = head;
				}
			}
		}
		if (LH != null) {
			LT.next = EH;
			EH = EH == null ? LH : EH;// 与大于区连接做准备
		}
		if (EH != null) {
			EH.next = MH;
		}
		return LH != null ? LH : EH != null ? EH : MH;
	}

}
