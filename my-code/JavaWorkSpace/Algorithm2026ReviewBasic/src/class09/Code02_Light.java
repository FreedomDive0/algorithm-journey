package class09;

import java.util.HashSet;

public class Code02_Light {
	// 1.求最少灯的数量全排列
	public static int minLightPermutation(String road) {
		if (road == null || road.length() < 1) {
			return 0;
		}
		return mProcess(road.toCharArray(), 0, new HashSet<Integer>());
	}

	public static int mProcess(char[] road, int idx, HashSet<Integer> lightIdx) {
		if (idx == road.length) {
			for (int i = 0; i < road.length; i++) {
				if (road[i] != 'X') {
					if (!lightIdx.contains(i - 1) && !lightIdx.contains(i) && !lightIdx.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lightIdx.size();
		} else {
			int no = mProcess(road, idx + 1, lightIdx);
			int yes = Integer.MAX_VALUE;
			if (road[idx] == '.') {
				lightIdx.add(idx);
				yes = mProcess(road, idx + 1, lightIdx);
				lightIdx.remove(idx);// 会污染其他分支需要清除
			}
			return Math.min(no, yes);
		}
	}

	// 2.求最少灯的数量贪心
	public static int minLightsGreedy(String road) {
		if (road == null || road.length() < 1) {
			return 0;
		}
		char[] roads = road.toCharArray();
		int idx = 0;
		int lights = 0;
		while (idx < roads.length) {
			if (roads[idx] == 'X') {
				idx++;
			} else {
				lights++;
				if (idx + 1 >= roads.length) {
					break;
				}
				if (roads[idx + 1] == 'X') {
					idx = idx + 2;
				} else {
					idx = idx + 3;
				}
			}
		}
		return lights;
	}
}
