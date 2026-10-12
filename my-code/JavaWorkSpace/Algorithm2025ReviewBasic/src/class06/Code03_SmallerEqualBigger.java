package class06;

public class Code03_SmallerEqualBigger {
	public static class Node {
		int value;
		Node next;

		public Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}

	}

	// 1.笔试
	public static Node netherLand(Node head, int value) {
		if (head == null || head.next == null) {
			return head;
		}
		Node cur = head;
		int count = 0;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		Node[] arr = new Node[count];
		cur = head;
		int idx = 0;
		while (cur != null) {
			Node next = cur.next;
			arr[idx] = cur;
			arr[idx++].next = null;
			cur = next;
		}
		int less = -1;
		int more = count;
		idx = 0;
		while (idx < more) {
			if (arr[idx].value < value) {
				swap(arr, idx++, ++less);
			} else if (arr[idx].value == value) {
				idx++;
			} else {
				swap(arr, idx, --more);
			}
		}
		head = arr[0];
		cur = head;
		for (int i = 1; i < arr.length; i++) {
			cur.next = arr[i];
			cur = arr[i];
		}
		return head;
	}

	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	// 2.面试
	public static Node smallerEqualBigger(Node head, int value) {
		if (head == null || head.next == null) {
			return head;
		}
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node mH = null;
		Node mT = null;
		while (head != null) {
			Node next = head.next;// 需要将head断链 否则影响后续连接
			head.next = null;
			if (head.value < value) {
				if (sT == null) {
					sH = head;
					sT = head;
				} else {
					sT.next = head;
					sT = head;
				}
			} else if (head.value == value) {
				if (eT == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else {
				if (mT == null) {
					mH = head;
					mT = head;
				} else {
					mT.next = head;
					mT = head;
				}
			}
			head = next;
		}
		if (sH != null) {
			sT.next = eH;
			eH = eH != null ? eH : sT;// 确定下个区域的头
		}
		if (eH != null) {
			eT.next = mH;
		}
		return sH != null ? sH : eH != null ? eH : mH;
	}
}
