package class02;

import java.util.Stack;

public class Review {
	// 单链表
	public static class Node<T> {
		T value;
		Node next;

		public Node(T value, Node next) {
			this.value = value;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}

	// 双链表
	public static class DoubleNode<T> {
		T value;
		DoubleNode last;
		DoubleNode next;

		public DoubleNode(T value, DoubleNode last, DoubleNode next) {
			this.value = value;
			this.last = last;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	// 从前加入
	public DoubleNode AddFromHead(DoubleNode head, DoubleNode node) {
		if (head == null) {
			return node;
		}
		node.last = head.last;
		head.last = node;
		node.next = head;
		return node;
	}

	// 从后加入
	public DoubleNode AddFromLast(DoubleNode head, DoubleNode node) {
		if (head == null) {
			return node;
		}
		DoubleNode tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		node.next = tmp.next;
		node.last = tmp;
		tmp.next = node;
		return head;
	}

	// 从前删除
	public DoubleNode DeleFromHead(DoubleNode head) {
		if (head == null) {
			return head;
		}
		DoubleNode tmp = head.next;
		tmp.last = head.last;
		head.next = null;
		return tmp;
	}

	// 从后删除
	public DoubleNode DeleFromLast(DoubleNode head) {
		if (head == null) {
			return head;
		}
		DoubleNode tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.last.next = tmp.next;
		tmp.last = null;
		return head;
	}

	// 单链表逆转
	public Node Reverse(Node head) {
		Node next = null;
		Node pre = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;// head->null
	}

	// 双链表反转
	public DoubleNode ReverseD(DoubleNode head) {
		DoubleNode next = null;
		DoubleNode pre = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}

	// 删除指定值
	public static Node<Integer> deleGivenNum(Node<Integer> head, int num) {
		while (head != null) {// 去除含有num的头部
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node<Integer> cur = head.next;
		Node<Integer> pre = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

	// 数组实现
	public static class MyStack1 {
		int[] arr = new int[10];
		int top = 0;
		int limit = 10;

		public void push(int e) {
			if (top == limit) {
				throw new RuntimeException("栈溢出");
			}
			arr[top++] = e;
		}

		public int pop() {
			if (top == 0) {
				throw new RuntimeException("栈为空");
			}
			return arr[top--];
		}

		public boolean isEmpty() {
			return top == 0;
		}

		public boolean isFull() {
			return top == limit;
		}
	}

	public static class MyQueue1 {
		int[] arr = new int[10];
		int head = 0;
		int tail = 0;
		int size = 0;
		int limit = 10;

		public void push(int e) {
			if (size == limit) {
				throw new RuntimeException("队列满");
			}
			arr[head] = e;
			head = nextIndex(head);
		}

		public int pop() {
			if (size == 0) {
				throw new RuntimeException("队列为空");
			}
			int res = arr[tail];
			tail = nextIndex(tail);
			return res;
		}

		public int nextIndex(int index) {
			return index + 1 == limit ? 0 : index + 1;
		}
	}

	// Q4 实现O(1)得到最小值的栈
	public static class minStack {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> min = new Stack<>();

		public void push(Integer e) {
			if (min.peek() < e) {
				min.add(min.peek());
				stack.add(e);
			} else {
				min.add(e);
				stack.add(e);
			}
		}

		public Integer pop() {
			if (stack.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			min.pop();
			return stack.pop();
		}

		public Integer getMin() {
			if (stack.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			return min.peek();
		}
	}
}
