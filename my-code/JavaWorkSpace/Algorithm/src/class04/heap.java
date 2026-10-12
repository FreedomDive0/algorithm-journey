package class04;

import SortTools.SortTools;
import class03.QuickSort;

public class heap {
//	public static class Myheap {
//		private int limit;
//		private int heapSize;
//		private int heap[];
//
//		public Myheap(int limit) {
//			this.limit = limit;
//			heap = new int[limit];
//			heapSize = 0;
//		}
//
//		public boolean isEmpty() {
//			return heapSize == 0;
//		}
//
//		public boolean isFull() {
//			return heapSize == limit;
//		}
//
//		public void push(int value) {
//			if (isFull()) {
//				throw new RuntimeException("heap is full");
//			}
//			heap[heapSize] = value;
//			heapInsert(heapSize++);
//		}
//
//		public void heapInsert(int index) {// 向上比较
//			while (heap[index] > heap[(index - 1) / 2]) {
//				SortTools.swap(heap, index, (index - 1) / 2);
//				index = (index - 1) / 2;
//			}
//		}
//
//		public int pop() {
//			int value = heap[0];
//			SortTools.swap(heap, 0, --heapSize);
//			heapify(0, heapSize);
//			return value;
//		}
//
//		public void heapify(int index, int heapSize) {
//			int leftSon = index * 2 + 1;
//			while (leftSon < heapSize) {// 1.有孩子
//				int largest = leftSon + 1 < heapSize && heap[leftSon] < heap[leftSon + 1] ? leftSon + 1 : leftSon;
//				largest = heap[largest] > heap[index] ? largest : index;
//				if (largest == index) {// 2.比两个孩子大
//					break;
//				}
//				SortTools.swap(heap, index, largest);
//				index = largest;
//				leftSon = index * 2 + 1;// 记得更新leftSon
//			}
//		}
//	}
	// 复习
	static class Myheap {
		int limit;
		int size;
		int[] values;

		public Myheap(int limit) {
			this.limit = limit;
			this.size = 0;
			this.values = new int[limit];
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		// 插入
		public void push(int element) {
			if (isFull()) {
				throw new RuntimeException("堆满了");
			}
			values[size] = element;
			heapInsert(size++);
		}

//		public void heapInsert() {
//			int index = size - 1;
//			int parent = (index - 1) / 2;
//			while (parent >= 0) {
//				if (values[index] <= values[parent]) {
//					break;
//				}
//				swap(values, index, parent);
//				index = parent;
//				parent = (index - 1) / 2;
//			}
//		}
		// 优化
		public void heapInsert(int index) {
			while (values[index] > values[(index - 1) / 2]) {// ∵(0-1)/2==0所以数组不会越界
				swap(values, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		// 删除
		public int pop() {
			if (isEmpty()) {
				throw new RuntimeException("堆中没有数据");
			}
			swap(values, --size, 0);
			heapify();
			return values[size];
		}

		public void heapify() {
			int index = 0;
			int leftSon = index * 2 + 1;
			while (leftSon < size) {
				int maxIndex = leftSon + 1 < size ? values[leftSon] > values[leftSon + 1] ? leftSon : leftSon + 1
						: leftSon;
				maxIndex = values[maxIndex] > values[index] ? maxIndex : index;
				if (maxIndex == index) {
					return;
				}
				swap(values, index, maxIndex);
				index = maxIndex;
				leftSon = index * 2 + 1;
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (arr == null || i == j) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = SortTools.generateRandomArray(maxSize, maxValue);
			int[] arr2 = SortTools.copyArray(arr1);
			Myheap heap = new Myheap(arr1.length);
			for (int j = 0; j < arr1.length; j++) {
				heap.push(arr1[j]);
			}
			int j = arr1.length - 1;
			while (!heap.isEmpty()) {
				arr1[j--] = heap.pop();
			}
			QuickSort.quickSort3(arr2);
			if (!SortTools.isEqual(arr1, arr2)) {
				succeed = false;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
