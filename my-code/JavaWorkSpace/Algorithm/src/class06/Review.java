package class06;

public class Review {
	public static class Node {
		int value;
		Node next;
		Node rand;

		public Node(int value) {
			this.value = value;
			this.next = null;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	// 1.中点问题
	// 返回上中点
	public static Node upMid(Node head) {
		if (head == null) {
			return head;
		}
		Node fast = head;
		Node slow = head;
		while (fast != null) {
			if (fast.next == null || fast.next.next == null) {
				return slow;
			}
			Node next = fast.next.next;
			slow = slow.next;
			fast = next;
		}
		return slow;
	}

	// 返回下中点
	public static Node downMid(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node fast = head.next;
		Node slow = head.next;
		while (fast != null) {
			if (fast.next == null || fast.next.next == null) {
				return slow;
			}
			Node next = fast.next.next;
			slow = slow.next;
			fast = next;
		}
		return slow;
	}

	// 返回上中点前一个
	public static Node upMidPre(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		Node fast = head.next.next;
		Node slow = head;
		while (fast != null) {
			if (fast.next == null || fast.next.next == null) {
				return slow;
			}
			Node next = fast.next.next;
			slow = slow.next;
			fast = next;
		}
		return slow;
	}

	// 下中点前一个
	public static Node downMidPre(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		Node fast = head.next;
		Node slow = head;
		while (fast != null) {
			if (fast.next == null || fast.next.next == null) {
				return slow;
			}
			Node next = fast.next.next;
			slow = slow.next;
			fast = next;
		}
		return slow;
	}

	// 2.回文字符串
	public static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		// 获得上中点
		Node fast = head;
		Node slow = head;
		while (fast != null) {
			if (fast.next == null || fast.next.next == null) {
				break;
			}
			Node next = fast.next.next;
			slow = slow.next;
			fast = next;
		}
		// 反转列表
		Node head2 = reverseLinkedList(slow.next);// 上中点下一个为 回文另一部分开头
		Node head3 = head2;
		// 比较
		boolean res = true;
		Node node = head;
		while (node != null && head3 != null) {
			if (node.value != head3.value) {
				res = false;// 先不要返回 复原链表再返回
			}
			node = node.next;
			head3 = head3.next;
		}
		reverseLinkedList(head2);
		return res;
	}

//	单链表逆转
	public static Node reverseLinkedList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;// 保存
			head.next = pre;// 反转
			pre = head;// 移动
			head = next;
		}
		return pre;// 最后head->next==null 所以返回pre
	}

	// 3.链表分为小中大三部分
	public static Node SmallEqualBig(Node head, int num) {
		if (head == null || head.next == null) {
			return head;
		}
		// 设置三部分开头结尾 用于最后连接
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node bH = null;
		Node bT = null;
		Node node = head;
		// 分配
		while (node != null) {
			// 为了不使用额外空间
			Node next = node.next;
			node.next = null;
			if (node.value < num) {
				if (sH == null) {
					sH = node;
					sT = node;
				} else {
					sT.next = node;
					sT = node;
				}
			} else if (node.value == num) {
				if (eH == null) {
					eH = node;
					eT = node;
				} else {
					eT.next = node;
					eT = node;
				}
			} else {
				if (bH == null) {
					bH = node;
					bT = node;
				} else {
					bT.next = node;
					bT = node;
				}
			}
			node = next;// 更新节点
		}
		// 连接
		if (sT != null) {// 判断尾部即可
			sT.next = eH;
			eT = eH == null ? sT : eT;
		}
		if (eH != null) {
			eT.next = bH;
		}
		return sH != null ? sH : eH != null ? eH : bH;
	}

	// 4.含rand的链表复制
	public static Node copyRand(Node head) {
		if (head == null) {
			return null;
		}
		Node node = head;
		// 复制相邻链表 Rand节点也具有相邻关系
		while (node != null) {
			Node next = node.next;
			node.next = new Node(node.value);
			node.next.next = next;
			node = next;
		}
		// 复制Rand关系
		node = head;
		Node copy = null;
		while (node != null) {
//			copy.rand = node.rand != null ? node.rand : null;
//			node = copy.next;
//			copy = node != null ? node.next : null;
			Node next = node.next.next;
			copy = node.next;
			copy.rand = node.rand != null ? node.rand.next : null;
			node = next;
		}
		// 断开链表
		node = head;
		copy = head.next;
		Node res = copy;
		while (node != null) {
//			node.next = copy.next;//未更新copy和node
//			copy.next = node.next;
			Node next = node.next.next;// 开头更新copy只用考虑next.next情况 简化判断
			copy = node.next;
			node.next = next;
			copy.next = next == null ? null : next.next;
			node = next;
		}
		return res;
	}

	// 5.给定两个可能有环可能无环的链表 如果相交返回第一个相交的节点
	public static Node FirstIntersect(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node n1 = isCycle(head1);
		Node n2 = isCycle(head2);
		System.out.println(n1);
		System.out.println(n2);
		Node res = null;
		if (n1 == null && n2 == null) {
			res = noCycle(head1, head2);
		} else if (n1 != null && n2 != null) {
			if (n1 == n2) {
				Node next1 = n1.next;
				Node next2 = n2.next;
				n1.next = null;
				n2.next = null;
				res = noCycle(head1, head2);
				n1.next = next1;
				n2.next = next2;
			} else {
				Node mark = n1;
				n1 = n1.next;
				while (n1 != mark) {
					if (n1 == n2) {
						return mark;
					}
					n1 = n1.next;
				}
			}
		}
		return res;
	}

	// 获得入环FirstNode
	public static Node isCycle(Node head) {
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

	// 不含环处理
	public static Node noCycle(Node head1, Node head2) {
		int n = 0;
		Node n1 = head1;
		Node n2 = head2;
		while (n1 != null) {
			n++;
			n1 = n1.next;
		}
		while (n2 != null) {
			n--;
			n2 = n2.next;
		}
		if (n1 != n2) {// 结尾不相同 没有相交
			return null;
		}
		n1 = n > 0 ? head1 : head2;// n1存放长链表
		n2 = n1 == head1 ? head2 : head1;
		n = Math.abs(n);
		while (n != 0) {
			n--;
			n1 = n1.next;
		}
		while (n1 != null) {
			if (n1 == n2) {
				return n1;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		return null;
	}

	public static void main(String[] args) {
		Node node = new Node(1);
		Node node1 = new Node(2);
		Node node2 = new Node(3);
		Node node3 = new Node(4);
		Node node4 = new Node(5);
		Node node5 = new Node(6);
		node.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		System.out.println(downMidPre(node));

	}
}
