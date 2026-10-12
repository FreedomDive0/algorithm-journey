package 数组字符串;

public class Problem_0080_RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates(int[] nums) {
		return process(nums, 2);
	}

	public int process(int[] nums, int k) {
		int index = 0;
		for (int num : nums) {
			if (index < k || nums[index - k] != num) {// 前k个数直接保留 当前数与第index-k不同时加入
				nums[index++] = num;
			}
		}
		return 0;
	}
}
