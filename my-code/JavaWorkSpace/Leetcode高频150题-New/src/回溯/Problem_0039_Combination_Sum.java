package 回溯;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Problem_0039_Combination_Sum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		process(0, new ArrayDeque<>(), 0, candidates, target, ans);
		return ans;
	}

	public void process(int sum, Deque<Integer> path, int index, int[] candidates, int target,
			List<List<Integer>> ans) {
		if (sum > target) {
			return;
		}
		if (sum == target) {
			ans.add(new ArrayList<Integer>(path));
			return;
		}
		if (index == candidates.length) {
			return;
		}
		for (int i = 0; i * candidates[index] + sum <= target; i++) {
			int times = 0;
			for (; times < i; times++) {
				path.addLast(candidates[index]);
			}
			process(sum + times * candidates[index], path, index + 1, candidates, target, ans);
			for (; times > 0; times--) {
				path.removeLast();
			}
		}
	}
}
