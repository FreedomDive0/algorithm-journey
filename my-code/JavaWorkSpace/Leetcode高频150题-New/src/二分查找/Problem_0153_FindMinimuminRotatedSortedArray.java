package 二分查找;

public class Problem_0153_FindMinimuminRotatedSortedArray {
	public int findMin(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		int n = nums.length;
		if (nums[0] < nums[n - 1]) {
			return nums[0];
		}
		int l = 0;
		int r = n - 1;
		int index = -1;
		while (l <= r) {
			int m = (l + r) >> 1;
			if (nums[m] > nums[n - 1]) {
				l = m + 1;
				index = m;
			} else {
				r = m - 1;
			}
		}
		return nums[index + 1];
	}
}
