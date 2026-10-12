package 回溯法与深度优先搜索专题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC216_组合总和III {
	public static void main(String[] args) {
		combinationSum3(3, 7);
	}

	public static List<List<Integer>> combinationSum3(int k, int n) {
		process(k, n, 0);
		return res;
	}

	static List<List<Integer>> res = new ArrayList<List<Integer>>();
	static LinkedList<Integer> path = new LinkedList<Integer>();
	static int nums = 10;

	public static void process(int k, int n, int index) {
		if (n == 0) {
			res.add(new ArrayList<Integer>(path));
			return;
		}
		if (k == 0 || n < 0 || index == nums) {
			return;
		}
		process(k, n, index + 1);
		path.add(index);
		process(k - 1, n - index, index + 1);
		path.removeLast();
	}
}
