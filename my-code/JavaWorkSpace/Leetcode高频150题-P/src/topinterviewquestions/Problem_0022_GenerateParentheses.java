package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

public class Problem_0022_GenerateParentheses {
	public static List<String> generateParenthesis(int n) {
		char[] path = new char[n << 1];
		List<String> res = new ArrayList<String>();
		process(path, res, n, 0, 0);
		return res;
	}

	public static void process(char[] path, List<String> ans, int leftRest, int leftOverRight, int index) {
		if (index == path.length) {
			ans.add(String.valueOf(path));
			return;
		}
		if (leftRest > 0) {
			path[index] = '(';
			process(path, ans, leftRest - 1, leftOverRight + 1, index + 1);
		}
		if (leftOverRight > 0) {
			path[index] = ')';
			process(path, ans, leftRest, leftOverRight - 1, index + 1);
		}
	}
}
