package class02;

public class Code03_DoubleEndsQueueToStackAndQueue {
	public static class Node<T> {
		public T value;
		public Node<T> last;
		public Node<T> next;

		public Node(T data) {
			value = data;
		}
	}

	public static class DoubleEndsQueue<T> {
		public Node<T> head;
		public Node<T> tail;

		public void addFromHead(T value) {
			Node<T> cur = new Node<T>(value);
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				cur.next = head;
				head.last = cur;
				head = cur;
			}
		}

		public void addFromTail(T value) {
			Node<T> cur = new Node<T>(value);
			if (tail == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				cur.last = tail;
				tail = cur;
			}
		}

		public T popFromHead() {
			if (head == null) {
				return null;
			}
			T res = head.value;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				Node<T> next = head.next;
				next.last = null;
				head = next;
			}
			return res;
		}

		public T popFromTail() {
			if (tail == null) {
				return null;
			}
			T res = tail.value;
			if (tail == head) {
				head = null;
				tail = null;
			} else {
				Node<T> last = tail.last;
				last.next = null;
				tail = last;
			}
			return res;
		}
	}

	public static class MyStack<T> {
		DoubleEndsQueue<T> linkedList;

		public MyStack() {
			this.linkedList = new DoubleEndsQueue<T>();
		}

		public void push(T value) {
			linkedList.addFromHead(value);
		}

		public T pop() {
			return linkedList.popFromHead();
		}
	}

	public static class MyQueue<T> {
		DoubleEndsQueue<T> linkedList;

		public MyQueue() {
			this.linkedList = new DoubleEndsQueue<T>();
		}

		public void push(T value) {
			linkedList.addFromTail(value);
		}

		public T poll() {
			return linkedList.popFromHead();
		}
	}
}
