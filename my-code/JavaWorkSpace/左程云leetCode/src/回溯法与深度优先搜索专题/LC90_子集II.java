package 回溯法与深度优先搜索专题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC90_子集II {

	List<List<Integer>> res = new ArrayList<List<Integer>>();
	LinkedList<Integer> path = new LinkedList<Integer>();
	boolean[] visited;

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		visited = new boolean[nums.length];
		Arrays.sort(nums);
		process(nums, 0);
		return res;
	}

	public void process(int[] nums, int index) {
		res.add(new ArrayList<>(path));
		if (index >= nums.length) {
			return;
		}
		for (int i = index; i < nums.length; i++) {// 剪枝层剪枝
			if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {// !visited[i-1]同一层当前一个相同元素使用过后 之后就不可以使用了
				continue;
			}
			path.add(nums[i]);
			visited[i] = true;
			process(nums, i + 1);
			path.removeLast();
			visited[i] = false;
		}
	}
}
