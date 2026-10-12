package 二分查找;

public class Problem_0034_FindFirstandLastPositionofElementinSortedArray {
	public int[] searchRange(int[] nums, int target) {
		if (nums.length < 1) {
			return new int[] { -1, -1 };
		}
		int l = 0;
		int r = nums.length - 1;
		int first = -1;
		while (l <= r) {
			int m = (l + r) >> 1;
			if (nums[m] > target) {
				r = m - 1;
			} else if (nums[m] < target) {
				l = m + 1;
			} else {
				first = m;
				r = m - 1;
			}
		}
		int last = -1;
		l = 0;
		r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) >> 1;
			if (nums[m] > target) {
				r = m - 1;
			} else if (nums[m] < target) {
				l = m + 1;
			} else {
				last = m;
				l = m + 1;
			}
		}
		return new int[] { first, last };
	}
}
