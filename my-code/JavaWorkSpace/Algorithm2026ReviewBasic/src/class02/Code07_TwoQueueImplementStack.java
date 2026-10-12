package class02;

import java.util.LinkedList;
import java.util.Queue;

public class Code07_TwoQueueImplementStack {
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
}
