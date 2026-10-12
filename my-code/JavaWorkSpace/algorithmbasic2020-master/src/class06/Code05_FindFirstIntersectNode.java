package class06;

public class Code05_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
//		System.out.println(loop1);
//		System.out.println(loop2);
//		System.out.println("loop1: " + loop1 + " loop2: " + loop2);
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		return null;
	}

	// 找到链表第一个入环节点，如果无环，返回null
	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		// n1 慢 n2 快
		Node n1 = head.next; // n1 -> slow
		Node n2 = head.next.next; // n2 -> fast
		while (n1 != n2) {
			if (n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		n2 = head; // n2 -> walk again from head
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	// 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		if (cur1 != cur2) {
			return null;
		}
		// n : 链表1长度减去链表2长度的值
		cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
		cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
		n = Math.abs(n);
		while (n != 0) {
			n--;
			cur1 = cur1.next;
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	// 两个有环链表，返回第一个相交节点，如果不想交返回null
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			cur1 = loop1.next;
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	// 测试2026
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

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2));
		System.out.println(findFirstIntersectNode(head1, head2));
		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2));
		System.out.println(findFirstIntersectNode(head1, head2));
		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2));
		System.out.println(findFirstIntersectNode(head1, head2));
	}

}
