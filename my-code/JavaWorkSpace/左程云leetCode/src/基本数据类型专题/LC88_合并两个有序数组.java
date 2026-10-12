package 基本数据类型专题;

public class LC88_合并两个有序数组 {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] copy = new int[m];
		for (int i = 0; i < m; i++) {
			copy[i] = nums1[i];
		}
		int index = 0;
		int i = 0, j = 0;
		for (; i < m && j < n;) {
			if (copy[i] < nums2[j]) {
				nums1[index++] = copy[i++];
			} else {
				nums1[index++] = nums2[j++];
			}
		}
		for (; i < m; i++) {
			nums1[index++] = copy[i];
		}
		for (; j < n; j++) {
			nums1[index++] = nums2[j];
		}
	}
}
