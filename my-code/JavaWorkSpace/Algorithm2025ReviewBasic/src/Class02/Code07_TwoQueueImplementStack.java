package class02;

import java.util.LinkedList;
import java.util.Queue;

public class Code07_TwoQueueImplementStack {
	// 两个队列实现栈
	public static class TwoQueueStack<T> {
		public Queue<T> data;
		public Queue<T> help;

		public TwoQueueStack() {
			data = new LinkedList<T>();
			help = new LinkedList<T>();
		}

		public void push(T value) {
			data.offer(value);
		}

		public T pop() {
			if (data.isEmpty()) {
				throw new RuntimeException("queue is empty");
			}
			while (data.size() > 1) {
				help.offer(data.poll());
			}
			T res = data.poll();
			Queue<T> tmp = help;
			help = data;
			data = tmp;
			return res;
		}

		public T peek() {
			if (data.isEmpty()) {
				throw new RuntimeException("queue is empty");
			}
			while (data.size() > 1) {
				help.offer(data.poll());
			}
			T res = data.poll();
			Queue<T> tmp = help;
			help = data;
			data = tmp;
			data.offer(res);
			return res;
		}

		public boolean isEmpty() {
			return data.isEmpty();
		}
	}
}
