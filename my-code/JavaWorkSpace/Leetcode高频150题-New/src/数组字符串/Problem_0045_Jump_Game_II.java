package 数组字符串;

public class Problem_0045_Jump_Game_II {
	public int jump(int[] nums) {
		int cur = 0;
		int next = nums[0];
		int steps = 0;
		for (int i = 0; i < nums.length; i++) {
			if (cur < i) {
				steps++;
				cur = next;
			}
			next = Math.max(next, i + nums[i]);
		}
		return steps;
	}
}
