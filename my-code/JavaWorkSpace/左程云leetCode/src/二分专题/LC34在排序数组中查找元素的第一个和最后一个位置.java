package 二分专题;

public class LC34在排序数组中查找元素的第一个和最后一个位置 {
//	public static void main(String[] args) {
//		int[] arr = { 5, 7, 7, 8, 8, 10 };
//		System.out.println(searchRange(arr, 8));
//	}
//
//	public static int[] searchRange(int[] nums, int target) {
//		if (nums == null || nums.length == 0) {
//			return new int[] { -1, -1 };
//		}
//		int l = 0;
//		int r = nums.length - 1;
//		while (l < r) {// 找>=target左边界 及未来左边界
//			int mid = (l + r) / 2;// ∵l<r ∴(l+r)/2<r 所以不会陷入死循环
//			if (nums[mid] >= target) {
//				r = mid;
//			} else {
//				l = mid + 1;
//			}
//		}
//		if (nums[r] != target) {
//			return new int[] { -1, -1 };
//		}
//		int L = r;
//		l = r;
//		r = nums.length - 1;
//		while (l < r) {// 找<=target右边界 及未来右边界
//			int mid = (l + r + 1) / 2;// 在r==l+1时 (r+l)/2==l l不发生改变且nums[l]不变 陷入死循环
//			if (nums[mid] <= target) {
//				l = mid;
//			} else {
//				r = mid - 1;
//			}
//		}
//		return new int[] { L, r };
//	}
//	public int[] searchRange(int[] nums, int target) {// 二分法：根据条件筛掉一半不符合的情况 需要明确区分两边(当找到符合条件的位置可以两边扩张 所以范围不可以一次求出
//														// 需分别求出边界)
//		int[] res = new int[] { -1, -1 };
//		int l = 0;
//		int r = nums.length - 1;
//		while (l <= r) {// 左边界
//			int m = (l + r) / 2;
//			if (nums[m] < target) {
//				l = m + 1;
//			} else if (nums[m] > target) {
//				r = m - 1;
//			} else {
//				r = m - 1;
//				res[0] = m;
//			}
//		}
//		if (res[0] == -1) {// 存在该目标数字
//			return res;
//		}
//		l = res[0];// 更新边界
//		r = nums.length - 1;
//		while (l <= r) {// 右边界
//			int m = (l + r) / 2;
//			if (nums[m] < target) {
//				l = m + 1;
//			} else if (nums[m] > target) {
//				r = m - 1;
//			} else {
//				l = m + 1;
//				res[1] = m;
//			}
//		}
//		return res;
//	}

	// 优化：
	public int[] searchRange(int[] nums, int target) {// 二分法：根据条件筛掉一半不符合的情况 需要明确区分两边(当找到符合条件的位置可以两边扩张 所以范围不可以一次求出
		// 需分别求出边界)
		if (nums == null || nums.length == 0) {
			return new int[] { -1, -1 };
		}
		int l = 0;
		int r = nums.length - 1;
		while (l < r) {// 求最小>=target位置
			int m = (l + r) / 2;
			if (nums[m] >= target) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		if (nums[r] != target) {// 是否存在目标数字
			return new int[] { -1, -1 };
		}
		int L = r;
		l = r;// 更新边界
		r = nums.length - 1;
		while (l < r) {// 求最大<=target位置
			int m = (l + r + 1) / 2;// ∵l<r ∴(l+r)>=l ∴可能陷入死循环
			if (nums[m] <= target) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		return new int[] { L, r };
	}
}
