package class09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Code01_LowestLexicography {
	// 获取全排列 挨个比大小
	public static String lowestLexicography(String[] strs) {
		if (strs == null || strs.length < 1) {
			return null;
		}
		ArrayList<String> ans = new ArrayList<String>();
		getFullRange(strs, "", new HashSet<Integer>(), ans);
		String minStr = ans.get(0);
		for (int i = 1; i < ans.size(); i++) {
			if (ans.get(i).compareTo(minStr) < 0) {
				minStr = ans.get(i);
			}
		}
		return minStr;
	}

	public static void getFullRange(String[] strs, String path, HashSet<Integer> useSet, ArrayList<String> ans) {
		if (useSet.size() == strs.length) {
			ans.add(path);
		} else {
			for (int i = 0; i < strs.length; i++) {
				if (!useSet.contains(i)) {
					useSet.add(i);
					getFullRange(strs, path + strs[i], useSet, ans);
					useSet.remove(i);
				}
			}
		}
	}

	// 贪心法 找到字符串拼接的实质
	public static String lowestLexicography1(String[] strs) {
		if (strs == null || strs.length < 1) {
			return null;
		}
		Arrays.sort(strs, new JoinCom());
		String res = "";
		for (String str : strs) {
			res += str;
		}
		return res;
	}

	public static class JoinCom implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return (o1 + o2).compareTo(o2 + o1);
		}
	}

}
