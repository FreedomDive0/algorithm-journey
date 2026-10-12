package 基本数据类型专题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC187_重复的DNA序列 {
	int L = 10;

	public List<String> findRepeatedDnaSequences(String s) {
		List<String> res = new ArrayList<String>();
		if (s.length() < 10) {
			return res;
		}
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		int n = s.length();
		for (int i = 0; i <= n - L; i++) {
			String sub = s.substring(i, i + L);
			count.put(sub, count.getOrDefault(sub, 0) + 1);
			if (count.get(sub) == 2) {
				res.add(sub);
			}
		}
		return res;
	}
}
