package class04;

public class Code04_HeapSort {
	public static void HeapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 自顶向下建堆 O(NlogN)
		for (int i = 0; i < arr.length; i++) {// O(N)
			heapInsert1(arr, i);// O(logN)
		}
		// 自底向上建堆 O(N)
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify1(arr, i, arr.length);
		}
		int size = arr.length;
		while (size > 0) {
			swap(arr, 0, --size);
			heapify1(arr, 0, size);
		}
	}

	public static void heapInsert1(int[] heap, int idx) {
		while (heap[idx] > heap[(idx - 1) / 2]) {
			swap(heap, idx, (idx - 1) / 2);
			idx = (idx - 1) / 2;
		}
	}

	public static void heapify1(int[] heap, int idx, int heapSize) {
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

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
