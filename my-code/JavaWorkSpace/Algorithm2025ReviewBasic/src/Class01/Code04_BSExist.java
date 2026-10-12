package class01;

public class Code04_BSExist {
	// 二分法有序数组中给定数字是否存在
	public static boolean exist(int[] sortArr, int targetNum) {
		if (sortArr == null || sortArr.length < 1)
			return false;
		int l = 0;
		int r = sortArr.length - 1;
		int mid = 0;
		while (l <= r) {
			mid = l + ((r - l) >> 1);
			if (sortArr[mid] == targetNum) {
				return true;
			} else if (sortArr[mid] > targetNum) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 0, 3, 4, 6, 7, 7, 8 };
		System.out.println(exist(arr, 4));
	}
}
