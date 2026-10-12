package topinterviewquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0015_3Sum {
	public static List<List<Integer>> threeSum1(int[] nums) {
		if (nums == null || nums.length < 3) {
			return null;
		}
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || nums[i] != nums[i - 1]) {
				List<List<Integer>> res = twoSum(nums, i + 1, -nums[i]);
				for (List<Integer> cur : res) {
					cur.add(nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	public static List<List<Integer>> twoSum(int[] nums, int s, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		int l = s;
		int r = nums.length - 1;
		while (l < r) {
			if (nums[l] + nums[r] < target) {
				l++;
			} else if (nums[l] + nums[r] > target) {
				r--;
			} else {
				if (l == s || nums[l] != nums[l - 1]) {
					List<Integer> cur = new ArrayList<Integer>();
					cur.add(nums[l]);
					cur.add(nums[r]);
					ans.add(cur);
				}
				l++;
			}
		}
		return ans;
	}
}
