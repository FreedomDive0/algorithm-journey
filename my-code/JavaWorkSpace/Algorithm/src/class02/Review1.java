package class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Review1 {
	public static class Node<T> {
		Node next;
		T value;

		public Node(T value) {
			this.next = null;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	public static class DoubleNode<T> {
		DoubleNode last;
		DoubleNode next;
		T value;

		public DoubleNode(T value) {
			this.last = null;
			this.next = null;
			this.value = value;
		}

		@Override
		public String toString() {
			return "DoubleNode [value=" + value + "]";
		}

	}

	// 从头加入
	public static DoubleNode addFromHead(DoubleNode head, DoubleNode e) {
		if (head == null) {
			return e;
		}
		e.next = head;
		head.last = e;
		return e;
	}

	// 从尾加入
	public static DoubleNode addFormTail(DoubleNode head, DoubleNode e) {
		if (head == null) {
			return e;
		}
		DoubleNode tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = e;
		e.last = tmp;
		return head;
	}

	// 从头删除
	public static DoubleNode deleFromHead(DoubleNode head) {
		if (head == null) {
			return head;
		}
		DoubleNode next = head.next;
		next.last = head.last;
		head.next = null;
		return next;
	}

	// 从尾删除
	public static DoubleNode deleFromTail(DoubleNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		DoubleNode tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.last.next = null;
		tmp.last = null;
		return head;
	}

	// Q1 单链表反转
	public static Node reverse(Node head) {
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

	// Q1 双链表反转
	public static DoubleNode reverseD(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}

	// Q2 删除给定值（单链表）
	public static Node<Integer> deleteE(Node<Integer> head, Integer e) {
		while (head != null && head.value == e) {
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == e) {
				pre.next = cur.next;
				break;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

	// Q3 队列和栈实现
	public static class MyStack<T> {
		DoubleNode<T> top;

		public MyStack(T t) {
			top = new DoubleNode(t);
		}

		public DoubleNode<T> push(DoubleNode e) {
			return addFromHead(top, e);
		}

		public T pop() {
			DoubleNode<T> e = deleFromHead(top);
			return e.value;
		}

		public boolean isEmpty() {
			return top == null;
		}

		public T peek() {
			if (isEmpty()) {
				return null;
			}
			return top.value;
		}
	}

	public static class MyQueue<T> {
		DoubleNode queue;

		public MyQueue(T t) {
			this.queue = new DoubleNode(t);
		}

		public void push(DoubleNode e) {
			addFromHead(queue, e);
		}

		public T pop() {
			if (isEmpty()) {
				return null;
			}
			DoubleNode<T> e = deleFromTail(queue);
			return e.value;
		}

		public boolean isEmpty() {
			return queue == null;
		}
	}

	// Q4 队列实现栈
	public static class QueueToStack {
		Queue<Integer> data = new LinkedList<Integer>();
		Queue<Integer> help = new LinkedList<Integer>();

		public void push(Integer e) {
			// 加入每次直接加入data队列
			data.add(e);
		}

		public Integer pop() {
			// 弹出每次将data队列清空 最后一个元素为输出元素
			// data队列与help互换身份
			if (data.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			while (data.size() > 1) {
				help.add(data.poll());
			}
			Integer res = data.poll();
			Queue<Integer> tmp = new LinkedList<Integer>();
			tmp = data;
			data = help;
			help = tmp;
			return res;
		}

		public boolean isEmpty() {
			return data.isEmpty();
		}
	}

	// Q5 栈实现队列
	public static class StackToQueue {
		Stack<Integer> push = new Stack<Integer>();
		Stack<Integer> pop = new Stack<Integer>();

		public void push(int e) {
			adjustStack();
			push.add(e);
		}

		public Integer pop() {
			if (push.isEmpty() && pop.isEmpty()) {
				throw new RuntimeException("队列为空");
			}
			adjustStack();
			return pop.pop();
		}

		// 调整push和pop栈
		// pop栈为空 push将全部元素导入pop
		public void adjustStack() {
			if (pop.isEmpty()) {
				while (!push.isEmpty()) {
					pop.add(push.pop());
				}
			}
		}

		public boolean isEmpty() {
			return pop.isEmpty() && push.isEmpty();
		}
	}

	// 测试
	public static void main(String[] args) {
		StackToQueue stack = new StackToQueue();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	// 递归 Recursion
	// 将大问题分解成同类型的小问题 利用计算机特性(系统栈) 不断调用自身方法 以解决问题
	public static int findMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	public static int process(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		int mid = l + ((r - l) >> 1);
		int leftMax = process(arr, l, mid);
		int rightMax = process(arr, mid + 1, r);
		return Math.max(leftMax, rightMax);
	}
}
