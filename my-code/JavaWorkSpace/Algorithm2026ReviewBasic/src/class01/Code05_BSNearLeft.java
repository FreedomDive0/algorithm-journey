package class01;

public class Code05_BSNearLeft {
	// 有序数组中>=某个数最靠左位置
	public static int bsNearLeft(int[] arr, int num) {
		if (arr == null || arr.length < 1)
			return -1;
		int res = -1;
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] >= num) {
				res = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return res;
	}
}
