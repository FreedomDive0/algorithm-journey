package class02;

public class Code03_DoubleEndsQueueToStackAndQueue {
	// 双向链表实现栈和堆
	public static class DoubleNode<T> {
		T value;
		DoubleNode<T> last;
		DoubleNode<T> next;

		public DoubleNode(T value) {
			this.value = value;
		}

	}

	// 队列
	public static class DoubleEndsQueue<T> {
		public DoubleNode<T> head;
		public DoubleNode<T> tail;

		// 从头加入
		public void addFromHead(T value) {
			DoubleNode<T> cur = new DoubleNode<T>(value);
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				head.last = cur;
				cur.next = head;
				head = cur;
			}
		}

		// 从尾加入
		public void addFromTail(T value) {
			DoubleNode<T> cur = new DoubleNode<T>(value);
			if (tail == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				cur.last = tail;
				tail = cur;
			}
		}

		// 从头删除
		public T popFromHead() {
			if (head == null) {
				throw new RuntimeException("No Element");
			}
			T res = head.value;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				DoubleNode<T> next = head.next;
				next.last = null;
				head.next = null;
				head = next;
			}
			return res;
		}

		// 从尾删除
		public T popFromTail() {
			if (tail == null) {
				throw new RuntimeException("No Element");
			}
			T res = tail.value;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				DoubleNode<T> last = tail.last;
				last.next = null;
				tail.last = null;
				tail = last;
			}
			return res;
		}

		public boolean isEmpty() {
			return tail == null;
		}
	}

	// 双向列表实现栈
	public static class DEQStack<T> {
		public DoubleEndsQueue<T> stack;

		public DEQStack() {
			stack = new DoubleEndsQueue<T>();
		}

		public void push(T value) {
			stack.addFromHead(value);
		}

		public T pop() {
			return stack.popFromHead();
		}
	}

	// 双向列表实现队列
	public static class DEQQueue<T> {
		public DoubleEndsQueue<T> queue;

		public DEQQueue() {
			queue = new DoubleEndsQueue<T>();
		}

		public void push(T value) {
			queue.addFromTail(value);
		}

		public T poll() {
			return queue.popFromHead();
		}

	}
}
