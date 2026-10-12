package class04;

public class Code04_HeapSort {
	// 堆排序 O(NlogN) O(1)
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		int size = arr.length;
		// 建立大根堆
		// 自顶向下建堆 O(NlogN)
		for (int i = 0; i < size; i++) {
			heapInsert(arr, i);
		}
		// 自底向上建堆 O(N)
		for (int i = size - 1; i >= 0; i--) {
			heapify(arr, i, size);
		}
		// 调整
		for (int i = size - 1; i > 0; i--) {
			swap(arr, 0, i);
			heapify(arr, 0, i);
		}
	}

	public static void heapInsert(int[] arr, int idx) {
		while (arr[idx] > arr[(idx - 1) / 2]) {
			swap(arr, idx, (idx - 1) / 2);
			idx = (idx - 1) / 2;
		}
	}

	public static void heapify(int[] arr, int idx, int size) {
		int leftSon = idx * 2 + 1;
		while (leftSon < size) {
			int largest = leftSon + 1 < size && arr[leftSon + 1] > arr[leftSon] ? leftSon + 1 : leftSon;
			largest = arr[largest] > arr[idx] ? largest : idx;
			if (largest == idx)
				break;
			swap(arr, idx, largest);
			idx = largest;
			leftSon = idx * 2 + 1;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (i == j)
			return;
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}
}
