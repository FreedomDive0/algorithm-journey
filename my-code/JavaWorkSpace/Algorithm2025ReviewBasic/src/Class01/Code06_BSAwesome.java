package class01;

public class Code06_BSAwesome {
	// 局部最小值
	public static int localMinimum(int[] arr) {
		if (arr == null || arr.length < 1)
			return -1;
		if (arr.length == 1 || arr[0] < arr[1])
			return 0;
		if (arr[arr.length - 1] < arr[arr.length - 2])
			return arr.length - 1;
		int l = 1;
		int r = arr.length - 2;
		int mid = 0;
		while (l <= r) {
			mid = l + ((r - l) >> 1);
			if (arr[mid] < arr[mid - 1]) {
				r = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
