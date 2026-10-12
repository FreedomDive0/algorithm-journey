package 滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem_0030_Substring_with_Concatenation_of_All_Words {
	public List<Integer> findSubstring(String s, String[] words) {
		int n = s.length(), m = words.length, w = words[0].length();
		HashMap<String, Integer> map = new HashMap<String, Integer>();// 单词的map
		for (String str : words) {
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < w; i++) {// i从0~w-1就可以涵盖 字符串能形成的全部单词 i==w时删除i==0时第一个单词即可
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			for (int j = i; j + w <= n; j += w) {// 添加从i开始的全部单词 进行判断
				String cur = s.substring(j, j + w);
				tmp.put(cur, tmp.getOrDefault(cur, 0) + 1);
				if (j >= i + (m * w)) {// j到了下一个窗口开始位置 [隐性的使用了滑动窗口]
					int preIdx = j - w * m;// 删除单词
					String pre = s.substring(preIdx, preIdx + w);
					if (tmp.get(pre) == 1) {
						tmp.remove(pre);
					} else {
						tmp.put(pre, tmp.get(pre) - 1);
					}
					if (!tmp.getOrDefault(pre, 0).equals(map.getOrDefault(pre, 0))) {// 小加速 如果删除单词后 两表对应位置不同 即量表不可能相同
						continue;
					}
				}
				if (!tmp.getOrDefault(cur, 0).equals(map.getOrDefault(cur, 0))) {// 小加速同理
					continue;
				}
				if (tmp.equals(map)) {// 两表相同即收集答案
					ans.add(j - (m - 1) * w);
				}
			}
		}
		return ans;
	}
}
