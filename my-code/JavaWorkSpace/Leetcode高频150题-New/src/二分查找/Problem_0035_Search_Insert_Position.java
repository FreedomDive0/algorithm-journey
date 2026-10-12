package 二分查找;

public class Problem_0035_Search_Insert_Position {
	public int searchInsert(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		int index = 0;
		while (l <= r) {
			int m = (r + l) >> 1;
			if (nums[m] == target) {
				return m;
			} else if (nums[m] < target) {
				l = m + 1;
				index = l;
			} else {
				r = m - 1;
			}
		}
		return index;
	}
}
