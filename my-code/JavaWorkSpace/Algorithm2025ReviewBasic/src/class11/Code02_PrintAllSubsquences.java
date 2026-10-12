package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code02_PrintAllSubsquences {
	// 全部子序列 包括重复
	public static List<String> subs(String s) {
		List<String> ans = new ArrayList<String>();
		processS(s.toCharArray(), 0, "", ans);
		return ans;
	}

	public static void processS(char[] str, int idx, String path, List<String> ans) {
		if (idx == str.length) {
			ans.add(path);
		} else {
			processS(str, idx + 1, path, ans);
			String yes = path + str[idx];
			processS(str, idx + 1, yes, ans);
		}
	}

	public static List<String> subsNoRepeat(String s) {
		HashSet<String> res = new HashSet<String>();
		processS(s.toCharArray(), 0, "", res);
		List<String> ans = new ArrayList<String>(res);
		return ans;
	}

	// 子序列去重
	public static void processS(char[] str, int idx, String path, HashSet<String> ans) {
		if (idx == str.length) {
			ans.add(path);
		} else {
			processS(str, idx + 1, path, ans);
			String yes = path + str[idx];
			processS(str, idx + 1, yes, ans);
		}
	}

	public static void main(String[] args) {
		String test = "aacc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}
}
