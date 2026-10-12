package 数组字符串;

public class Problem_0088_MergeSortedArray {
	// 倒着填
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int e1 = m - 1;
		int e2 = n - 1;
		int index = n + m - 1;
		while (e1 >= 0 && e2 >= 0) {
			if (nums1[e1] > nums2[e2]) {
				nums1[index--] = nums1[e1--];
			} else {
				nums1[index--] = nums2[e2--];
			}
		}
		while (e2 >= 0) {
			nums1[index--] = nums2[e2--];
		}
	}
}
