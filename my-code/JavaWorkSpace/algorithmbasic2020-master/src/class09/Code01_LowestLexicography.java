package class09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Code01_LowestLexicography {

	public static String lowestString1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		ArrayList<String> all = new ArrayList<>();
		HashSet<Integer> use = new HashSet<>();
		process(strs, use, "", all);
		String lowest = all.get(0);
		for (int i = 1; i < all.size(); i++) {
			if (all.get(i).compareTo(lowest) < 0) {
				lowest = all.get(i);
			}
		}
		return lowest;
	}

	// strs里放着所有的字符串
	// 已经使用过的字符串的下标，在use里登记了，不要再使用了
	// 之前使用过的字符串，拼接成了-> path
	// 用all收集所有可能的拼接结果
	public static void process(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all) {
		if (use.size() == strs.length) {
			all.add(path);
		} else {
			for (int i = 0; i < strs.length; i++) {
				if (!use.contains(i)) {
					use.add(i);
					process(strs, use, path + strs[i], all);
					use.remove(i);
				}
			}
		}
	}

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 5);
			ans[i] = (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	// for test
	public static String[] copyStringArray(String[] arr) {
		String[] ans = new String[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = String.valueOf(arr[i]);
		}
		return ans;
	}

	// 全排列
	public static String minD(String[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		ArrayList<String> all = new ArrayList<>();
		process(arr, new HashSet<>(), "", all);
		String min = all.get(0);
		for (String str : all) {
			min = min.compareTo(str) < 0 ? min : str;
		}
		return min;
	}

	public static void process1(String[] arr, HashSet<Integer> set, String str, ArrayList<String> all) {
		if (set.size() == arr.length) {
			all.add(str.toString());
		}
		for (int i = 0; i < arr.length; i++) {
			set.add(i);
			process1(arr, set, str + arr[i], all);
			set.remove(i);
		}
	}

	// 测试2026
	// 获取全排列 挨个比大小
	public static String lowestLexicograhy(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		List<String> all = new ArrayList<String>();
		getAllArray(strs, new HashSet<Integer>(), "", all);
		String lowest = all.get(0);
		for (int i = 1; i < all.size(); i++) {
			if (lowest.compareTo(all.get(i)) > 0) {
				lowest = all.get(i);
			}
		}
		return lowest;
	}

	public static void getAllArray(String[] strs, HashSet<Integer> idxMap, String path, List<String> ans) {
		if (idxMap.size() == strs.length) {
			ans.add(path);// String是不可变对象所以每次是创建新的
		} else {
			for (int i = 0; i < strs.length; i++) {
				if (!idxMap.contains(i)) {
					idxMap.add(i);
					getAllArray(strs, idxMap, path + strs[i], ans);
					idxMap.remove(i);
				}
			}
		}
	}

	public static class SplicingCom implements Comparator<String> {// 拼接序

		@Override
		public int compare(String o1, String o2) {
			return (o1 + o2).compareTo(o2 + o1);
		}

	}

	public static String lowestLexicograhy1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new SplicingCom());
		String lowest = "";
		for (int i = 0; i < strs.length; i++) {
			lowest += strs[i];
		}
		return lowest;
	}

	public static void main(String[] args) {
		int arrLen = 6;
		int strLen = 5;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			String[] arr1 = generateRandomStringArray(arrLen, strLen);
			String[] arr2 = copyStringArray(arr1);
			if (!lowestString1(arr1).equals(lowestLexicograhy(arr2))) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
