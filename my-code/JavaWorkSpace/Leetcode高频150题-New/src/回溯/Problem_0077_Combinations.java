package 回溯;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Problem_0077_Combinations {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		Deque<Integer> path = new ArrayDeque<>();
		process(1, path, n, k, ans);
		return ans;
	}

	public void process(int begin, Deque<Integer> path, int n, int k, List<List<Integer>> ans) {
		if (path.size() == k) {
			ans.add(new ArrayList<>(path));
		} else {
			for (int num = begin; num <= n; num++) {
				path.addLast(num);
				process(num + 1, path, n, k, ans);
				path.removeLast();
			}
		}
	}
}
