package class09;

import java.util.HashSet;

public class Code02_lights {
	public static int lights1(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0, new HashSet<Integer>());
	}

	public static int process(char[] str, int index, HashSet<Integer> lightsIndex) {
		if (index == str.length) {// index走到了最后 判断是否符合条件
			for (int i = 0; i < str.length; i++) {
				if (str[i] == '.') {
					if (!lightsIndex.contains(i - 1) && !lightsIndex.contains(i) && !lightsIndex.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lightsIndex.size();
		} else {
//		yes,no 是进行完点灯操作后 判断是否成立返回的数值
			int no = process(str, index + 1, lightsIndex);// 先全不点
			int yes = Integer.MAX_VALUE;
			if (str[index] == '.') {
				lightsIndex.add(index);
				yes = process(str, index + 1, lightsIndex);
				lightsIndex.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int lights2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] s = str.toCharArray();
		int index = 0;
		int lights = 0;
		while (index < s.length) {
			if (s[index] == '.') {
				lights++;
				if (index + 1 == s.length) {
					break;
				} else {
					if (s[index + 1] == 'X') {
						index += 2;
					} else {
						index += 3;
					}
				}
			} else {
				index++;
			}
		}
		return lights;
	}

	// 复习
	public static int lightB(String str) {
		if (str == null) {
			return 0;
		}
		return processL(str.toCharArray(), 0, new HashSet<Integer>());
	}

	public static int processL(char[] arr, int index, HashSet<Integer> lights) {
		if (index == arr.length) {
			return isOk(arr, lights) ? lights.size() : arr.length;
		}
		int no = processL(arr, index + 1, lights);
		int yes = arr.length;
		if (arr[index] == '.') {
			lights.add(index);
			yes = processL(arr, index + 1, lights);
			lights.remove(index);
		}
		return Math.min(no, yes);
	}

	public static boolean isOk(char[] arr, HashSet<Integer> lights) {
		boolean ok = true;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '.' && !lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
				ok = false;
			}
		}
		return ok;
	}

	// 贪心
	public static int lightsT(String str) {
		if (str == null) {
			return 0;
		}
		char[] sArr = str.toCharArray();
		int res = 0;
		int index = 0;
		while (index < str.length()) {
			if (sArr[index] == '.') {
				res++;
				if (index + 1 == str.length()) {
					break;
				}
				if (sArr[index + 1] == '.') {
					index = index + 3;
				} else {
					index = index + 2;
				}
			} else {
				index++;
			}
		}
		return res;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = lightsT(test);
			int ans2 = lights2(test);
			if (ans1 != ans2) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
