package class03;

public class Code01_MergeSort {
	// 归并排序
	// 1.递归实现
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		mergeSortProcess(arr, 0, arr.length - 1);
	}

	public static void mergeSortProcess(int[] arr, int l, int r) {
		if (l == r)
			return;
		int m = l + ((r - l) >> 1);
		mergeSortProcess(arr, l, m);
		mergeSortProcess(arr, m + 1, r);
		merge(arr, l, m, r);
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int idx1 = l;
		int idx2 = m + 1;
		int idx = 0;
		while (idx1 <= m && idx2 <= r) {
			help[idx++] = arr[idx1] <= arr[idx2] ? arr[idx1++] : arr[idx2++];
		}
		while (idx1 <= m) {
			help[idx++] = arr[idx1++];
		}
		while (idx2 <= r) {
			help[idx++] = arr[idx2++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

	// 2.非递归实现
	public static void mergeSortNonRecursive(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int mergeSize = 1;
		int N = arr.length;
		while (mergeSize < N) {// 不能使用mergeSize < N/2作为判断依据 因为mergeSize <<= 1; >=N时 还没有完成本次排序
			int l = 0;
			while (l < N) {
				int m = Math.min(l + mergeSize - 1, N - 1);
				if (m >= N - 1) {// 小加速
					break;
				}
				int r = Math.min(m + mergeSize, N - 1);
				merge(arr, l, m, r);
				l = r + 1;
			}
			if (mergeSize > N / 2) {// 防止整型溢出检测
				break;
			}
			mergeSize <<= 1;
		}
	}
}
