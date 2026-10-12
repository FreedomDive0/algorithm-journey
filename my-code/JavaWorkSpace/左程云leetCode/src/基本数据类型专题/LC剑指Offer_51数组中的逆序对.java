package 基本数据类型专题;

public class LC剑指Offer_51数组中的逆序对 {
	public int reversePairs(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		return process(nums, 0, nums.length - 1);
	}

	public int process(int[] nums, int L, int R) {
		if (L == R) {
			return 0;
		}
		int mid = L + ((R - L) >> 1);
		return process(nums, L, mid) + process(nums, mid + 1, R) + merge(nums, L, mid, R);
	}

	private int merge(int[] arr, int L, int M, int R) {
		int[] help = new int[R - L + 1];
		int p1 = L, p2 = M + 1;
		int res = 0;
		int i = 0;
		while (p1 <= M && p2 <= R) {
			res += arr[p1] > arr[p2] ? M - p1 + 1 : 0;// 计算左边有多少个数比右边大
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];// 先移动左边
		}
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (int k = 0; k < help.length; k++) {
			arr[L + k] = help[k];
		}
		return res;
	}
}
