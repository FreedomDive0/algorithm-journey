package class03;

public class Review1 {
	// 1.bfprt 无序数组找第k小
	// (1).通过概率计算得出时间复杂度O(N)
	public static int bfprtQS(int[] arr, int k) {
		if (arr == null) {
			return -1;
		}
		return processBQS(arr, 0, arr.length - 1, k);
	}

	public static int processBQS(int[] arr, int L, int R, int k) {
		if (L > R) {
			return -1;
		}
		if (L == R) {
			return arr[L];
		}
		int povit = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = netherLand(arr, L, R, povit);
		if (k >= range[0] & k <= range[1]) {
			return arr[range[0]];
		} else if (k < range[0]) {
			return processBQS(arr, L, range[0] - 1, k);
		} else {
			return processBQS(arr, range[1] + 1, R, k);
		}
	}

	public static int[] netherLand(int[] arr, int L, int R, int povit) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1;
		int more = R + 1;
		int index = L;
		while (index < more) {
			if (arr[index] < povit) {
				swap(arr, ++less, index++);
			} else if (arr[index] > povit) {
				swap(arr, --more, index++);
			} else {
				index++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	// (2)bfprt 通过特定取值 确定时间复杂度
	public static int bfprt(int[] arr, int k) {
		if (arr == null || k < 1 || arr.length < k) {
			return -1;
		}
		return processBFPRT(arr, 0, arr.length - 1, k - 1);
	}

	public static int processBFPRT(int[] arr, int l, int r, int k) {// 递归必然要有两端范围 才能缩小区域继续
		if (l == r) {
			return arr[l];
		}
		int num = medianOfMedians1(arr, l, r);
		int[] range = netherLand(arr, l, r, num);
		if (k >= range[0] && k <= range[1]) {
			return arr[k];
		} else if (k < range[0]) {
			return processBFPRT(arr, l, range[0] - 1, k);
		} else {
			return processBFPRT(arr, range[1] + 1, r, k);
		}
	}

	public static int medianOfMedians1(int[] arr, int l, int r) {// 选取5个一组中位数的中位数 每次至少排除3/10的数字
		int size = r - l + 1;
		int offset = size % 5 == 0 ? 0 : 1;
		int[] medians = new int[size / 5 + offset];
		for (int i = 0; i < medians.length; i++) {
			int teamFirst = l + i * 5;
			medians[i] = getMedian1(arr, teamFirst, Math.min(teamFirst + 4, r));
		}
		return processBFPRT(medians, 0, medians.length - 1, medians.length / 2);
	}

	public static int getMedian1(int[] arr, int l, int r) {
		insertSort(arr, l, r);
		return arr[(l + r) / 2];
	}

	public static void insertSort(int[] arr, int l, int r) {
		if (arr == null || arr.length < 1) {
			return;
		}
		for (int i = l + 1; i < r; i++) {
			for (int j = i; j - 1 >= l && arr[j - 1] > arr[j]; j--) {
				swap(arr, i, j);
			}
		}
	}
}
