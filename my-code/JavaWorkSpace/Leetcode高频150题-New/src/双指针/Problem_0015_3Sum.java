package 双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0015_3Sum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || nums[i] != nums[i - 1]) {
				List<List<Integer>> next = twoSum(nums, i + 1, -nums[i]);
				for (List<Integer> list : next) {
					list.add(0, nums[i]);
					res.add(list);
				}
			}
		}
		return res;
	}

	public List<List<Integer>> twoSum(int[] nums, int begin, int target) {
		List<List<Integer>> res = new ArrayList<>();
		int l = begin, r = nums.length - 1;
		while (l < r) {
			if (nums[l] + nums[r] > target) {
				r--;
			} else if (nums[l] + nums[r] < target) {
				l++;
			} else {
				if (l == begin || nums[l] != nums[l - 1]) {
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(nums[l]);
					tmp.add(nums[r]);
					res.add(tmp);
				}
				l++;
			}
		}
		return res;
	}
}
