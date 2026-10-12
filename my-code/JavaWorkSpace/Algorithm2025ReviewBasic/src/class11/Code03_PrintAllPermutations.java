package class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {
	// 全排列
	public static ArrayList<String> permutation(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		processP(str.toCharArray(), 0, ans);
		return ans;
	}

	public static void processP(char[] str, int idx, List<String> ans) {
		if (idx == str.length) {
			ans.add(String.valueOf(str));
		} else {
			for (int j = idx; j < str.length; j++) {
				swap(str, idx, j);
				processP(str, idx + 1, ans);
				swap(str, idx, j);
			}
		}
	}

	public static void swap(char[] str, int i, int j) {
		char tmp = str[i];
		str[i] = str[j];
		str[j] = tmp;
	}

	// 全排列去重
	public static ArrayList<String> permutationNoRepeat(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		processPNR(str.toCharArray(), 0, ans);
		return ans;
	}

	public static void processPNR(char[] str, int idx, List<String> ans) {
		if (idx == str.length) {
			ans.add(String.valueOf(str));
		} else {
			boolean[] visited = new boolean[26];
			for (int j = idx; j < str.length; j++) {
				if (!visited[str[j] - 'a']) {
					visited[str[j] - 'a'] = true;
					swap(str, idx, j);
					processPNR(str, idx + 1, ans);
					swap(str, idx, j);
				}
			}
		}
	}

	public static void main(String[] args) {
		String s = "aac";
		List<String> ans1 = permutation(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutationNoRepeat(s);
		for (String str : ans2) {
			System.out.println(str);
		}

	}
}
