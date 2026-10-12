package 回溯;

import java.util.ArrayList;
import java.util.List;

public class Problem_0017_LetterCombinationsOfAPhoneNumber {
	public static char[][] phone = new char[][] { { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
			{ 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

	public static List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<String>();
		if (digits == null || digits.length() < 1) {
			return ans;
		}
		char[] strs = digits.toCharArray();
		char[] path = new char[strs.length];
		process(digits.toCharArray(), 0, path, ans);
		return ans;
	}

	public static void process(char[] strs, int index, char[] path, List<String> ans) {
		if (index == strs.length) {
			ans.add(String.valueOf(path));
			return;
		}
		for (char cur : phone[strs[index] - '2']) {
			path[index] = cur;// index不可以++ 每循环一次都变大
			process(strs, index + 1, path, ans);
		}
	}
}
