package 哈希表;

import java.util.HashMap;

public class Problem_0219_Contains_Duplicate_II {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int l = 0;
		int r = 0;
		while (r < nums.length) {
			while (r <= l + k && r < nums.length) {
				if (!map.containsKey(nums[r])) {
					map.put(nums[r], r);
				} else {
					return true;
				}
				r++;
			}
			map.remove(nums[l++]);
		}
		return false;
	}
}
