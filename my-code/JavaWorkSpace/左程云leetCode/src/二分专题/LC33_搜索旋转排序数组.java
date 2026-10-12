package 二分专题;

public class LC33_搜索旋转排序数组 {
	public int search(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		int num = nums[r];
		if (nums[0] <= nums[r]) {// 排除正常有序情况
			return find(nums, target, l, r);
		}
		while (l < r) {// 找到分界线
			int mid = l + (r - l + 1) / 2;
			if (nums[mid] > num) {
				l = mid;
			} else {
				r = mid - 1;
			}
		}
		if (target >= nums[0]) {
			return find(nums, target, 0, l);
		} else {
			return find(nums, target, l + 1, nums.length - 1);
		}
	}

	public int find(int[] nums, int target, int l, int r) {
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return -1;
	}

//	public static int search(int[] nums, int target) {
//		int l = 0;
//		int r = nums.length - 1;
//		if (nums[0] < nums[r]) {
////			System.out.println("实际未发生旋转");
//			return find(nums, 0, r, target);
//		}
//		int num = nums[r];
//		while (l < r) {
//			int m = (l + r + 1) / 2;
//			if (nums[m] > num) {
//				l = m;
//			} else {
//				r = m - 1;
//			}
//		}
//		int L = l;
////		System.out.println("分界线为：" + L);
//		if (nums[nums.length - 1] >= target) {
////			System.out.println("进入右侧");
//			return find(nums, L + 1, nums.length - 1, target);
//		} else {
////			System.out.println("进入左侧");
//			return find(nums, 0, L, target);
//		}
//	}
//
//	public static int find(int[] nums, int L, int R, int target) {
//		int l = L;
//		int r = R;
//		while (l <= r) {
//			int m = (l + r) / 2;
//			if (nums[m] < target) {
//				l = m + 1;
//			} else if (nums[m] > target) {
//				r = m - 1;
//			} else {
//				return m;
//			}
//		}
//		return -1;
//	}
}
