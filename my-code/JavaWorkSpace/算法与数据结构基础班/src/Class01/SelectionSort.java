package Class01;

import java.util.Arrays;

import Tools.Tools;

public class SelectionSort {
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIdx = arr[j] < arr[minIdx] ? j : minIdx;
			}
			swap(arr, i, minIdx);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int testTimes = 500000;
		int maxValue = 100;
		int maxSize = 100;
		boolean succeed = true;
		for (int i = 0; i < testTimes; i++) {
			int[] arr = Tools.generateRandomArray(maxSize, maxValue);
			int[] copyArr = Tools.copyArr(arr);
			Arrays.sort(copyArr);
			selectionSort(arr);
			if (!Tools.isEqual(arr, copyArr)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked");
	}
}
