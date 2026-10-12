package 二分专题;

public class LC153_寻找旋转排序数组中的最小值 {
	public int findMin(int[] nums) {
		int l = 0;
		int r = nums.length - 1;
		int num = nums[r];
		if (nums[0] <= nums[r])// 排除nums.length长度次旋转
			return nums[0];
		while (l < r) {// 找到最大值下标l l+1及为最小值
			int mid = l + (r - l + 1) / 2;
			if (nums[mid] > num) {
				l = mid;
			} else {
				r = mid - 1;
			}
		}
		return nums[l + 1];
	}
}
