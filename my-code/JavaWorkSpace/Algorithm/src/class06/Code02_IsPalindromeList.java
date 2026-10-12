package class06;

import java.util.Stack;

public class Code02_IsPalindromeList {
	public static class Node{
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
			next = null;
		}
	}
//	额外空间复杂度O(N)
	public static boolean isPalindrome1(Node head) {
		if(head==null||head.next==null) {
			return true;
	}
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
//		进栈
		while(cur!=null) {
			stack.add(cur);
			cur = cur.next;
		}
//		比较
		cur = head;
		while(cur!=null) {
			if(cur.value != stack.pop().value) {
				return false;
			}
			cur = cur.next;
		}
		return true;
	}
	
//	额外空间复杂度O(n/2)
	public static boolean isPalindrome2(Node head) {
		if(head==null||head.next==null) {
			return true;
	}
		Node head1 = head;
		Node head2 = head;
//		使用快慢指针跳到中点末尾
		while(head2.next!=null&&head2.next.next!=null) {
			head1 = head1.next;
			head2 = head2.next.next;
		}
		Node cur = head1.next;
		Stack<Node> stack = new Stack<Node>();
		while(cur!=null) {
			stack.add(cur);
			cur = cur.next;
		}
		cur = head;
		while(!stack.isEmpty()) {
			if(stack.pop().value!=cur.value) {
				return false;
			}
		}
		return true;
	}
	
//	额外空间复杂度O(1)
	public static boolean isPalindrome3(Node head) {
		if(head==null||head.next==null) {
			return true;
	}
		Node head1 = head;
		Node head2 = head;
//		使用快慢指针跳到中点末尾
		while(head2.next!=null&&head2.next.next!=null) {
			head1 = head1.next;
			head2 = head2.next.next;
		}
//		从中点(上中点)下一个开始反转链表
		Node cur = head1.next;
		head1.next = null;
		while(cur!=null) {
			Node next = cur.next;
			cur.next = head1;
			head1 = cur;
			cur = next;
		}
//		反转后对比元素
		cur = head;
		Node cur2 = head2;
		while(cur!=null&&cur2!=null) {
			if(cur.value!=cur2.value) {
				return false;
			}
			cur = cur.next;
			cur2 = cur2.next;
		}
//		恢复链表
		cur = head2.next;
		head2.next = null;
		while(cur!=null) {
			Node next = cur.next;
			cur.next = head2;
			head2 = cur;
			cur = cur.next;
		}
		return true;
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
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}
}
