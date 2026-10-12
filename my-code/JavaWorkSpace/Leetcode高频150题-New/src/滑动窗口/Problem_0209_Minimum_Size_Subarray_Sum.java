package 滑动窗口;

public class Problem_0209_Minimum_Size_Subarray_Sum {
	public int minSubArrayLen(int target, int[] nums) {
		int l = 0;
		int r = 0;// [l,r]
		int sum = 0;
		int len = Integer.MAX_VALUE;
		while (r < nums.length) {
			sum += nums[r];// 将r++与求sum分开 避免出现nums.length-1刚加上就结束循环
			while (sum >= target) {
				len = Math.min(len, r - l + 1);
				sum -= nums[l++];
			}
			r++;
		}
		return len == Integer.MAX_VALUE ? 0 : len;
	}
//	public int minSubArrayLen(int target, int[] nums) {
//		int l = 0;
//		int r = 0;// [l,r)
//		int sum = 0;
//		int len = 0;
//		while (r < nums.length) {
//			while (sum >= target) {
//				len = len == 0 ? r - l : Math.min(len, r - l);
//				sum -= nums[l++];
//			}
//			sum += nums[r++];//将r++与求sum分开 避免出现nums.length-1刚加上就结束循环
//		}
//		return len;
//	}
}
