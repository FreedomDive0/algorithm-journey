package 二分查找;

public class Problem_0004_Median_of_Two_Sorted_Arrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		boolean even = (size & 1) == 0;// even偶数
		if (nums1.length != 0 && nums2.length != 0) {
			if (even) {
				return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
			} else {
				return findKthNum(nums1, nums2, size / 2 + 1);
			}
		} else if (nums1.length == 0) {
			if (even) {
				return (double) (nums2[size / 2 - 1] + nums2[size / 2]) / 2D;
			} else {
				return nums2[size / 2];
			}
		} else if (nums2.length == 0) {
			if (even) {
				return (double) (nums1[size / 2 - 1] + nums1[size / 2]) / 2D;
			} else {
				return nums1[size / 2];
			}
		} else {// 两个数组长度都为0
			return 0;
		}
	}

	// 有序不一定等长数组求第k小
	public int findKthNum(int[] nums1, int[] nums2, int k) {
		int[] shorts = nums1.length < nums2.length ? nums1 : nums2;
		int[] longs = shorts == nums1 ? nums2 : nums1;
		int s = shorts.length;
		int l = longs.length;
		if (k <= s) {
			return getUpMedian(shorts, 0, k - 1, longs, 0, k - 1);
		}
		if (k > s && k <= l) {
			if (longs[k - s - 1] >= shorts[s - 1]) {
				return longs[k - s - 1];
			}
			return getUpMedian(shorts, 0, s - 1, longs, k - s, k - 1);
		}
		if (k > l && k <= s + l) {
			if (shorts[k - l - 1] >= longs[l - 1]) {
				return shorts[k - l - 1];
			} else if (longs[k - s - 1] >= shorts[s - 1]) {
				return longs[k - s - 1];
			}
		}
		return getUpMedian(shorts, k - l, s - 1, longs, k - s, s - 1);
	}

	// 有序等长数组 得到上中位数
	public int getUpMedian(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {
		int m1 = 0;
		int m2 = 0;
		while (s1 < e1) {
			m1 = (s1 + e1) >> 1;
			m2 = (s2 + e2) >> 1;
			if (nums1[m1] == nums2[m2]) {
				return nums1[m1];
			}
			if (((e1 - s1 + 1) & 1) == 1) {// 奇数
				if (nums1[m1] > nums2[m2]) {
					if (nums2[m2] >= nums1[m1 - 1]) {
						return nums2[m2];
					} else {
						e1 = m1 - 1;
						s2 = m2 + 1;
					}
				} else {
					if (nums1[m1] >= nums2[m2 - 1]) {
						return nums1[m1];
					} else {
						s1 = m1 + 1;
						e2 = m2 - 1;
					}
				}
			} else {
				if (nums1[m1] > nums2[m2]) {
					e1 = m1;// 注意偶数下标取舍
					s2 = m2 + 1;
				} else {
					s1 = m1 + 1;
					e2 = m2;
				}
			}
		}
		return Math.min(nums1[s1], nums2[s2]);
	}
}
