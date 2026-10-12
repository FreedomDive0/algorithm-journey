package class02;

public class Code04_RingArray {
	// 循环队列(数组)
	public static class RingArray {
		public int[] arr;
		public int pushIdx;
		public int pollIdx;
		public int count;
		public int limit;

		public RingArray(int limit) {
			arr = new int[limit];
			this.limit = limit;
			pushIdx = 0;
			pollIdx = 0;
			count = 0;
		}

		public void add(int value) {
			if (count >= limit) {
				throw new RuntimeException("queue is full");
			}
			arr[pushIdx] = value;
			pushIdx = getNextId(pushIdx);
			count++;
		}

		public int delete() {
			if (count == 0) {
				throw new RuntimeException("queue is empty");
			}
			int res = arr[pollIdx];
			pollIdx = getNextId(pollIdx);
			count--;
			return res;
		}

		public boolean isFull() {
			return count == limit;
		}

		public boolean isEmpty() {
			return count == 0;
		}

		private int getNextId(int id) {
			return id < limit - 1 ? id + 1 : 0;
		}
	}

}
