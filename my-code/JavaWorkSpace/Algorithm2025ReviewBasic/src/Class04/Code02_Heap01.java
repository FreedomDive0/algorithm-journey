package class04;

public class Code02_Heap01 {
	// 1.大根堆
	public static class MyHeap {
		private int[] heap;
		private int size;
		private int limit;

		public MyHeap(int limit) {
			this.limit = limit;
			heap = new int[limit];
			size = 0;
		}

		public void push(int num) {
			if (isFull()) {
				throw new RuntimeException("Heap is Full");
			}
			heap[size] = num;
			heapInsert(size++);
		}

		public int pop() {
			if (isEmpty()) {
				throw new RuntimeException("Heap is Empty");
			}
			int ans = heap[0];
			swap(heap, 0, --size);
			heapify(0, size);
			return ans;
		}

		public void heapInsert(int idx) {
			while (heap[idx] > heap[(idx - 1) / 2]) {
				swap(heap, idx, (idx - 1) / 2);
				idx = (idx - 1) / 2;
			}
		}

		public void heapify(int idx, int heapSize) {
			int left = idx * 2 + 1;
			while (left < heapSize) {
				int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
				largest = heap[largest] > heap[idx] ? largest : idx;
				if (largest == idx) {
					break;
				}
				swap(heap, idx, largest);
				idx = largest;
				left = idx * 2 + 1;
			}
		}

		public void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}

		public boolean isFull() {
			return size == limit;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}
}
