package class03;

public class Code03_PartitionAndQuickSort {
	// 1.Partition 分为小于等于区和大于区
	public static int partition(int[] arr, int l, int r) {
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
		swap(arr, ++lessEqual, r);
		return lessEqual;
	}

	public static void swap(int[] arr, int i, int j) {
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

	// 3.1 快排1.0
	public static void quickSort1(int[] arr) {
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
	public static void quickSort2(int[] arr) {
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
	public static void quickSort3(int[] arr) {
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
}
