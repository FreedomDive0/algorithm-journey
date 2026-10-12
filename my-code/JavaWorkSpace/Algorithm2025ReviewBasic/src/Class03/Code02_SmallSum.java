package class03;

public class Code02_SmallSum {
	// 1.数的小和
	public static int smallSum1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return smallSumProcess(arr, 0, arr.length - 1);
	}

	public static int smallSumProcess(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = l + ((r - l) >> 1);
		return smallSumProcess(arr, l, m) + smallSumProcess(arr, m + 1, r) + merge(arr, l, m, r);
	}

	public static int merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int idx1 = l;
		int idx2 = m + 1;
		int idx = 0;
		int sum = 0;
		while (idx1 <= m && idx2 <= r) {
			if (arr[idx1] < arr[idx2]) {
				sum += (r - idx2 + 1) * arr[idx1];
				help[idx++] = arr[idx1++];
			} else {
				help[idx++] = arr[idx2++];
			}
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
		return sum;
	}

	// 2.逆序对ReversePair
	public static int reversePairs(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return reversePairProcess(arr, 0, arr.length - 1);
	}

	public static int reversePairProcess(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = l + ((r - l) >> 1);
		return reversePairProcess(arr, l, m) + reversePairProcess(arr, m + 1, r) + merge1(arr, l, m, r);
	}

	public static int merge1(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int idx = 0;
		int idx1 = l;
		int idx2 = m + 1;
		int pairNum = 0;
		while (idx1 <= m && idx2 <= r) {
			if (arr[idx1] > arr[idx2]) {
				pairNum += m - idx1 + 1;
				help[idx++] = arr[idx2++];
			} else {
				help[idx++] = arr[idx1++];
			}
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
		return pairNum;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 3, 1, 7, 0, 2 };
		System.out.println(reversePairs(arr));
	}

}
