package 二分专题;

public class LC35_搜索插入位置 {
//	public int searchInsert(int[] nums, int target) {
//		int l = 0;
//		int r = nums.length - 1;
//		int index = -1;
//		while (l <= r) {
//			int mid = l + (r - l) / 2;
//			if (nums[mid] == target) {
//				index = mid;
//				break;
//			} else if (nums[mid] > target) {
//				r = mid - 1;
//			} else {
//				l = mid + 1;
//			}
//		}
//		if (index == -1) {
//			if (nums[l] < target) {
//				return l + 1;
//			} else {
//				return l;
//			}
//		}
//		return index;
//	}
//	public int searchInsert(int[] nums, int target) {
//		int l = 0;
//		int r = nums.length - 1;
//		while (l <= r) {
//			int mid = l + (r - l) / 2;
//			if (nums[mid] == target) {
//				return mid;
//			} else if (nums[mid] > target) {
//				r = mid - 1;
//			} else {
//				l = mid + 1;
//			}
//		}
//		return l;
////		选用l 最后l==r时 --> 1.nums[l]>target 会进入 r = mid -1 l不会被影响 l及为target所在位置
////						  2.nums[l]<target 会进入 l = mid + 1 mid+1恰好为target所在位置
//	}

	public int searchInsert(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		int index = 0;
		while (l <= r) {
			int m = (l + r) / 2;
			if (nums[m] > target) {
				r = m - 1;
			} else if (nums[m] < target) {
				l = m + 1;
				index = l;
			} else {
				return m;
			}
		}
		return index;
	}

}
