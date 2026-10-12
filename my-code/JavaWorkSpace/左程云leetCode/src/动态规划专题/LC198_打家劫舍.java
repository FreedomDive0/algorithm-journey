package 动态规划专题;

public class LC198_打家劫舍 {
	public int rob(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n + 1];
		dp[n] = 0;
		dp[n - 1] = nums[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
		}
		return Math.max(dp[0], dp[1]);// 反证法
	}

	int max = 0;

	public int process(int[] nums, int index, int num) {
		if (index >= nums.length) {
			max = Math.max(max, num);
			return 0;
		}
		return Math.max(process(nums, index + 1, num), process(nums, index + 2, num + nums[index]));
	}
}
