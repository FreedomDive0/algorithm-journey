package class03;

public class Code02_SmallSum {
	// 1.数组小和
	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2)
			return 0;
		return sProcess(arr, 0, arr.length - 1);
	}

	public static int sProcess(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = l + ((r - l) >> 1);
		return sProcess(arr, l, m) + sProcess(arr, m + 1, r) + sumMerge(arr, l, m, r);
	}

	public static int sumMerge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int idx = 0;
		int sum = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			sum += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
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
		return sum;
	}

	// 2. 求逆序对
	public static int reversePair(int[] arr) {
		if (arr == null || arr.length < 2)
			return 0;
		return pProcess(arr, 0, arr.length - 1);
	}

	public static int pProcess(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = l + ((r - l) >> 1);
		return pProcess(arr, l, m) + pProcess(arr, m + 1, r) + pairMerge(arr, l, m, r);
	}

	public static int pairMerge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int idx = 0;
		int count = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			count += arr[p1] > arr[p2] ? m - p1 + 1 : 0;
			help[idx++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
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
		return count;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 3, 1, 7, 0, 2 };
		System.out.println(reversePair(arr));
	}
}
