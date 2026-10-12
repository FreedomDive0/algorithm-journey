package class01;

public class Code01_SelectionSort {
	// 选择排序
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 0; i < arr.length; i++) {
			int minIdx = i;
			for (int j = minIdx + 1; j < arr.length; j++) {
				minIdx = arr[minIdx] > arr[j] ? j : minIdx;
			}
			swap(arr, i, minIdx);
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
