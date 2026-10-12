package class09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Code01_LowestLexicography {
	// 返回最小字典序排序
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
}
