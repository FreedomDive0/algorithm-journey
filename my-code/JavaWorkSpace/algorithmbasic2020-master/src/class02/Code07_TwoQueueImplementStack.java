package class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack {

	public static class TwoQueueStack<T> {
		public Queue<T> queue;
		public Queue<T> help;

		public TwoQueueStack() {
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}

		public void push(T value) {
			queue.offer(value);
		}

		public T poll() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			T ans = queue.poll();
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public T peek() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			T ans = queue.poll();
			help.offer(ans);
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public boolean isEmpty() {
			return queue.isEmpty();
		}

	}

	// 2026复习
	public static class TwoQueueStack1<T> {
		Queue<T> data;
		Queue<T> help;

		public TwoQueueStack1() {
			data = new LinkedList<T>();
			help = new LinkedList<T>();
		}

		public void push(T value) {
			data.offer(value);
		}

		public T pop() {
			if (data.isEmpty())
				throw new RuntimeException("Queue is empty");
			while (data.size() > 1) {
				help.offer(data.poll());
			}

			Queue<T> tmp = data;
			data = help;
			help = tmp;
			return help.poll();
		}

		public T peek() {
			if (data.isEmpty())
				throw new RuntimeException("Queue is empty");
			while (data.size() > 1) {
				help.offer(data.poll());
			}

			Queue<T> tmp = data;
			data = help;
			help = tmp;
			T ans = help.poll();
			data.offer(ans);
			return ans;
		}

		public boolean isEmpty() {
			return data.isEmpty();
		}
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		TwoQueueStack1<Integer> myStack = new TwoQueueStack1<Integer>();
		Stack<Integer> test = new Stack<>();
		int testTime = 1000000;
		int max = 1000000;
		for (int i = 0; i < testTime; i++) {
			if (myStack.isEmpty()) {
				if (!test.isEmpty()) {
					System.out.println("Oops");
				}
				int num = (int) (Math.random() * max);
				myStack.push(num);
				test.push(num);
			} else {
				if (Math.random() < 0.25) {
					int num = (int) (Math.random() * max);
					myStack.push(num);
					test.push(num);
				} else if (Math.random() < 0.5) {
					if (!myStack.peek().equals(test.peek())) {
						System.out.println("Oops");
					}
				} else if (Math.random() < 0.75) {
					if (!myStack.pop().equals(test.pop())) {
						System.out.println("Oops");
					}
				} else {
					if (myStack.isEmpty() != test.isEmpty()) {
						System.out.println("Oops");
					}
				}
			}
		}

		System.out.println("test finish!");

	}

}
