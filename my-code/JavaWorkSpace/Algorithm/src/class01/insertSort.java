package class01;

import java.util.Arrays;

import SortTools.SortTools;

public class insertSort {
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100; // 随机数组的长度0～100
		int maxValue = 100;// 值：-100～100
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = SortTools.generateRandomArray(maxSize, maxValue);
			int[] arr2 = SortTools.copyArray(arr1);
			insertSort1(arr1);
			Arrays.sort(arr2);
			if (!SortTools.isEqual(arr1, arr2)) {
				// 打印arr1
				// 打印arr2
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {//
				SortTools.swap(arr, j, j + 1);
			}
		}
	}

	// 插入排序
//	public static void insertionSort(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = i; j > 0 ; j--) {
//				if (arr[j] < arr[j - 1]) {
//					int tmp = arr[j];
//					arr[j] = arr[j - 1];
//					arr[j - 1] = tmp;
//				}
//			}
//		}
//	}
	// 测试
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
				Swap(arr, j, j + 1);
			}
		}
	}

	public static void insertSort1(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
				Swap(arr, j + 1, j);
			}
		}
	}

	public static void Swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
