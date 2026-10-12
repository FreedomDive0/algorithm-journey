package 二分查找;

public class Problem_0162_Find_Peak_Element {
	public int findPeakElement(int[] nums) {
		if (nums.length == 1) {
			return 0;
		}
		if (nums[0] > nums[1]) {
			return 0;
		}
		int n = nums.length;
		if (nums[n - 1] > nums[n - 2]) {
			return n - 1;
		}
		int l = 0;
		int r = n - 1;
		while (l <= r) {
			int m = (l + r) >> 1;
			if (m - 1 >= 0 && nums[m] < nums[m - 1]) {
				r = m - 1;
			} else if (m + 1 < n && nums[m] < nums[m + 1]) {
				l = m + 1;
			} else {
				return m;
			}
		}
		return -1;
	}
}
