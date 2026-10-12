package 回溯法与深度优先搜索专题;

import java.util.ArrayList;
import java.util.List;

public class LC78_子集 {
	public List<List<Integer>> subsets(int[] nums) {
		process(nums, 0, new ArrayList<Integer>());
		return res;
	}

	List<List<Integer>> res = new ArrayList<List<Integer>>();

	public void process(int[] nums, int index, ArrayList<Integer> path) {
		res.add(new ArrayList<Integer>(path));// 从每个值向后遍历 直接记录
		for (int j = index; j < nums.length; j++) {
			path.add(nums[j]);
			process(nums, j + 1, path);
			path.remove(path.size() - 1);
		}
	}
}
