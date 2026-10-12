package class04;

public class Code02_Heap01 {

	public static class MyMaxHeap {
		private int[] heap;
		private final int limit;
		private int heapSize;

		public MyMaxHeap(int limit) {
			heap = new int[limit];
			this.limit = limit;
			heapSize = 0;
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == limit;
		}

		public void push(int value) {
			if (heapSize == limit) {
				throw new RuntimeException("heap is full");
			}
			heap[heapSize] = value;
			// value heapSize
			heapInsert(heap, heapSize++);
		}

		// 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
		// 剩下的数，依然保持大根堆组织
		public int pop() {
			int ans = heap[0];
			swap(heap, 0, --heapSize);
			heapify(heap, 0, heapSize);
			return ans;
		}

		private void heapInsert(int[] arr, int index) {
			// arr[index]
			// arr[index] 不比 arr[index父]大了 ， 停
			// index = 0;
			while (arr[index] > arr[(index - 1) / 2]) {
				swap(arr, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		// 从index位置，往下看，不断的下沉，
		// 停：我的孩子都不再比我大；已经没孩子了
		private void heapify(int[] arr, int index, int heapSize) {
			int left = index * 2 + 1;
			while (left < heapSize) {
				// 左右两个孩子中，谁大，谁把自己的下标给largest
				// 右 -> 1) 有右孩子 && 2）右孩子的值比左孩子大才行
				// 否则，左
				int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
				largest = arr[largest] > arr[index] ? largest : index;
				if (largest == index) {
					break;
				}
				swap(arr, largest, index);
				index = largest;
				left = index * 2 + 1;
			}
		}

		private void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}

	}

	public static class RightMaxHeap {
		private int[] arr;
		private final int limit;
		private int size;

		public RightMaxHeap(int limit) {
			arr = new int[limit];
			this.limit = limit;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("heap is full");
			}
			arr[size++] = value;
		}

		public int pop() {
			int maxIndex = 0;
			for (int i = 1; i < size; i++) {
				if (arr[i] > arr[maxIndex]) {
					maxIndex = i;
				}
			}
			int ans = arr[maxIndex];
			arr[maxIndex] = arr[--size];
			return ans;
		}

	}

	// 测试 2026
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
		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
//			MyMaxHeap my = new MyMaxHeap(curLimit);
			MyHeap my = new MyHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("isEmpty Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("isFull Oops!");
				}
				if (my.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) {
					int myPop = my.pop();
					int testPop = test.pop();
					if (myPop != testPop) {
						System.out.println("pop Oops!" + "myPop:" + myPop + " testPop" + testPop);
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						int myPop = my.pop();
						int testPop = test.pop();
						if (myPop != testPop) {
							System.out.println("pop Oops!" + "myPop:" + myPop + " testPop" + testPop);
						}
					}
				}
			}
		}
		System.out.println("finish!");

	}

}
