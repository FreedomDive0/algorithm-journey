package 回溯;

import java.util.ArrayList;
import java.util.List;

public class Problem_0022_GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<String>();
		process(n, 0, 0, n << 1, new char[n << 1], ans);
		return ans;
	}

	public void process(int leftRest, int leftMore, int index, int n, char[] path, List<String> ans) {
		if (index == n) {
			ans.add(String.valueOf(path));
			return;
		}
		if (leftRest > 0) {
			path[index] = '(';
			process(leftRest - 1, leftMore + 1, index + 1, n, path, ans);
		}
		if (leftMore > 0) {
			path[index] = ')';
			process(leftRest, leftMore - 1, index + 1, n, path, ans);
		}
	}
}
