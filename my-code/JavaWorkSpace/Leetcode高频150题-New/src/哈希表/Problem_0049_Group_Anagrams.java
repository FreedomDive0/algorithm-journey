package 哈希表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Problem_0049_Group_Anagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			char[] c = str.toCharArray();
			Arrays.sort(c);
			String key = String.valueOf(c);
			List<String> list = map.getOrDefault(key, new ArrayList<>());
			list.add(str);
			map.put(key, list);
		}
		return new ArrayList<List<String>>(map.values());
	}
	// 超时了
//	public List<List<String>> groupAnagrams(String[] strs) {
//		List<List<String>> ans = new ArrayList<>();
//		boolean[] used = new boolean[strs.length];
//		for (int i = 0; i < strs.length; i++) {
//			if (used[i]) {
//				continue;
//			}
//			int[] map = new int[128];
//			int len = strs[i].length();
//			int all = len;
//			for (int index = 0; index < len; index++) {
//				map[strs[i].charAt(index)]++;
//			}
//			used[i] = true;
//			ArrayList<String> tmp = new ArrayList<String>();
//			tmp.add(strs[i]);
//			for (int j = i + 1; j < strs.length; j++) {
//				int[] tempMap = Arrays.copyOf(map, map.length);
//				if (strs[j].length() != len || used[j]) {
//					continue;
//				}
//				for (int index = 0; index < len; index++) {
//					tempMap[strs[j].charAt(index)]--;
//					if (tempMap[strs[j].charAt(index)] >= 0) {
//						all--;
//					}
//				}
//				if (all == 0) {
//					tmp.add(strs[j]);
//					used[j] = true;
//				}
//				all = len;
//			}
//			ans.add(tmp);
//		}
//		return ans;
//	}
}
