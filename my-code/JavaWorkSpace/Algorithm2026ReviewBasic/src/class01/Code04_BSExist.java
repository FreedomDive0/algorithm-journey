package class01;

public class Code04_BSExist {
	// 二分查找有序数组中是否存在某个数
	public static boolean bsExist(int[] arr, int num) {
		if (arr == null || arr.length < 1)
			return false;
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] == num) {
				return true;
			} else if (arr[mid] > num) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return false;
	}
}
