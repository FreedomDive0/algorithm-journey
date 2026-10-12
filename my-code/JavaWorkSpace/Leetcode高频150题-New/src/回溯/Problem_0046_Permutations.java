package 回溯;

import java.util.ArrayList;
import java.util.List;

public class Problem_0046_Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		process(nums, 0, ans);
		return ans;
	}

	public void process(int[] nums, int index, List<List<Integer>> ans) {
		if (index == nums.length) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < nums.length; i++) {
				list.add(nums[i]);
			}
			ans.add(list);
		} else {
			for (int i = index; i < nums.length; i++) {
				swap(nums, index, i);
				process(nums, index + 1, ans);
				swap(nums, index, i);
			}
		}
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
