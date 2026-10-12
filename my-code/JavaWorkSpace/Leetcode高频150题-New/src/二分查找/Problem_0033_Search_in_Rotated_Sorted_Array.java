package 二分查找;

public class Problem_0033_Search_in_Rotated_Sorted_Array {
	public int search(int[] nums, int target) {
		int n = nums.length;
		if (nums[0] < nums[n - 1]) {
			return find(nums, 0, n - 1, target);
		}
		int l = 0;
		int r = n - 1;
		while (l < r) {
			int m = (l + r + 1) >> 1;
			if (nums[m] > nums[n - 1]) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		if (target >= nums[0]) {
			return find(nums, 0, l, target);
		} else {
			return find(nums, l + 1, n - 1, target);
		}
	}

	public int find(int[] nums, int l, int r, int target) {
		while (l <= r) {
			int m = (l + r) >> 1;
			if (nums[m] < target) {
				l = m + 1;
			} else if (nums[m] > target) {
				r = m - 1;
			} else {
				return m;
			}
		}
		return -1;
	}
}
