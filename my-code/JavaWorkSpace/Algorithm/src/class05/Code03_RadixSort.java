package class05;

import java.util.Arrays;

public class Code03_RadixSort {
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = 0;// 基数排序 非负数十进制
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int digit = digitCount(max);// 得到位数
		int help[] = new int[arr.length];
		for (int d = 1; d <= digit; d++) {// 用两个数组取代 10个队列方法
			int count[] = new int[10];
			for (int i = 0; i < arr.length; i++) {// 1.确定d位的数字个有几个
				count[getDigit(arr[i], d)]++;
			}
			for (int j = 1; j < count.length; j++) {
				count[j] += count[j - 1];// 2.确定所在位数字个数边界
				// arr倒序遍历 为前面的数字腾位置
			}
			for (int i = arr.length - 1; i >= 0; i--) {// 3.进桶
				int digitNum = getDigit(arr[i], d);
				help[count[digitNum] - 1] = arr[i];
				count[digitNum]--;
			}
			for (int i = 0; i < help.length; i++) {// 出桶
				arr[i] = help[i];
			}
		}
	}

	public static int digitCount(int max) {
		int res = 0;
		while (max > 0) {
			max /= 10;
			res++;
		}
		return res;
	}

	public static int getDigit(int num, int digit) {
		return ((num / (int) Math.pow(10, digit - 1)) % 10);
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			radixSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		radixSort(arr);
		printArray(arr);

	}
}
