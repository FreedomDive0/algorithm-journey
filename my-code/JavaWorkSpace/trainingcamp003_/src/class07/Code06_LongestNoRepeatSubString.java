package class07;

import java.util.HashMap;

public class Code06_LongestNoRepeatSubString {
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
			dp[i] = Math.min(dp[i - 1] + 1, i - cIndex.getOrDefault(chs[i], -1));// 分析pre位置 每次只要选最小就能得到正确答案
			cIndex.put(chs[i], i);
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
