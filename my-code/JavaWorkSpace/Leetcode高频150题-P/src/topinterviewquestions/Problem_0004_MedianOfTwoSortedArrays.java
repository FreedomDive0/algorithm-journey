package topinterviewquestions;

public class Problem_0004_MedianOfTwoSortedArrays {
	// 训练营第4期class4 code2
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		boolean even = (size & 1) == 0;// even number偶数
		if (nums1.length != 0 && nums2.length != 0) {
			if (even) {
				return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
			} else {
				return findKthNum(nums1, nums2, size / 2 + 1);
			}
		} else if (nums1.length != 0) {
			if (even) {
				return (double) (nums1[size / 2 - 1] + nums1[size / 2]) / 2D;// 数组判断与找第k小下标不同需要注意
			} else {
				return nums1[size / 2];
			}
		} else if (nums2.length != 0) {
			if (even) {
				return (double) (nums2[size / 2 - 1] + nums2[size / 2]) / 2D;
			} else {
				return nums2[size / 2];
			}
		} else {
			return 0;
		}
	}

	// 方法原型1：
	// 1.arr1[] arr2[]长度相等且内部有序 return两个数组合并上中位数(递归版本)
//	public int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
//		if (s1 == e1) {
//			return arr1[s1] < arr2[s2] ? arr1[s1] : arr2[s2];
//		}
//		int mid1 = (s1 + e1) / 2;
//		int mid2 = (s2 + e2) / 2;
//		if (arr1[mid1] == arr2[mid2]) {
//			return arr1[mid1];
//		}
//		if (((e1 - s1 + 1) & 1) == 1) {// 判断一个数是否为奇数
//			if (arr1[mid1] > arr2[mid2]) {
//				if (arr2[mid2] >= arr1[mid1 - 1]) {// 奇数需要剔除一个位置保持两个数组长度相同方便递归
//					return arr2[mid2];
//				} else {
//					return getUpMedian(arr1, s1, mid1 - 1, arr2, mid2 + 1, e2);
//				}
//			} else {
//				if (arr1[mid1] >= arr2[mid2 - 1]) {
//					return arr1[mid1];
//				} else {
//					return getUpMedian(arr1, mid1 + 1, e1, arr2, s2, mid2 - 1);
//				}
//			}
//		} else {
//			if (arr1[mid1] > arr2[mid2]) {
//				return getUpMedian(arr1, s1, mid1, arr2, mid2 + 1, e2);
//			} else {
//				return getUpMedian(arr1, mid1 + 1, e1, arr2, s2, mid2);
//			}
//		}
//	}
	// 1.arr1[] arr2[]长度相等且内部有序 return两个数组合并上中位数(迭代版本)
	public int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		while (s1 < e1) {
			mid1 = (s1 + e1) / 2;
			mid2 = (s2 + e2) / 2;
			if (arr1[mid1] == arr2[mid2]) {
				return arr1[mid1];
			}
			if (((e1 - s1 + 1) & 1) == 1) {// 判断一个数是否为奇数
				if (arr1[mid1] > arr2[mid2]) {
					if (arr2[mid2] >= arr1[mid1 - 1]) {// 奇数需要剔除一个位置保持两个数组长度相同方便递归
						return arr2[mid2];
					}
					e1 = mid1 - 1;
					s2 = mid2 + 1;
				} else {
					if (arr1[mid1] >= arr2[mid2 - 1]) {
						return arr1[mid1];
					}
					s1 = mid1 + 1;
					e2 = mid2 - 1;
				}
			} else {
				if (arr1[mid1] > arr2[mid2]) {
					e1 = mid1;
					s2 = mid2 + 1;
				} else {
					s1 = mid1 + 1;
					e2 = mid2;
				}
			}
		}
		return Math.min(arr1[s1], arr2[s2]);
	}

	// 方法原型2：
	// 在有序数组arr1和arr2中找第K小
	public int findKthNum(int[] arr1, int[] arr2, int k) {
		int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
		int[] longs = shorts == arr1 ? arr2 : arr1;
		int s = shorts.length;
		int l = longs.length;
		if (k <= s) {
			return getUpMedian(arr1, 0, k - 1, arr2, 0, k - 1);
		}
		if (k > l) {
			if (shorts[k - l - 1] >= longs[l - 1]) {
				return shorts[k - l - 1];
			} else if (longs[k - s - 1] >= shorts[s - 1]) {
				return longs[k - s - 1];
			} else {
				return getUpMedian(shorts, k - l, s - 1, longs, k - s, l - 1);
			}
		}
		if (longs[k - s - 1] >= shorts[s - 1]) {
			return longs[k - s - 1];
		}
		return getUpMedian(shorts, 0, s - 1, longs, k - s, k - 1);
	}
}
