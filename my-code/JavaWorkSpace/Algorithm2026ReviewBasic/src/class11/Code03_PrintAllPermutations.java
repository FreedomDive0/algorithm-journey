package class11;

import java.util.ArrayList;
import java.util.HashSet;

public class Code03_PrintAllPermutations {
	// 全排列
	public static ArrayList<String> permutation(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		if (str == null) {
			return ans;
		}
		pProcess(str.toCharArray(), new HashSet<Integer>(), "", ans);
		return ans;
	}

	public static void pProcess(char[] chs, HashSet<Integer> idxSet, String path, ArrayList<String> ans) {
		if (path.length() == chs.length) {
			ans.add(path);
			return;
		}
		for (int i = 0; i < chs.length; i++) {
			if (!idxSet.contains(i)) {
				idxSet.add(i);
				pProcess(chs, idxSet, path + chs[i], ans);
				idxSet.remove(i);
			}
		}
	}

	public static ArrayList<String> permutationS(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		if (str == null) {
			return ans;
		}
		pSProcess(str.toCharArray(), 0, ans);
		return ans;
	}

	// 交换法
	public static void pSProcess(char[] chs, int idx, ArrayList<String> ans) {
		if (idx == chs.length) {
			ans.add(new String(chs));
			return;
		}
		for (int i = idx; i < chs.length; i++) {
			swap(chs, idx, i);
			pSProcess(chs, idx + 1, ans);
			swap(chs, idx, i);
		}

	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static ArrayList<String> permutationNoRepeat(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		if (str == null) {
			return ans;
		}
		pNProcess(str.toCharArray(), new HashSet<Integer>(), "", ans);
		return ans;
	}

	public static void pNProcess(char[] chs, HashSet<Integer> idxSet, String path, ArrayList<String> ans) {
		if (path.length() == chs.length) {
			ans.add(path);
			return;
		}
		boolean[] used = new boolean[26];
		for (int i = 0; i < chs.length; i++) {
			if (!idxSet.contains(i) && !used[chs[i] - 'a']) {
				used[chs[i] - 'a'] = true;
				idxSet.add(i);
				pNProcess(chs, idxSet, path + chs[i], ans);
				idxSet.remove(i);
			}
		}
	}

	public static ArrayList<String> permutationSNoReapeat(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		if (str == null) {
			return ans;
		}
		pSNProcess(str.toCharArray(), 0, ans);
		return ans;
	}

	// 交换法
	public static void pSNProcess(char[] chs, int idx, ArrayList<String> ans) {
		if (idx == chs.length) {
			ans.add(new String(chs));
			return;
		}
		boolean[] used = new boolean[26];
		for (int i = idx; i < chs.length; i++) {
			if (!used[chs[i] - 'a']) {
				used[chs[i] - 'a'] = true;
				swap(chs, idx, i);
				pSNProcess(chs, idx + 1, ans);
				swap(chs, idx, i);
			}
		}
	}
}
