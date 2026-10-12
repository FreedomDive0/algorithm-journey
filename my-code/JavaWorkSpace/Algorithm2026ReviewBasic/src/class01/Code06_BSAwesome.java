package class01;

public class Code06_BSAwesome {
	// 1.局部最小值
	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length < 1)
			return -1;
		if (arr.length == 0 || arr[0] < arr[1]) {
			return 0;
		}
		int l = 0;
		int r = arr.length - 1;
		if (arr[r] < arr[r - 1]) {
			return r;
		}
		int mid = 0;
		while (l < r) {
			mid = l + ((r - l) >> 1);
			if (arr[mid] > arr[mid - 1]) {
				r = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return l;
	}

}
