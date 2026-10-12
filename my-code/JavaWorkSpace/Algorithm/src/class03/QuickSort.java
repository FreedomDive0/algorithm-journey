package class03;

import java.util.Arrays;

import SortTools.SortTools;

public class QuickSort {
	// 分<=区间 >区间
	public static int partiton(int arr[], int L, int R) {
		if (L > R) {
			return -1;
		}
		if (L == R) {
			return L;
		}
		int index = L - 1;// <=区间结束下标
		int i = L;
		while (i < R) {
			if (arr[i] <= arr[R]) {// <=区间扩展的过程
				SortTools.swap(arr, i, ++index);
			}
			i++;// 不用写else都要i++
		}
		SortTools.swap(arr, ++index, R);
		return index;
	}

	// 分<区间 =区间 >区间
	public static int[] netherlandsFlag(int arr[], int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };// 注意{}不是数组
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1;// <区间左
		int more = R; // >区间右
		int i = L;// 数组下标
		while (i < more) {
			if (arr[i] < arr[R]) {
				SortTools.swap(arr, i++, ++less);
			} else if (arr[i] == arr[R]) {
				i++;
			} else {
				SortTools.swap(arr, i, --more);// 大于交换 i不变
			}
		}
		SortTools.swap(arr, R, more);// 最后交换
		return new int[] { less + 1, more };
	}

	// 快排1.0
	public static void quickSort1(int arr[]) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process1(arr, 0, arr.length - 1);
	}

	public static void process1(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		int M = partiton(arr, L, R);
		process1(arr, L, M - 1);
		process1(arr, M + 1, R);
	}

//	快排2.0
	public static void quickSort2(int arr[]) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process2(arr, 0, arr.length - 1);
	}

	public static void process2(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		int equalArea[] = netherlandsFlag(arr, L, R);
		process2(arr, L, equalArea[0] - 1);
		process2(arr, equalArea[1] + 1, R);
	}

//	快排3.0
	public static void quickSort3(int arr[]) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}

	public static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		SortTools.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);// 随机选取R位置的数
		int equalArea[] = netherlandsFlag(arr, L, R);
		process3(arr, L, equalArea[0] - 1);
		process3(arr, equalArea[1] + 1, R);
	}

	// 复习
	public static void QuickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int l, int r) {
		if (l >= r) {// 注意条件
			return;
		}
		int[] res = NetherLand(arr, l, r);
		process(arr, l, res[0]);
		process(arr, res[1], r);
	}

	public static int[] NetherLand(int[] arr, int l, int r) {
//		if (l > r) {
//			return new int[] { -1, -1 };
//		}
//		if (l == r) {
//			return new int[] { l, r };
//		}
		int less = l - 1;
		int more = r;
		int index = l;
		while (index < more) {
			if (arr[index] < arr[r]) {
				swap(arr, index++, ++less);
			} else if (arr[index] == arr[r]) {
				index++;
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, index, r);
		return new int[] { less, more + 1 };
	}

	public static void swap(int[] arr, int i, int j) {
		if (arr == null || i == j) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
//		int arr[] = { 1, 3, 4, 2, 5, 2 };
//		int arr1[] = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		quickSort3(arr1);
//		for (int i = 0; i < arr1.length; i++) {
//			System.out.print(arr1[i] + " ");
//		}
//		netherlandsFlag(arr);
//		quickSort2(arr1);
//		for (int i = 0; i < arr1.length; i++) {
//			System.out.print(arr1[i] + " ");
//		}
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = SortTools.generateRandomArray(maxSize, maxValue);
			int[] arr2 = SortTools.copyArray(arr1);
			QuickSort(arr1);
			Arrays.sort(arr2);
			if (!SortTools.isEqual(arr1, arr2)) {
				succeed = false;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
