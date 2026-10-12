package class06;

import java.util.HashMap;
import java.util.Stack;

public class Review1 {
	public static class Node {
		int value;
		Node next;
		Node rand;

		public Node(int value) {
			this.value = value;
			this.next = null;
			this.rand = null;
		}

	}

	// 1.链表的中点
	// (1)奇数中点和偶数上中点
	public static Node midOrUpmid(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		Node slow = head.next;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// (2)奇数中点和偶数下中点
	public static Node midOrDownMid(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node slow = head.next;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// (3)奇数中点前一个和偶数上中点前一个
	public static Node midPOrUpmidP(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// (4)奇数中点前一个和偶数下中点前一个
	public static Node midPOrDownmidP(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		Node slow = head;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	// 2.判断是否为回文结构
	// (1)使用栈结构
	public static boolean isPlalindrome1(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while (cur != null) {
			stack.add(new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (!stack.isEmpty()) {
			if (stack.pop().value != cur.value) {
				return false;
			}
			cur = cur.next;
		}
		return true;
	}

	// (2)在链表上操作
	public static boolean isPlaindrome2(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Node n1 = head;
		Node n2 = head;
		while (n2.next != null && n2.next.next != null) {
			n2 = n2.next.next;
			n1 = n1.next;
		}
		n2 = n1.next;// n2右部分头结点cur
		n1.next = null;// n1相当于pre
		Node n3 = null;// n3相当于next
		while (n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}
		n2 = head;
		n3 = n1;
		while (n2 != null && n3 != null) {
			if (n2.value != n3.value) {
				return false;
			}
			n2 = n2.next;
			n3 = n3.next;
		}
		n2 = null;// n1是cur n2是pre n3是next
		while (n3 != null) {
			n3 = n1.next;
			n1.next = n2;
			n2 = n1;
			n1 = n3;
		}
		return true;
	}

	// 3.给定链表 划分为小中大区 返回
	// (1)数组partition
	public static Node partitionNode1(Node head, int num) {
		if (head == null || head.next == null) {
			return head;
		}
		int i = 0;
		Node cur = head;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		int[] arr = new int[i];
		cur = head;
		for (int j = 0; j < arr.length; j++) {
			arr[j] = cur.value;
			cur = cur.next;
		}
		int less = -1;
		int more = arr.length;
		i = 0;
		while (i < more) {
			if (arr[i] < num) {
				swap(arr, i++, ++less);
			} else if (arr[i] > num) {
				swap(arr, i, --more);
			} else {
				i++;
			}
		}
		cur = head;
		for (int j = 0; j < arr.length; j++) {
			cur.value = arr[j];
			cur = cur.next;
		}
		return head;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// (2)六个引用相连
	public static Node partitionNode2(Node head, int num) {
		if (head == null || head.next == null) {
			return head;
		}
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node mH = null;
		Node mT = null;
		Node cur = head;
		while (cur != null) {
			if (cur.value < num) {
				if (sH == null) {
					sH = cur;
					sT = cur;
				} else {
					sT.next = cur;
					sT = sT.next;
				}
			} else if (cur.value == num) {
				if (eH == null) {
					eH = cur;
					eT = cur;
				} else {
					eT.next = cur;
					eT = eT.next;
				}
			} else {
				if (mH == null) {
					mH = cur;
					mT = cur;
				} else {
					mT.next = cur;
					mT = mT.next;
				}
			}
			Node next = cur.next;
			cur.next = null;
			cur = next;
		}
		if (sT != null) {
			sT.next = eH;
			eT = eT == null ? sT : eT;// 处理sT存在 eT不存在的情况
		}
		if (eT != null) {
			eT.next = mH;
		}
		return sH != null ? sH : eH != null ? eH : mH;
	}

	// 4.复制含有random指针的链表
	// (1)使用HashMap
	public static Node copyNR1(Node head) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		Node res = map.get(cur);
		while (cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return res;
	}

	// (2)共链复制
	public static Node copyNR2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		while (cur != null) {// 复制节点
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		while (cur != null) {// 复制random
			next = cur.next;// next即为copy
			next.rand = cur.rand != null ? cur.rand.next : null;// rand可能为空 所以要先判断
			cur = cur.next.next;// 没有更改next指针所以可以直接跳
		}
		Node res = head.next;// 直接为head.next
		Node copy = null;
		cur = head;
		while (cur != null) {// 断链
			next = cur.next.next;// 先保存
			copy = cur.next;// 得到copy头
			cur.next = next;
			copy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

	// 5.可能有环或可能无环的链 if相交返回第一个相交的点 else return null
	public static Node FindFirstIntersectNode(Node h1, Node h2) {
		if (h1 == null || h2 == null) {
			return null;
		}
		Node c1 = isCycle(h1);
		Node c2 = isCycle(h2);
		if (c1 == null && c2 == null) {
			return isIntersect(h1, h2);
		} else if (c1 != null && c2 != null) {
			return bothCycle(h1, h2, c1, c2);
		} else {
			return null;
		}
	}

	// (1)判断是否有环 若有返回入环节点 无环返回null
	public static Node isCycle(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head.next;
		Node fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}

	// (2)判断两个无环链表是否相交
	public static Node isIntersect(Node h1, Node h2) {
		if (h1 == null || h2 == null) {
			return null;
		}
		Node n1 = h1;
		Node n2 = h2;
		int n = 0;
		while (n1.next != null) {
			n++;
			n1 = n1.next;
		}
		while (n2.next != null) {
			n--;
			n2 = n2.next;
		}
		if (n1 != n2) {
			return null;
		}
		n1 = n > 0 ? h1 : h2;
		n2 = n1 == h1 ? h2 : h1;
		n = Math.abs(n);// n一定要绝对值 否则循环不结束
		while (n != 0) {
			n1 = n1.next;
			n--;
		}
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	// (3)判断两个有环链表是否相交
	public static Node bothCycle(Node h1, Node h2, Node c1, Node c2) {
		if (c1 == c2) {// 入环点相不相同需分开讨论 只用else中方法会丢掉 c1==c2的情况
			Node n1 = h1;
			Node n2 = h2;
			int n = 0;
			while (n1.next != c1) {
				n++;
				n1 = n1.next;
			}
			while (n2.next != c2) {
				n--;
				n2 = n2.next;
			}
			if (n1 != n2) {
				return c1;
			}
			n1 = n > 0 ? h1 : h2;
			n2 = n1 == h1 ? h2 : h1;
			n = Math.abs(n);// n一定要绝对值 否则循环不结束
			while (n != 0) {
				n1 = n1.next;
				n--;
			}
			while (n1 != n2) {
				n1 = n1.next;
				n2 = n2.next;
			}
			return n2;
		} else {
			Node cur = c1.next;//
			while (cur != c1) {
				if (cur == c2) {
					return cur;
				}
				cur = cur.next;
			}
			return null;
		}
	}
}
