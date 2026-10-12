package class04;

public class Code02_Heap01 {
	// 1.大根堆
	public static class MyHeap {
		public int[] arr;
		public int size;
		public int limit;

		public MyHeap(int limit) {
			this.limit = limit + 1;
			arr = new int[this.limit];
			size = 0;
		}

		public void push(int value) {
			if (isFull())
				throw new RuntimeException("Heap is Full");
			arr[++size] = value;
			heapInsert(size);
		}

		public void heapInsert(int idx) {
			while (idx > 1 && arr[idx] > arr[idx >> 1]) {
				swap(arr, idx, idx >> 1);
				idx >>= 1;
			}
		}

		public int pop() {
			if (isEmpty())
				throw new RuntimeException("Heap is Empty");
			swap(arr, 1, size--);
			heapify(1);
			return arr[size + 1];
		}

		public void heapify(int idx) {
			while ((idx << 1) <= size) {
				int leftSon = idx << 1;
				int largest = leftSon + 1 <= size && arr[leftSon + 1] > arr[leftSon] ? leftSon + 1 : leftSon;
				largest = arr[largest] > arr[idx] ? largest : idx;
				if (largest == idx)
					break;
				swap(arr, idx, largest);
				idx = largest;
			}
		}

		public void swap(int[] arr, int i, int j) {
			if (i == j)
				return;
			arr[i] ^= arr[j];
			arr[j] ^= arr[i];
			arr[i] ^= arr[j];
		}

		public boolean isFull() {
			return size == limit - 1;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}

	public static void main(String[] args) {
		int idx = 100;
		System.out.println(idx << 1);
		System.out.println(idx);
	}
}
