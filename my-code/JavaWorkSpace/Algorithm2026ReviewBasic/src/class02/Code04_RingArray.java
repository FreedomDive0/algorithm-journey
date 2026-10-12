package class02;

public class Code04_RingArray {
	// 循环数组
	public static class MyQueue {
		public int pushIdx;
		public int pollIdx;
		public int size;
		private int limit;
		private int[] arr;

		public MyQueue(int limit) {
			pushIdx = 0;
			pollIdx = 0;
			size = 0;
			this.limit = limit;
			if (limit <= 0)
				throw new IllegalArgumentException("limit must be > 0");
			arr = new int[limit];
		}

		public void push(int value) {
			if (isFull())
				throw new RuntimeException("Queue is full");
			arr[pushIdx] = value;
			size++;
			pushIdx = getNextIdx(pushIdx);
		}

		public int poll() {
			if (isEmpty())
				throw new RuntimeException("Queue is empty");
			int res = arr[pollIdx];
			size--;
			pollIdx = getNextIdx(pollIdx);
			return res;
		}

		public int getNextIdx(int idx) {
			return idx == limit - 1 ? 0 : idx + 1;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

	}

	public static class MyStack {
		public int[] arr;
		public int limit;
		public int topIdx;

		public MyStack(int limit) {
			if (limit <= 0)
				throw new IllegalArgumentException("limit must be > 0");
			this.limit = limit;
			arr = new int[limit];
			topIdx = -1;
		}

		public void push(int value) {
			if (isFull())
				throw new RuntimeException("Stack is full");
			arr[++topIdx] = value;
		}

		public int pop() {
			if (isEmpty())
				throw new RuntimeException("Stack is empty");
			int ans = arr[topIdx--];
			return ans;
		}

		public boolean isFull() {
			return topIdx == limit - 1;
		}

		public boolean isEmpty() {
			return topIdx == -1;
		}
	}
}
