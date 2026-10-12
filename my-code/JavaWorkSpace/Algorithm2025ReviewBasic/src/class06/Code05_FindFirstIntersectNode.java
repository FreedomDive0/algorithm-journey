package class06;

public class Code05_FindFirstIntersectNode {
	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
			next = null;
		}

	}

	public static Node findFirstIntersectNode(Node h1, Node h2) {
		if (h1 == null || h2 == null) {
			return null;
		}
		Node loop1 = findFirstInLoop(h1);
		Node loop2 = findFirstInLoop(h2);
		Node res = null;
		if (loop1 == null && loop2 == null) {
			res = noLoopFisrtIntersectNode(h1, h2);
		} else if (loop1 != null && loop2 != null) {
			res = bothLoopFisrtIntersectNode(h1, h2, loop1, loop2);
		}
		return res;
	}

	// 找到第一个入环的节点
	public static Node findFirstInLoop(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node fast = head.next.next;// 先走一步 为了使while条件成立
		Node slow = head.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {// 第一次相遇
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (fast != slow) {// fast返回重新走 设链长度为a 在环内走b相遇 环长度为c
			fast = fast.next;// ∵fast比slow快走1 所以fast会在c-b步追上slow在一圈之内
			slow = slow.next;// 又∵ fast走过距离: a+b+c*n slow走过距离: a+b 且fast走过距离是slow两倍 可得公式 a+b+n*c == 2*(a+b)
			// 化简可得 a+b == n*c
			// 变形可得 (n-1)*c+c-b == a 意味着无论slow在环内走多少圈 一定和fast从头走 在入环第一个节点相遇
		}
		return slow;
	}

	// 无环第一个相交节点
	public static Node noLoopFisrtIntersectNode(Node h1, Node h2) {
		int n = 0;
		Node c = h1;
		while (c != null) {
			c = c.next;
			n++;
		}
		c = h2;
		while (c != null) {
			c = c.next;
			n--;
		}
		Node l = n > 0 ? h1 : h2;
		Node s = l == h1 ? h2 : h1;
		n = Math.abs(n);
		while (n != 0) {// 双链对齐
			l = l.next;
			n--;
		}
		while (l != s) {
			l = l.next;
			s = s.next;
		}
		return l;
	}

	// 两个有环第一个相交
	public static Node bothLoopFisrtIntersectNode(Node h1, Node h2, Node l1, Node l2) {
		if (l1 == l2) {
			int n = 0;
			Node c = h1;
			while (c != l1) {
				c = c.next;
				n++;
			}
			c = h2;
			while (c != l2) {
				c = c.next;
				n--;
			}
			Node l = n > 0 ? h1 : h2;
			Node s = l == h1 ? h2 : h1;
			n = Math.abs(n);
			while (n != 0) {
				l = l.next;
				n--;
			}
			while (l != s) {
				l = l.next;
				s = s.next;
			}
			return l;
		} else {
			Node c = l1.next;
			Node res = null;
			while (c != l1) {
				if (c == l2) {
					res = l1;
					break;
				}
				c = c.next;
			}
			return res;
		}
	}
}
