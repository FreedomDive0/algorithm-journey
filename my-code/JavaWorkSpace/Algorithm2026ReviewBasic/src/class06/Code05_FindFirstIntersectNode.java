package class06;

public class Code05_FindFirstIntersectNode {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node findFirstIntersectNode(Node n1, Node n2) {
		if (n1 == null || n2 == null) {
			return null;
		}
		Node loopNode1 = getFirstNodeInLoop(n1);
		Node loopNode2 = getFirstNodeInLoop(n2);
//		System.out.println("loopNode1: " + loopNode1 + " loopNode2: " + loopNode2);
		if (loopNode1 == null && loopNode2 == null) {
			return getFirstNodeBothNoLoop(n1, n2);
		}
		if (loopNode1 != null && loopNode2 != null) {
			return getFirstNodeBothLoop(n1, n2, loopNode1, loopNode2);
		}
		return null;
	}

	public static Node getFirstNodeInLoop(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node fast = head.next.next;
		Node slow = head.next;
		while (fast != slow) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}

	public static Node getFirstNodeBothNoLoop(Node n1, Node n2) {
		if (n1 == null || n2 == null) {
			return null;
		}
		int delta = 0;
		Node cur = n1;
		while (cur != null) {
			delta++;
			cur = cur.next;
		}
		cur = n2;
		while (cur != null) {
			delta--;
			cur = cur.next;
		}
		Node large = delta > 0 ? n1 : n2;
		Node small = n1 == large ? n2 : n1;
		delta = Math.abs(delta);
		while (delta > 0) {
			large = large.next;
			delta--;
		}
		while (large != small) {
			large = large.next;
			small = small.next;
		}
		return large;
	}

	public static Node getFirstNodeBothLoop(Node n1, Node n2, Node l1, Node l2) {
		if (n1 == null || n2 == null) {
			return null;
		}
		if (l1 == l2) {
			Node cur = n1;
			int delta = 0;
			while (cur != l1) {
				delta++;
				cur = cur.next;
			}
			cur = n2;
			while (cur != l2) {
				delta--;
				cur = cur.next;
			}
			Node large = delta > 0 ? n1 : n2;
			Node small = large == n1 ? n2 : n1;
			delta = Math.abs(delta);
			while (delta > 0) {
				large = large.next;
				delta--;
			}
			while (large != small) {
				large = large.next;
				small = small.next;
			}
			return large;
		} else {
			Node cur = l1.next;
			while (cur != l1) {
				if (cur == l2) {
					return l1;
				}
				cur = cur.next;
			}
			return null;
		}
	}
}
