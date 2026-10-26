package class004_practice;

public class SelectBubbleInsert {
	// 数组交换方法
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 选择排序
	public static void selectSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 0; i < arr.length; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIdx])
					minIdx = j;
			}
			swap(arr, i, minIdx);
		}
	}

	// 冒泡排序
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
			}
		}
	}

	// 插入排序
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
				swap(arr, j - 1, j);
			}
		}
	}
}
