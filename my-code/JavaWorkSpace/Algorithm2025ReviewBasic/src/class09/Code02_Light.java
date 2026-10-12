package class09;

import java.util.HashSet;

public class Code02_Light {

	// 全排列找少的灯
	public static int light1(String road) {
		if (road == null || road.length() < 1) {
			return 0;
		}
		char[] str = road.toCharArray();
		return processL(str, 0, new HashSet<Integer>());
	}

	public static int processL(char[] chs, int idx, HashSet<Integer> lightMap) {
		if (idx == chs.length) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] != 'X') {
					if (!lightMap.contains(i - 1) && !lightMap.contains(i) && !lightMap.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lightMap.size();
		}
		int no = processL(chs, idx + 1, lightMap);
		int yes = Integer.MAX_VALUE;
		if (chs[idx] == '.') {// 能放灯时才可以放
			lightMap.add(idx);
			yes = processL(chs, idx + 1, lightMap);
			lightMap.remove(idx);
		}
		return Math.min(no, yes);
	}

	// 贪心法
	public static int light2(String road) {
		if (road == null || road.length() < 1) {
			return 0;
		}
		char[] str = road.toCharArray();
		int idx = 0;
		int light = 0;
		int length = road.length();
		while (idx < length) {
			if (str[idx] == '.') {
				light++;
				if (idx + 1 < length) {
					if (str[idx + 1] == '.') {
						idx = idx + 3;
					} else {
						idx = idx + 2;
					}
				}
			} else {
				idx++;
			}
		}
		return light;
	}
}
