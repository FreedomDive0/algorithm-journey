package 回溯法与深度优先搜索专题;

import java.util.ArrayList;
import java.util.List;

public class LC46_全排列 {

	public List<List<Integer>> permute(int[] nums) {
		process(nums, 0, new ArrayList<Integer>(), new boolean[nums.length]);
		return res;
	}

	List<List<Integer>> res = new ArrayList<List<Integer>>();

	public void process(int[] nums, int len, List<Integer> path, boolean[] visited) {
		if (len == nums.length) {
//			res.add(path);实际传递进去的是path的地址 递归过程中只有一个path 递归完成后path就为空了
			res.add(new ArrayList<Integer>(path));
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				path.add(nums[i]);
				process(nums, len + 1, path, visited);
				visited[i] = false;
				path.remove(path.size() - 1);
			}
		}
	}
}
