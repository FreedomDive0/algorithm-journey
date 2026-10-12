package topinterviewquestions;

public class Problem_0033_SearchInRotatedSortedArray {
	public static int search(int[] arr, int num) {
		if (arr == null || arr.length < 1) {
			return -1;
		}
		if (arr[0] < arr[arr.length - 1]) {// 数组未旋转
			return find(arr, num, 0, arr.length - 1);
		}
		int l = 0;
		int r = arr.length - 1;
		int end = arr[r];
		while (l < r) {// 找到旋转划分点
			int m = (l + r + 1) / 2;
			if (arr[m] > end) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		if (num >= arr[0]) {
			return find(arr, num, 0, l);
		} else {
			return find(arr, num, l + 1, arr.length - 1);
		}
	}

	public static int find(int[] arr, int target, int L, int R) {
		while (L <= R) {
			int M = (L + R) / 2;
			if (arr[M] > target) {
				R = M - 1;
			} else if (arr[M] < target) {
				L = M + 1;
			} else {
				return M;
			}
		}
		return -1;
	}
}
