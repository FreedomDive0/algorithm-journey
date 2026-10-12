package class06;

import java.util.Stack;

public class Code02_IsPalindromeList {
	public static class Node {
		int value;
		Node next;

		public Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}

	}

	// 1.笔试
	public static boolean isPalindrome1(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Stack<Node> stack = new Stack<Node>();
		while (cur != null) {
			stack.add(cur);
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

	// 2.面试
	public static boolean isPalindrome2(Node head) {
		if (head == null) {
			return false;
		}
		Node fast = head;
		Node slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		Node pre = null;
		while (slow != null) {
			Node next = slow.next;
			slow.next = pre;
			pre = slow;
			slow = next;
		}
		Node h1 = pre;// 翻转链表的头
		slow = head;
		boolean res = true;
		while (pre != null && slow != null) {
			if (pre.value != slow.value) {
				res = false;
				break;
			}
			pre = pre.next;
			slow = slow.next;
		}
		pre = null;
		while (h1 != null) {
			Node next = h1.next;
			h1.next = pre;
			pre = h1;
			h1 = next;
		}
		return res;
	}
}
