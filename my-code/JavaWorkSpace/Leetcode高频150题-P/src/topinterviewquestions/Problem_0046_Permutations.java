package topinterviewquestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Problem_0046_Permutations {
	// 交换法
	public static List<List<Integer>> permute1(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		process2(nums, 0, ans);
		return ans;
	}

	public static void process2(int[] nums, int i, List<List<Integer>> ans) {
		if (i == nums.length) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Integer num : nums) {
				arr.add(num);
			}
			ans.add(arr);
		} else {
			for (int j = i; j < nums.length; j++) {
				swap(nums, i, j);
				process2(nums, i + 1, ans);
				swap(nums, i, j);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 使用填充法
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		HashSet<Integer> rest = new HashSet<Integer>();
		for (Integer num : nums) {
			rest.add(num);
		}
		process1(rest, new ArrayList<Integer>(), ans);
		return ans;
	}

	public static void process1(HashSet<Integer> rest, ArrayList<Integer> path, List<List<Integer>> ans) {
		if (rest.isEmpty()) {
			ans.add(path);
		} else {
			for (int num : rest) {
				HashSet<Integer> clone = cloneExceptNum(rest, num);
				ArrayList<Integer> curPath = new ArrayList<Integer>(path);
				curPath.add(num);
				process1(clone, curPath, ans);
			}
		}
	}

	public static HashSet<Integer> cloneExceptNum(HashSet<Integer> set, int num) {
		HashSet<Integer> res = new HashSet<Integer>(set);
		res.remove(num);
		return res;
	}

}
