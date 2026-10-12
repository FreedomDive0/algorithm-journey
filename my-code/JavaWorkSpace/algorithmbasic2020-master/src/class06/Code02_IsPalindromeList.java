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

	// need n extra space
	public static boolean isPalindrome1(Node head) {
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while (head != null) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// need n/2 extra space
	public static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Node right = head.next;
		Node cur = head;
		while (cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		while (right != null) {
			stack.push(right);
			right = right.next;
		}
		while (!stack.isEmpty()) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// need O(1) extra space
	public static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Node n1 = head;
		Node n2 = head;
		while (n2.next != null && n2.next.next != null) { // find mid node
			n1 = n1.next; // n1 -> mid
			n2 = n2.next.next; // n2 -> end
		}
		// n1 中点

		n2 = n1.next; // n2 -> right part first node
		n1.next = null; // mid.next -> null
		Node n3 = null;
		while (n2 != null) { // right part convert
			n3 = n2.next; // n3 -> save next node
			n2.next = n1; // next of right node convert
			n1 = n2; // n1 move
			n2 = n3; // n2 move
		}
		n3 = n1; // n3 -> save last node
		n2 = head;// n2 -> left first node
		boolean res = true;
		while (n1 != null && n2 != null) { // check palindrome
			if (n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next; // left to mid
			n2 = n2.next; // right to mid
		}
		n1 = n3.next;
		n3.next = null;
		while (n1 != null) { // recover list
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}

	// 测试
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

	// 测试2026
	// 使用Stack
	public static boolean isPalindromeA(Node head) {
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

	public static boolean isPalindromeB(Node head) {
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

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindromeA(head) + " | ");
		System.out.println(isPalindromeB(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}

}
