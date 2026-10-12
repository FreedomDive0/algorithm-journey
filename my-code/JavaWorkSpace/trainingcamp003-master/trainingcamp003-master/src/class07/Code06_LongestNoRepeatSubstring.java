package class07;

import java.util.HashMap;

public class Code06_LongestNoRepeatSubstring {

	public static int maxUnique(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		// map 替代了哈希表 假设字符的码是0~255
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = 0;
		int pre = -1;
		int cur = 0;
		for (int i = 0; i != chas.length; i++) {
			pre = Math.max(pre, map[chas[i]]);
			cur = i - pre;
			len = Math.max(len, cur);
			map[chas[i]] = i;
		}
		return len;
	}

	// for test
	public static String getRandomString(int len) {
		char[] str = new char[len];
		int base = 'a';
		int range = 'z' - 'a' + 1;
		for (int i = 0; i != len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + base);
		}
		return String.valueOf(str);
	}

	// for test
	public static String maxUniqueString(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		char[] chas = str.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = -1;
		int pre = -1;
		int cur = 0;
		int end = -1;
		for (int i = 0; i != chas.length; i++) {
			pre = Math.max(pre, map[chas[i]]);
			cur = i - pre;
			if (cur > len) {
				len = cur;
				end = i;
			}
			map[chas[i]] = i;
		}
		return str.substring(end - len + 1, end + 1);
	}

	// 测试
	public static int longNRSS(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		char[] chs = s.toCharArray();
		int N = chs.length;
		HashMap<Character, Integer> cIndex = new HashMap<>();
		int[] dp = new int[N];// 以i结尾最长无重复字符串长度
		int max = 0;
		dp[0] = 1;
		cIndex.put(chs[0], 0);
		for (int i = 1; i < N; i++) {
			dp[i] = Math.min(dp[i - 1] + 1, i - cIndex.getOrDefault(chs[i], -1));
			cIndex.put(chs[i], i);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		String str = getRandomString(20);
		System.out.println(str);
		System.out.println(maxUnique(str));
		System.out.println(maxUniqueString(str));
		System.out.println(longNRSS(str));
	}
}
