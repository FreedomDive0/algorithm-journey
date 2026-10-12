package class03;

//bfprt解决无序数组第k小问题
public class Code01_FindMinKth {
	// leetcode 215
	// 1.笔试使用 快排netherLands
	public static int minKth1(int[] arr, int k) {
		if (arr == null || arr.length < 1 || arr.length < k) {
			return -1;
		}
		String[] nums = new String[2];
		int[] arr1 = new int[2];
		for (int i = 0; i < nums.length; i++) {
			arr1[i] = Integer.valueOf(nums[i]);
		}
		return netherLand(arr, 0, arr.length - 1, k - 1);
	}

	public static int netherLand(int[] arr, int l, int r, int k) {
		swap(arr, r, l + (int) (Math.random() * (r - l + 1)));
		int[] equals = process(arr, l, r);
		if (k >= equals[0] && k <= equals[1]) {
			return arr[k];
		} else if (k < equals[0]) {
			return netherLand(arr, l, equals[0] - 1, k);
		} else {
			return netherLand(arr, equals[0] + 1, r, k);
		}
	}

	public static int[] process(int[] arr, int l, int r) {
		if (l > r) {
			return new int[] { -1, -1 };
		}
		if (l == r) {
			return new int[] { l, r };
		}
		int less = l - 1;
		int more = r;
		int idx = l;
		while (idx < more) {
			if (arr[idx] < arr[r]) {
				swap(arr, idx++, ++less);
			} else if (arr[idx] > arr[r]) {
				swap(arr, idx, --more);
			} else {
				idx++;
			}
		}
		swap(arr, more, r);
		return new int[] { less + 1, more };
	}

	public static void swap(int[] arr, int i, int j) {
		if (i == j) {
			return;
		}
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}

	// 2.bfprt精细化选值 从计算上避免概率直接确定时间复杂度
	public static int minKth2(int[] arr, int k) {
		if (arr == null || arr.length < 1 || arr.length < k) {
			return -1;
		}
		return bfprt(arr, 0, arr.length - 1, k - 1);
	}

	public static int bfprt(int[] arr, int l, int r, int k) {
		if (l == r) {
			return arr[k];
		}
		int pivot = medianOfMedians(arr, l, r);
		int[] equals = netherland(arr, l, r, pivot);
		if (k >= equals[0] && k <= equals[1]) {
			return arr[k];
		} else if (k < equals[0]) {
			return bfprt(arr, l, equals[0] - 1, k);
		} else {
			return bfprt(arr, equals[1] + 1, r, k);
		}
	}

	public static int medianOfMedians(int[] arr, int l, int r) {
		int size = r - l + 1;
		int offSet = size % 5 == 0 ? 0 : 1;
		int[] medians = new int[size / 5 + offSet];
		for (int team = 0; team < medians.length; team++) {
			int teamFisrt = l + team * 5;
			medians[team] = getMedian(arr, teamFisrt, Math.min(teamFisrt + 4, r));
		}
		return bfprt(medians, 0, medians.length - 1, medians.length / 2);
	}

	public static int getMedian(int[] arr, int l, int r) {
		insertionSort(arr, l, r);
		System.out.println("l: " + l + " r: " + r);
		return arr[(l + r) / 2];
	}

	public static void insertionSort(int[] arr, int l, int r) {
		if (arr == null || arr.length < 2 || r - l < 1 || l == r) {
			return;
		}
		for (int i = l + 1; i <= r; i++) {
			for (int idx = l; idx > l && arr[idx] < arr[idx - 1]; idx++) {
				swap(arr, idx - 1, idx);
			}
		}
	}

	public static int[] netherland(int[] arr, int l, int r, int pivot) {
		if (l > r) {
			return new int[] { -1, -1 };
		}
		if (l == r) {
			return new int[] { l, r };
		}
		int less = l - 1;
		int more = r + 1;
		int idx = l;
		while (idx < more) {
			if (arr[idx] < pivot) {
				swap(arr, idx++, ++less);
			} else if (arr[idx] > pivot) {
				swap(arr, idx, --more);
			} else {
				idx++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 3, 2, 1, 5, 6, 4 };
		System.out.println(minKth2(arr, 2));
	}
}
