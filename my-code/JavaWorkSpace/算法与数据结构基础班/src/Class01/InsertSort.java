package Class01;

import Tools.Tools;

public class InsertSort {
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
				Tools.swap(arr, j + 1, j);
			}
		}
	}
}
