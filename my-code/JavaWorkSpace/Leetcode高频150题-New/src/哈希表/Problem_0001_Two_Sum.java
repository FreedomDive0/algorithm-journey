package 哈希表;

import java.util.HashMap;

public class Problem_0001_Two_Sum {
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] ans = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				ans[0] = i;
				ans[1] = map.get(target - nums[i]);
				return ans;
			}
			map.put(nums[i], i);
		}
		return ans;
	}
}
