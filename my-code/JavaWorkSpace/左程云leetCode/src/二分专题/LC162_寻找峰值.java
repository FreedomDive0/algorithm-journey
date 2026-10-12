package 二分专题;

public class LC162_寻找峰值 {
	public static void main(String[] args) {
		System.out.println(findPeakElement(new int[] { 2, 1 }));
	}

	public static int findPeakElement(int[] nums) {
//		int l = 0;
//		int r = nums.length - 1;
//		int index = -1;
//		if (nums.length == 1) {
//			return 0;
//		}
//		while (l <= r) {//
//			int mid = (l + r) / 2;
//			if (nums[mid] < (mid - 1 < 0 ? Integer.MIN_VALUE : nums[mid - 1])) {
//				r = mid - 1;
//			} else if (nums[mid] < (mid + 1 >= nums.length ? Integer.MIN_VALUE : nums[mid + 1])) {
//				l = mid + 1;
//			} else {
//				return mid;
//			}
//		}
//		return index;
		int left = 0, right = nums.length - 1;
		while (left < right) {// 因为数组两侧为-∞ ∵左右两侧皆为递增 ∴峰值一定出现在大于区
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[mid + 1]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
