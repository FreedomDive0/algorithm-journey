package 数组字符串;

public class Problem_0055_Jump_Game {
	public boolean canJump(int[] nums) {
		int range = nums[0];
		for (int i = 1; i < nums.length && range >= i; i++) {
			range = Math.max(range, nums[i] + i);
		}
		return range >= nums.length - 1;
	}
}
