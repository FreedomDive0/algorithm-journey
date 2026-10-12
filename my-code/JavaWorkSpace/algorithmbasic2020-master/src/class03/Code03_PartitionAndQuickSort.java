package class03;

public class Code03_PartitionAndQuickSort {

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static int partition(int[] arr, int L, int R) {
		if (L > R) {
			return -1;
		}
		if (L == R) {
			return L;
		}
		int lessEqual = L - 1;
		int index = L;
		while (index < R) {
			if (arr[index] <= arr[R]) {
				swap(arr, index, ++lessEqual);
			}
			index++;
		}
		swap(arr, ++lessEqual, R);
		return lessEqual;
	}

	// arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
	// <arr[R] ==arr[R] > arr[R]
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1; // < 区 右边界
		int more = R; // > 区 左边界
		int index = L;
		while (index < more) {
			if (arr[index] == arr[R]) {
				index++;
			} else if (arr[index] < arr[R]) {
				swap(arr, index++, ++less);
			} else { // >
				swap(arr, index, --more);
			}
		}
		swap(arr, more, R);
		return new int[] { less + 1, more };
	}

	public static void quickSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process1(arr, 0, arr.length - 1);
	}

	public static void process1(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
		int M = partition(arr, L, R);
		process1(arr, L, M - 1);
		process1(arr, M + 1, R);
	}

	public static void quickSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process2(arr, 0, arr.length - 1);
	}

	public static void process2(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		int[] equalArea = netherlandsFlag(arr, L, R);
		process2(arr, L, equalArea[0] - 1);
		process2(arr, equalArea[1] + 1, R);
	}

	public static void quickSort3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}

	public static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = netherlandsFlag(arr, L, R);
		process3(arr, L, equalArea[0] - 1);
		process3(arr, equalArea[1] + 1, R);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

	// 测试2026
	// 1.Partition 分为小于等于区和大于区
	public static int partition1(int[] arr, int l, int r) {
		if (l > r) {
			return -1;
		}
		if (l == r) {
			return l;
		}
		int lessEqual = l - 1;
		int idx = 0;
		while (idx < r) {
			if (arr[idx] <= arr[r])
				swap(arr, idx, ++lessEqual);
			idx++;
		}
		swap1(arr, ++lessEqual, r);
		return lessEqual;
	}

	public static void swap1(int[] arr, int i, int j) {
		if (i == j)
			return;
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}

	// 2. NetherLands 荷兰国旗问题
	public static int[] netherlands(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1;
		int more = R;
		int idx = L;
		while (idx < more) {
			if (arr[idx] < arr[R]) {
				swap(arr, idx++, ++less);
			} else if (arr[idx] > arr[R]) {
				swap(arr, idx, --more);
			} else {
				idx++;
			}
		}
		swap(arr, R, more);
		return new int[] { less + 1, more };
	}

	// 1. 快排1.0
	public static void quickSort11(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		qsProcess1(arr, 0, arr.length - 1);
	}

	public static void qsProcess1(int[] arr, int l, int r) {
		if (l >= r)
			return;
		int m = partition(arr, l, r);
		qsProcess1(arr, l, m - 1);
		qsProcess1(arr, m + 1, r);
	}

	// 3.2 快排2.0
	public static void quickSort22(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		qsProcess2(arr, 0, arr.length - 1);
	}

	public static void qsProcess2(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int[] equals = netherlands(arr, l, r);
		qsProcess1(arr, l, equals[0] - 1);
		qsProcess2(arr, equals[1] + 1, r);
	}

	// 3.3 快排3.0
	public static void quickSort33(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		qsProcess3(arr, 0, arr.length - 1);
	}

	public static void qsProcess3(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int randomIdx = l + (int) (Math.random() * (r - l + 1));
		swap(arr, r, randomIdx);
		int[] equals = netherlands(arr, l, r);
		qsProcess3(arr, l, equals[0] - 1);
		qsProcess3(arr, equals[1] + 1, r);
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			quickSort11(arr1);
			quickSort22(arr2);
			quickSort33(arr3);
			if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Oops!");

	}

}
