package class02;

public class Code08_GetMax {
	// 递归找数组中的最大值
	public int getMax(int[] arr) {
		return processGM(arr, 0, arr.length - 1);
	}

	private int processGM(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		int mid = l + ((r - l) >> 1);
		return Math.max(processGM(arr, l, mid), processGM(arr, mid + 1, r));
	}
}
