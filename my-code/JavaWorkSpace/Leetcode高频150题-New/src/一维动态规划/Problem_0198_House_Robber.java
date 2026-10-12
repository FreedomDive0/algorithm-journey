package 一维动态规划;

public class Problem_0198_House_Robber {
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
		}
		return dp[nums.length - 1];
	}

	public int rob1(int[] nums) {
		return process(nums, nums.length - 1, 0);
	}

	public int process(int[] nums, int index, int money) {
		if (index == 0) {
			return money + nums[0];
		}
		if (index < 0) {
			return money;
		}
		int no = process(nums, index - 1, money);
		int yes = process(nums, index - 2, money + nums[index]);
		return Math.max(no, yes);
	}
}
