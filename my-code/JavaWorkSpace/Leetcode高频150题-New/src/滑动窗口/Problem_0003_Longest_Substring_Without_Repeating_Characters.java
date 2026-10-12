package 滑动窗口;

public class Problem_0003_Longest_Substring_Without_Repeating_Characters {
	public int lengthOfLongestSubstring(String s) {
		int[] map = new int[128];
		int start = -1;// [start,i] ∴未出现字符map中为-1
		int len = 0;
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			start = Math.max(start, map[s.charAt(i)] - 1);
			len = i - start;
			map[s.charAt(i)] = i + 1;// 0下标不使用
			res = Math.max(res, len);
		}
		return res;
	}

}
