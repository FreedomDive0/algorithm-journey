package class06;

import java.util.Stack;

public class Code02_IsPalindromeList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 使用Stack
	public static boolean isPalindrome1(Node head) {
		if (head == null || head.next == null)
			return true;
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		cur = head;
		while (!stack.isEmpty()) {
			if (stack.pop().value != cur.value)
				return false;
			cur = cur.next;
		}
		return true;
	}

	public static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null)
			return true;
		Node fast = head;
		Node slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		Node h1 = reverse(slow);
		fast = head;
		slow = h1;
		boolean res = true;
		while (fast != null && slow != null) {
			if (fast.value != slow.value)
				res = false;
			fast = fast.next;
			slow = slow.next;
		}
		reverse(h1);
		return res;
	}

	public static Node reverse(Node head) {
		if (head == null || head.next == null)
			return head;
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
}
