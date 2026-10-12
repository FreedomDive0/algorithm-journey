package class03;

import java.util.Arrays;

import SortTools.SortTools;

public class MergeSort {
	// 递归调用
	public static void MergeSort(int arr[]) {
		if (arr == null || arr.length < 2) {// 整体数组条件
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int arr[], int L, int R) {
		if (L == R) {// 最小基本划分 base case
			return;
		}
		int mid = L + ((R - L) >> 1);// 位运算需要括起来
		process(arr, L, mid);
		process(arr, mid + 1, R);
		merge(arr, L, mid, R);
	}

	public static void merge(int arr[], int L, int M, int R) {
		int help[] = new int[R - L + 1];
		int i = 0;// help下标
		int p1 = L;// 左数组下标
		int p2 = M + 1;
		while (p1 <= M && p2 <= R) {// 左右不越界
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {// 将help导入原数组
			arr[L + i] = help[i];
		}
	}
	// 复习
//	public static void MergeSort(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		process(arr, 0, arr.length - 1);
//	}
//
//	public static void process(int[] arr, int l, int r) {
//		if (l == r) {
//			return;
//		}
//		int m = l + ((r - l) >> 1);
//		process(arr, l, m);
//		process(arr, m + 1, r);
//		merge(arr, l, m, r);
//	}
//
//	public static void merge(int[] arr, int l, int m, int r) {
//		int[] help = new int[r - l + 1];
//		int index = 0;
//		int lIndex = l;
//		int rIndex = m + 1;
//		while (lIndex <= m && rIndex <= r) {
//			if (arr[lIndex] < arr[rIndex]) {
//				help[index++] = arr[lIndex++];
//			} else {
//				help[index++] = arr[rIndex++];
//			}
//		}
//		while (lIndex <= m) {
//			help[index++] = arr[lIndex++];
//		}
//		while (rIndex <= r) {
//			help[index++] = arr[rIndex++];
//		}
//		for (int i = 0; i < index; i++) {
//			arr[l++] = help[i];
//		}
//	}

	// 非递归
	public static void MergeSort2(int arr[]) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int mergeSize = 1;// 左划分大小
		int N = arr.length;
		while (mergeSize < N) {// mergeSize执行最后一次时应>=N/2
			int L = 0;
			while (L < N) {// 左标不越界就继续
				int M = L + mergeSize - 1;
				if (M >= N) {// 中点越界不用继续
					break;
				}
				int R = Math.min(M + mergeSize, N - 1);
				merge(arr, L, M, R);
				L = R + 1;
			}
			if (mergeSize > N / 2) {// mergeSize>N/2排完后即为答案 防止int溢出
				break;
			}
			mergeSize <<= 1;
		}
	}

	// 应用1 数组小和
	public static int smallSum(int arr[]) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process1(arr, 0, arr.length - 1);
	}

	private static int process1(int arr[], int L, int R) {
		if (L == R) {
			return 0;
		}
		int mid = L + ((R - L) >> 1);
		return process1(arr, L, mid) + process1(arr, mid + 1, R) + merge1(arr, L, mid, R);
	}

	public static int merge1(int arr[], int L, int M, int R) {
		int help[] = new int[R - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = M + 1;
		int res = 0;
		while (p1 <= M && p2 <= R) {
//			res += arr[p1]<arr[p2]?(R-p2+1)*arr[p1]:0;//数组小和 
			res += arr[p1] > arr[p2] ? (M - p1 + 1) : 0;// 左边有多少个数比右边大
//			help[i++] = arr[p1] < arr[p2] ? arr[p1++]:arr[p2++];//数组小和
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];// 逆序对数 注意左右标移动条件
		}
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {// 直接小于help长度
			arr[L + i] = help[i];
		}
		return res;
	}

	// 对数器
	public static int smallSumV(int arr[]) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					res += arr[i];
				}
			}
		}
		return res;
	}

//	应用2 逆序对数
	public static int inverse(int arr[]) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process1(arr, 0, arr.length - 1);
	}

	// 对数器
	public static int inverseV(int arr[]) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					res++;
				}
			}
		}
		return res;
	}

	// 测试
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int mergeSize = 1;
		int N = arr.length;
		while (mergeSize < N) {
			int L = 0;
			while (L < N) {
				int M = L + mergeSize - 1;
				if (M >= N) {
					break;
				}
				int R = Math.min(M + mergeSize, N - 1);
				merge2(arr, L, M, R);
				L = R + 1;
			}
			if (mergeSize > N / 2) {
				break;
			}
			mergeSize <<= 1;
		}
	}

	public static void merge2(int[] arr, int l, int mid, int r) {
		int[] help = new int[r - l + 1];
		int p1 = l;
		int p2 = mid + 1;
		int index = 0;
		while (p1 <= mid && p2 <= r) {
			help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {
			help[index++] = arr[p1++];
		}
		while (p2 <= r) {
			help[index++] = arr[p2++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

	public static void main(String[] args) {
//		int arr[] = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		MerSort(arr);
//		MergeSort2(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = SortTools.generateRandomArray(maxSize, maxValue);
			int[] arr2 = SortTools.copyArray(arr1);
//			if(inverse(arr1)!=inverseV(arr2)) {
//				succeed = false;
//			}
			Arrays.sort(arr1);
			mergeSort(arr2);
			if (!SortTools.isEqual(arr1, arr2)) {
				succeed = false;
				SortTools.printArray(arr1);
				SortTools.printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
