package class04;

public class Code06_SubArrayMaxSum {
	public static int maxSubSum(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;// 相当于虚拟数组 小于0的前缀舍弃即可
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = cur > max ? cur : max;
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}
}
