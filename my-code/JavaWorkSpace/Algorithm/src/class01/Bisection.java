package class01;

public class Bisection {
	// 有序数组中是否存在某个数
	public static int exist(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int l = 0;
		int r = arr.length - 1;
		while (l < r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return arr[l] == target ? l : -1;
	}

	// 有序数组寻找>=target最左位置
	public static int biggerLeft(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int l = 0;
		int r = arr.length - 1;
		int index = -1;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] >= target) {
				index = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return index;
	}

	// 有序数组寻找<=target最右位置
	public static int smallerRight(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int l = 0;
		int r = arr.length - 1;
		int index = -1;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] <= target) {
				index = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return index;
	}

	// 局部最小值
	public static int min(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int l = 1;
		int r = arr.length - 2;
		while (l < r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] > arr[mid - 1]) {
				r = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return l;// 首先局部最小值一定存在 且上面判断无误 所以剩余数字一定是局部最小值
	}
}
