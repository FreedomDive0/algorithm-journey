package class09;

import java.util.HashSet;

public class Code02_Light {

	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	// str[index....]位置，自由选择放灯还是不放灯
	// str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
	// 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) { // 结束的时候
			for (int i = 0; i < str.length; i++) {
				if (str[i] != 'X') { // 当前位置是点的话
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else { // str还没结束
			// i X .
			int no = process(str, index + 1, lights);
			int yes = Integer.MAX_VALUE;
			if (str[index] == '.') {
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int minLight2(String road) {
		char[] str = road.toCharArray();
		int index = 0;
		int light = 0;
		while (index < str.length) {
			if (str[index] == 'X') {
				index++;
			} else { // i -> .
				light++;
				if (index + 1 == str.length) {
					break;
				} else {
					if (str[index + 1] == 'X') {
						index = index + 2;
					} else {
						index = index + 3;
					}
				}
			}
		}
		return light;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	// 复习2026
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

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLightsGreedy(test);
			int ans2 = minLight2(test);
			if (ans1 != ans2) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
