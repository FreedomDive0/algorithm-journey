package class02;

public class Code07_KthMinPair {
	// arr N个数 组成数值对N^2个 返回排序后第K个
	public static int[] kthMin(int[] arr, int K) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		int N = arr.length;
		if (Math.pow(N, 2) < K) {
			return null;
		}
		int[] ans = new int[2];
		int first = findKthMin(arr, (K - 1) / N);
		int numLess = 0;
		int numEqual = 0;
		for (int i = 0; i < ans.length; i++) {
			if (arr[i] < first) {
				numLess++;
			}
			if (arr[i] == first) {
				numEqual++;
			}
		}
		int second = findKthMin(arr, (K - numLess * N - 1) / numEqual);
		ans[0] = first;
		ans[1] = second;
		return ans;
	}

	// bfprt算法 O(N)求出无序数组Kth位置的数
	public static int findKthMin(int[] arr, int k) {
		int[] array = copyArray(arr);
		return bfprt(array, 0, arr.length - 1, k - 1);
	}

	public static int bfprt(int[] arr, int k, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		int m = getMidArr(arr, l, r);// 返回小组中位数数组的中位数 精心求出的数字m 用于划分
		int[] range = partition(arr, l, r, m);
		if (k < range[0]) {
			return bfprt(arr, k, l, range[0] - 1);
		} else if (k > range[1]) {
			return bfprt(arr, k, range[1] + 1, r);
		} else {
			return arr[range[0]];
		}
	}

	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		for (int i = 0; i != ans.length; i++) {
			ans[i] = arr[i];
		}
		return ans;
	}

//		返回中位数数组的中位数
	public static int getMidArr(int[] arr, int l, int r) {
		int offSet = r % 5 == 0 ? 0 : 1;
		int size = r - l + 1;
		int[] mArr = new int[size / 5 + offSet];// 将原数组每5个一份 排序求中位数 mArr存放每组数的中位数
		for (int i = 0; i < mArr.length; i++) {
			int teamFirst = l + i * 5;
			insertSort(arr, teamFirst, Math.min(teamFirst + 4, r));
			mArr[i] = arr[(teamFirst + Math.min(teamFirst + 4, r)) / 2];
		}
		return bfprt(mArr, mArr.length / 2, 0, mArr.length - 1);// 递归调用了上层bfprt 求中位数数组(无序)的中位数
	}

	public static int[] partition(int[] arr, int L, int R, int M) {
		int less = L - 1;
		int more = R + 1;
		int index = L;
		while (index < more) {
			if (arr[index] < M) {
				swap(arr, index++, ++less);
			} else if (arr[index] == M) {
				index++;
			} else {
				swap(arr, index, --more);
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int i, int j) {
		if (arr == null || i == j) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 插入排序 常数时间小
	public static void insertSort(int[] arr, int s, int e) {
		for (int i = s; i < e; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}
}
