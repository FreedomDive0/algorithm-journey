
public class test {
	public static void main(String[] args) {
//		int[] nums = new int[] { 3, 4, 3, 2, 1 };
//		System.out.println(findPeakElement(nums));
//		int[] nums = new int[] { 0, 1, 3, 5, 6 };
//		System.out.println(hIndex(nums));
//		int a = 6;
//		int b = 7;
//		System.out.println("a：" + a + " b：" + b);
//		a = a ^ b;
//		b = a ^ b;
//		a = a ^ b;
//		System.out.println("a：" + a + " b：" + b);
		System.out.println(-1 / 2);
		System.out.println("\\\\");
	}

//
//	public static int findPeakElement(int[] nums) {
//		if (nums.length < 2) {
//			return 0;
//		}
//		if (nums[0] > nums[1]) {
//			return 0;
//		} else if (nums[nums.length - 1] > nums[nums.length - 2]) {
//			return nums.length - 1;
//		}
//		int l = 0;
//		int r = nums.length - 1;
//		while (l <= r) {
//			int m = l + ((r - l) >> 1);// 位运算优先级低
//			if (nums[m] < (m - 1 < 0 ? Integer.MIN_VALUE : nums[m - 1])) {
//				r = m - 1;
//			} else if (nums[m] < (m + 1 >= nums.length - 1 ? Integer.MIN_VALUE : nums[m + 1])) {
//				l = m + 1;
//			} else {
//				return m;
//			}
//		}
//		return -1;
//	}
//	public int findPeakElement(int[] nums) {
//		int left = 0, right = nums.length - 1;
//		while (left < right) {// ∵两侧为-∞ ∴左增右减 ∴可以在两种情况中选一种
//			int mid = left + (right - left) / 2;
//			if (nums[mid] > nums[mid + 1]) {
//				right = mid;
//			} else {
//				left = mid + 1;
//			}
//		}
//		return left;
//	}
//	public static int findDuplicate(int[] nums) {
//		int l = 0;
//		int r = nums.length - 1;
//		int res = -1;
//		while (l <= r) {
//			int cnt = 0;
//			int m = (l + r) >> 1;
//			for (int i = 0; i < nums.length; i++) {// 创造出二分条件
//				if (nums[i] <= m) {
//					cnt++;
//				}
//			}
//			if (cnt <= m) {
//				l = m + 1;
//			} else {
//				res = m;
//				r = m - 1;
//			}
//		}
//		return res;
//	}
//	public static int hIndex(int[] citations) {
//		int l = 0;
//		int r = citations.length - 1;
//		int res = 0;
//		while (l <= r) {
//			int m = (l + r) >> 1;
//			if (citations[m] >= citations.length - m) {
//				res = citations.length - m;
//				r = m - 1;
//			} else {
//				l = m + 1;
//			}
//		}
//		return res;
//	}
	public int upper_bound_(int n, int v, int[] a) {
		// write code here
		int l = 0;
		int r = a.length - 1;
		int res = -1;
		while (l <= r) {
			int m = (l + r) >> 2;
			if (a[m] >= v) {
				res = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return res == -1 ? n : res;
	}

}
