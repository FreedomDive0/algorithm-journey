package 回溯法与深度优先搜索专题;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class LC47_全排列II {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(nums);// 排序方便剪枝
		process(nums, res, 0, new ArrayDeque(), new boolean[nums.length]);
		return res;
	}

	public void process(int[] nums, List<List<Integer>> res, int len, Deque<Integer> path, boolean[] visited) {
		if (len == nums.length) {
			res.add(new ArrayList<Integer>(path));
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
					continue;
				}
				path.add(nums[i]);
				visited[i] = true;
				process(nums, res, len + 1, path, visited);
				path.removeLast();
				visited[i] = false;
			}
		}
	}
}
