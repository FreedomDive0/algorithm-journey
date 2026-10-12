package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code02_PrintAllSubsquences {
	public static ArrayList<String> printSubsquences(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		String path = "";
		ArrayList<String> ans = new ArrayList<String>();
		process1(str.toCharArray(), 0, path, ans);
		return ans;
	}

	public static void process1(char[] str, int index, String path, ArrayList<String> ans) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		String no = path;
		process1(str, index + 1, no, ans);
		String yes = path + String.valueOf(str[index]);
		process1(str, index + 1, yes, ans);
	}

	public static ArrayList<String> printNoRepeatSubsquences(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		String path = "";
		HashSet<String> set = new HashSet<String>();
		ArrayList<String> ans = new ArrayList<>();
		process2(str.toCharArray(), 0, path, set);
		for (String string : set) {
			ans.add(string);
		}
		return ans;
	}

	public static void process2(char[] str, int index, String path, HashSet<String> ans) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, no, ans);
		String yes = path + String.valueOf(str[index]);
		process2(str, index + 1, yes, ans);
	}

	// 复习
	public static List<String> SubString(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> res = new ArrayList<>();
		processSS(str.toCharArray(), 0, res, "");
		return res;
	}

	public static void processSS(char[] arr, int index, ArrayList<String> res, String cur) {
		if (index == arr.length) {
			res.add(cur);
			return;
		}
		processSS(arr, index + 1, res, cur);
		processSS(arr, index + 1, res, cur + String.valueOf(arr[index]));
	}

	public static void main(String[] args) {
		String test = "aacc";
		List<String> ans1 = printSubsquences(test);
		List<String> ans2 = printNoRepeatSubsquences(test);
		List<String> ans3 = SubString(test);
		for (String str : ans1) {
			System.out.print(str + " ");
		}
		System.out.println("=================");
		for (String str : ans3) {
			System.out.print(str + " ");
		}
		System.out.println("=================");

	}
}
