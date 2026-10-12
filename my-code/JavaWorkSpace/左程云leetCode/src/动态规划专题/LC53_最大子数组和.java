package 动态规划专题;

public class LC53_最大子数组和 {
	public int maxSubArray(int[] nums) {
		int max = nums[nums.length - 1];
		int sum = max;// 暴力递归是每个index之后位置的最大值
		for (int i = nums.length - 2; i >= 0; i--) {
			if (sum > 0) {
				sum += nums[i];
			} else {
				sum = nums[i];
			}
			max = Math.max(max, sum);
		}
		return max;
	}
}
