package class01;

import java.util.Arrays;

import SortTools.SortTools;

public class selectionSort {
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100; // 随机数组的长度0～100
		int maxValue = 100;// 值：-100～100
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = SortTools.generateRandomArray(maxSize, maxValue);
			int[] arr2 = SortTools.copyArray(arr1);
			selectionSort(arr1);
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

	public static void selectSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {// 找最小
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[minIndex] > arr[j] ? j : minIndex;
			}
			SortTools.swap(arr, i, minIndex);
		}
	}

	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIdx = arr[j] < arr[minIdx] ? j : minIdx;
			}
			SortTools.swap(arr, i, minIdx);
		}
	}

	// 选择排序
//	public static void selectSort(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		int index = -1;
//		for (int i = 0; i < arr.length; i++) {
//			index = arr.length - 1;
//			for (int j = arr.length - 1; j >= i; j--) {
//				if (arr[index] > arr[j]) {
//					index = j;
//				}
//			}
//			int tmp = arr[i];
//			arr[i] = arr[index];
//			arr[index] = tmp;
//		}
//	}
	// 测试
	public static void SelectSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[minIndex] > arr[j] ? j : minIndex;
			}
			Swap(arr, i, minIndex);
		}
	}

	public static void Swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
