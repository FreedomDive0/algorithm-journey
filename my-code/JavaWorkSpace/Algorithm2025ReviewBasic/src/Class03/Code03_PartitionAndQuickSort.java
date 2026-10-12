package class03;

public class Code03_PartitionAndQuickSort {
	// 1.Partition 分为<= 和 >区
	public static int partition(int[] arr, int l, int r) {
		if (l > r) {
			return -1;
		}

		int smallEqual = l - 1;
		while (l < r) {
			if (arr[l] <= arr[r]) {
				swap(arr, ++smallEqual, l);
			}
			l++;
		}
		swap(arr, r, ++smallEqual);
		return smallEqual;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 2.NetherLands 荷兰国旗
	public static int[] netherLands(int[] arr, int L, int R) {
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
				swap(arr, --more, idx);
			} else {
				idx++;
			}
		}
		swap(arr, more, R);
		return new int[] { less + 1, more };
	}

	// 3.1 QuickSort1.0 Partition 每次确定一个位置
	public static void quickSort11(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process11(arr, 0, arr.length - 1);
	}

	public static void process11(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int m = partition(arr, l, r);
		process11(arr, l, m - 1);
		process11(arr, m + 1, r);
	}

	// 3.2 QuickSort2.0 NetherLands 每次确定一个区间
	public static void quickSort22(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process22(arr, 0, arr.length - 1);
	}

	public static void process22(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int[] equal = netherLands(arr, l, r);
		process22(arr, l, equal[0] - 1);
		process22(arr, equal[1] + 1, r);
	}

	// 3.3 QuickSort3.0 随机NetherLands
	public static void quickSort33(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process33(arr, 0, arr.length - 1);
	}

	public static void process33(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
		int[] equal = netherLands(arr, l, r);
		process33(arr, l, equal[0] - 1);
		process33(arr, equal[1] + 1, r);
	}
}
