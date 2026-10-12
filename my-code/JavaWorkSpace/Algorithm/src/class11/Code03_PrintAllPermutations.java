package class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code03_PrintAllPermutations {
	public static ArrayList<String> permutations(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process1(chs, 0, res);
		return res;
	}

	public static void process1(char[] str, int index, ArrayList<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
			return;
		}
		// 每个index 之后的下标都可以交换
		for (int i = index; i < str.length; i++) {
			swap(str, i, index);
			process1(str, index + 1, ans);
			swap(str, index, i);// 完成一次还原再继续
		}
	}

	public static void swap(char[] str, int i, int j) {
		char tmp = str[i];
		str[i] = str[j];
		str[j] = tmp;
	}

	public static ArrayList<String> noRepeatPermutations1(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		HashSet<String> set = new HashSet<String>();
		char[] chs = str.toCharArray();
		process2(chs, 0, set);
		for (String string : set) {
			res.add(string);
		}
		return res;
	}

	public static void process2(char[] chs, int i, HashSet<String> set) {
		if (i == chs.length) {
			set.add(String.valueOf(chs));
			return;
		}
		for (int j = i; j < chs.length; j++) {
			swap(chs, i, j);
			process2(chs, i + 1, set);
			swap(chs, i, j);
		}
	}

	public static ArrayList<String> noRepeatPermutations2(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process3(chs, 0, res);
		return res;
	}

	public static void process3(char[] chs, int i, ArrayList<String> res) {
		if (i == chs.length) {
			res.add(String.valueOf(chs));
			return;
		}
		boolean[] isSelect = new boolean[26];
		for (int j = i; j < chs.length; j++) {
			if (!isSelect[chs[j] - 'a']) {// 判断是否有相同字符来过该位置 分支限界
				isSelect[chs[j] - 'a'] = true;
				swap(chs, i, j);
				process3(chs, i + 1, res);
				swap(chs, i, j);
			}

		}
	}

	// 复习
	public static List<String> AllPer(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> res = new ArrayList<>();
		processAP(str.toCharArray(), 0, res);
		return res;
	}

	public static void processAP(char[] arr, int index, ArrayList<String> res) {
		if (index == arr.length) {
			res.add(String.valueOf(arr));
			return;
		}
		for (int i = index; i < arr.length; i++) {
			swap(arr, index, i);
			processAP(arr, index + 1, res);
			swap(arr, index, i);
		}
	}

	// 6.全排列无重复
	public static List<String> AllPerNoRepeat(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> res = new ArrayList<>();
		processAPNP(str.toCharArray(), 0, res);
		return res;
	}

	public static void processAPNP(char[] arr, int index, ArrayList<String> res) {
		if (index == arr.length) {
			res.add(String.valueOf(arr));
			return;
		}
		boolean[] visit = new boolean[26];// 保证每个位置不出现重复值
		for (int i = index; i < arr.length; i++) {
			if (!visit[arr[i] - 'a']) {
				visit[arr[i] - 'a'] = true;
				swap(arr, index, i);
				processAPNP(arr, index + 1, res);
				swap(arr, index, i);
			}
		}
	}

	public static void main(String[] args) {
		String s = "aac";
		List<String> ans1 = permutations(s);
//		for (String str : ans1) {
//			System.out.println(str);
//		}
//		System.out.println("=======");
//		List<String> ans11 = AllPer(s);
//		for (String str : ans11) {
//			System.out.println(str);
//		}
//		System.out.println("=======");
		List<String> ans2 = noRepeatPermutations1(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans3 = AllPerNoRepeat(s);
		for (String str : ans3) {
			System.out.println(str);
		}
	}
}
