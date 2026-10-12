package class03;

public class Code01_MergeSort {
	// 归并排序
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		int m = l + ((r - l) >> 1);
		process(arr, l, m);
		process(arr, m + 1, r);
		merge(arr, l, m, r);
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int idx = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[idx++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[idx++] = arr[p1++];
		}
		while (p2 <= r) {
			help[idx++] = arr[p2++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

	// 2.归并排序(迭代)
	public static void mergeIteration(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		int N = arr.length;
		int mergeSize = 1;
		while (mergeSize < N) {
			int l = 0;
			while (l < N) {
				int m = Math.min(l + mergeSize - 1, N - 1);
				if (m >= N - 1)// 当前这一趟比完
					break;
				int r = Math.min(m + mergeSize, N - 1);
				merge(arr, l, m, r);
				l = r + 1;
			}
			if (mergeSize > N / 2)// 整个任务结束
				break;
			mergeSize <<= 1;
		}
	}
}
