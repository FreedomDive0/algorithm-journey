package class04;

import java.util.Arrays;

import SortTools.SortTools;

public class heapSort {
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
//			heapInsert(heap, heapSize++);
//		}
//
//		public void heapInsert(int[] heap, int index) {
//			while (heap[index] > heap[(index - 1) / 2]) {
//				SortTools.swap(heap, index, (index - 1) / 2);
//				index = (index - 1) / 2;
//			}
//		}
//
//		public int pop() {
//			int value = heap[0];
//			SortTools.swap(heap, 0, --heapSize);
//			heapify(heap, 0, heapSize);
//			return value;
//		}
//
//		public void heapify(int[] heap, int index, int heapSize) {
//			int leftSon = index * 2 + 1;
//			while (leftSon < heapSize) {
//				int largest = leftSon + 1 < heapSize && heap[leftSon] < heap[leftSon + 1] ? leftSon + 1 : leftSon;
//				largest = heap[largest] > heap[index] ? largest : index;
//				if (largest == index) {
//					break;
//				}
//				SortTools.swap(heap, index, largest);
//				index = largest;
//				leftSon = index * 2 + 1;// 记得更新leftSon
//			}
//		}
//	}
//
//	public static void heapSort(int arr[]) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		Myheap heap = new Myheap(arr.length);
//		int heapSize = arr.length;
////		for (int index = 0; index < arr.length; index++) {
////			heap.heapInsert(arr, index);// 每个位置重新构成大根堆 由上至下构成大根堆 与整体层数有关
////		}
//		for (int index = arr.length - 1; index >= 0; index--) {
//			heap.heapify(arr, index, arr.length);// 由下至上构成大根堆 调整为常数 即所在层数 arr.length为边界值
//		}
//		SortTools.swap(arr, 0, --heapSize);
//		while (heapSize > 0) {
//			heap.heapify(arr, 0, heapSize);// 交换重排 构成新大根堆 每次将最大值后移
//			SortTools.swap(arr, 0, --heapSize);
//		}
//	}

// 复习
// 堆排序
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
			heapInsert(values, size++);
		}

//			public void heapInsert() {
//				int index = size - 1;
//				int parent = (index - 1) / 2;
//				while (parent >= 0) {
//					if (values[index] <= values[parent]) {
//						break;
//					}
//					swap(values, index, parent);
//					index = parent;
//					parent = (index - 1) / 2;
//				}
//			}
		// 优化
		public void heapInsert(int[] values, int index) {
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
			heapify(values, 0, size);
			return values[size];
		}

		public void heapify(int[] values, int index, int size) {
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

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		Myheap heap = new Myheap(arr.length);
		for (int i = 0; i < arr.length; i++) {
			heap.heapInsert(arr, i);// 从上向下建堆
		}
//		for (int i = arr.length - 1; i >= 0; i--) {
//			heap.heapify(arr, i, arr.length);
//		}
		int size = arr.length;
		swap(arr, 0, --size);
		while (size > 0) {
			heap.heapify(arr, 0, size);
			swap(arr, 0, --size);
		}
	}

	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = SortTools.generateRandomArray(maxSize, maxValue);
			int[] arr2 = SortTools.copyArray(arr1);
			heapSort(arr1);
			Arrays.sort(arr2);
			if (!SortTools.isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
