package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code02_PrintAllSubsquences {
	// 1.子序列全排列
	public static List<String> subs(String s) {
		ArrayList<String> ans = new ArrayList<String>();
		if (s == null) {
			return ans;
		}
		sProcess(s.toCharArray(), 0, "", ans);
		return ans;
	}

	public static void sProcess(char[] chs, int idx, String path, List<String> ans) {
		if (idx == chs.length) {
			ans.add(path);
			return;
		}
		sProcess(chs, idx + 1, path, ans);
		sProcess(chs, idx + 1, path + chs[idx], ans);
	}

	public static List<String> subsNoRepeat(String s) {
		ArrayList<String> ans = new ArrayList<String>();
		if (s == null) {
			return ans;
		}
		HashSet<String> set = new HashSet<String>();
		sNProcess(s.toCharArray(), 0, "", set);
		for (String str : set) {
			ans.add(str);
		}
		return ans;
	}

	public static void sNProcess(char[] chs, int idx, String path, HashSet<String> set) {
		if (idx == chs.length) {
			set.add(path);
			return;
		}
		sNProcess(chs, idx + 1, path, set);
		sNProcess(chs, idx + 1, path + chs[idx], set);
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
