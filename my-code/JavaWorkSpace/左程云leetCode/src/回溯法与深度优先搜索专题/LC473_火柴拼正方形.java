package 回溯法与深度优先搜索专题;

import java.util.Arrays;

public class LC473_火柴拼正方形 {
	public boolean makesquare(int[] matchsticks) {
		int len = 0;
		for (int i = 0; i < matchsticks.length; i++) {
			len += matchsticks[i];
		}
		if (len % 4 != 0) {
			return false;
		}
		Arrays.sort(matchsticks);
		int[] ms = new int[matchsticks.length];
		int index = 0;
		for (int i = matchsticks.length - 1; i >= 0; i--) {
			ms[index++] = matchsticks[i];
		}
		len /= 4;
		int[] nums = new int[4];
		return process(ms, 0, nums, 4, len);

	}

	public boolean process(int[] matchsticks, int index, int[] nums, int k, int target) {
		if (index == matchsticks.length) {
			return true;// 因为所有火柴都已按要求分配了 所以肯定符合条件
		}
		for (int i = 0; i < k; i++) {
			if (nums[i] + matchsticks[index] > target) {// 剪枝
				continue;
			}
			if (i > 0 && nums[i] == nums[i - 1]) {// 当前桶和前一个桶相同 当前桶不需要放该数字
				continue;
			}
			if (i > 0 && index == 0) {// 第一个元素放在任何桶都等效 所以规定只放到第一个桶里
				break;
			}
			nums[i] += matchsticks[index];
			if (process(matchsticks, index + 1, nums, k, target)) {
				return true;
			}
			nums[i] -= matchsticks[index];
		}
		return false;
	}

}
