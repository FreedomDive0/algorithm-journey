package topinterviewquestions;

public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {
	public static int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length < 1) {
			return new int[] { -1, -1 };
		}
		int[] res = new int[2];
		res[0] = findFirst(nums, target);
		res[1] = findLast(nums, target);
		return res;
	}

	public static int findFirst(int[] arr, int num) {
		int l = 0;
		int r = arr.length - 1;
		int index = -1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (arr[m] > num) {
				r = m - 1;
			} else if (arr[m] < num) {
				l = m + 1;
			} else {
				index = m;
				r = m - 1;
			}
		}
		return index;
	}

	public static int findLast(int[] arr, int num) {
		int l = 0;
		int r = arr.length - 1;
		int index = -1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (arr[m] > num) {
				r = m - 1;
			} else if (arr[m] < num) {
				l = m + 1;
			} else {
				index = m;
				l = m + 1;
			}
		}
		return index;
	}
}
