package 回溯法与深度优先搜索专题;

import java.util.ArrayList;
import java.util.List;

public class LC17_电话号码的字母组合 {
	public static void main(String[] args) {

	}

	String[] letter_map = { " ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
	List<String> res = new ArrayList<String>();

	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			return res;
		}
		process(digits, 0, new StringBuilder());
		return res;
	}

	// StringBuilder拼接效率远高于String+String
	public void process(String digits, int index, StringBuilder tmp) {
		if (index == digits.length()) {
			res.add(tmp.toString());// 拼接到最后一位直接添加
			return;
		}
		int pos = digits.charAt(index) - '0';// 字符变int -'0'
		String map_String = letter_map[pos];
		for (int i = 0; i < letter_map[pos].length(); i++) {
			tmp.append(map_String.charAt(i));
			process(digits, index + 1, tmp);
			tmp.deleteCharAt(tmp.length() - 1);
		}
	}
}
