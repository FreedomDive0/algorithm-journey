package class04;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Review1 {
	// 1.堆结构
	public static class heap {
		int[] arr;
		int heapSize;
		final int limit;

		public heap(int limit) {
			this.limit = limit;
			this.arr = new int[limit];
			this.heapSize = 0;
		}

		// 添加
		public void push(int num) {
			if (heapSize == limit) {
				throw new RuntimeException("堆满了");
			}
			arr[heapSize] = num;
			heapInsert(arr, heapSize++);
		}

		// 自下而上调整 子>父向上调整
		public void heapInsert(int[] arr, int index) {
			while (arr[index] > arr[(index - 1) / 2]) {
				swap(arr, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		// 弹出
		public int pop() {
			if (heapSize == 0) {
				throw new RuntimeException("堆为空");
			}
			int res = arr[0];
			swap(arr, 0, --heapSize);
			heapify(arr, 0, heapSize);
			return res;
		}

		// 自上而下调整 父亲小于左右儿子 向大儿子下沉
		public void heapify(int[] arr, int index, int heapSize) {
			int leftIndex = 2 * index + 1;
			while (leftIndex < heapSize) {// 有孩子才能继续
				// 判断是否有右孩子 并且比较大小
				int LargestIndex = leftIndex + 1 < heapSize
						? arr[leftIndex] > arr[leftIndex + 1] ? leftIndex : leftIndex + 1
						: leftIndex;
				LargestIndex = arr[LargestIndex] > arr[index] ? LargestIndex : index;
				if (index == LargestIndex) {// 发现儿子不大于自己 终止循环
					break;
				}
				swap(arr, index, LargestIndex);
				index = LargestIndex;
				leftIndex = index * 2 + 1;
			}
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == limit;
		}

		public void swap(int[] arr, int a, int b) {
			int tmp = arr[a];
			arr[a] = arr[b];
			arr[b] = tmp;
		}
	}

	// 2.堆排序
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		int heapSize = arr.length;
		// 建立大根堆
		// 1.自上而下 每个位置heapInsert O(N*logN)
//		for (int i = 0; i < arr.length; i++) {
//			heapInsert(arr, i);
//		}
		// 2.自下而上 每个位置heapify
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify(arr, i, heapSize);
		}
		swap(arr, 0, --heapSize);
		while (heapSize > 0) {
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
		}
	}

	public static void heapInsert(int[] arr, int index) {
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	public static void heapify(int[] arr, int index, int heapSize) {
		int leftIndex = 2 * index + 1;
		while (leftIndex < heapSize) {// 有孩子才能继续
			// 判断是否有右孩子 并且比较大小
			int LargestIndex = leftIndex + 1 < heapSize
					? arr[leftIndex] > arr[leftIndex + 1] ? leftIndex : leftIndex + 1
					: leftIndex;
			LargestIndex = arr[LargestIndex] > arr[index] ? LargestIndex : index;
			if (index == LargestIndex) {// 发现儿子不大于自己 终止循环
				break;
			}
			swap(arr, index, LargestIndex);
			index = LargestIndex;
			leftIndex = index * 2 + 1;
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	// 3.数组基本有序 每个位置排好序后移动距离不会超过k
	public static void heapSort1(int[] arr, int k) {
		if (k == 0) {// 不需移动
			return;
		}
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int i = 0;
		for (; i < Math.min(arr.length, k + 1); i++) {// i~i距离为0 小跟堆为0~k k+1个
			heap.add(arr[i]);
		}
		int index = 0;
		while (i < arr.length) {
			arr[index++] = heap.poll();
			heap.add(arr[i++]);
		}
		while (!heap.isEmpty()) {
			arr[index++] = heap.poll();
		}
	}

	// 4.比较器
	public static class MyCom implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	public static void main(String[] args) {
		heap h = new heap(100);
		for (int i = 0; i < 8; i++) {
			h.push(i);
		}
		int[] arr = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
		heapSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println("------------------------------------------------");
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new MyCom());
		int[] arr1 = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
		for (int i = 0; i < arr1.length; i++) {
			heap.add(arr1[i]);
		}
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
	}
}
