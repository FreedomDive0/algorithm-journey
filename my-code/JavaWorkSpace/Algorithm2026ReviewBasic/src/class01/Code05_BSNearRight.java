package class01;

public class Code05_BSNearRight {
	// 有序数组中找到<=num最右位置
	public static int bsNearRight(int[] arr, int num) {
		int res = -1;
		if (arr == null || arr.length < 1)
			return res;
		int l = 0;
		int r = arr.length - 1;
		int mid = 0;
		while (l <= r) {
			mid = l + ((r - l) >> 1);
			if (arr[mid] <= num) {
				res = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return res;
	}
}
