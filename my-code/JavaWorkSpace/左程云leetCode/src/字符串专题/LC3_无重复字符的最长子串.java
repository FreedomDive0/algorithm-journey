package 字符串专题;

public class LC3_无重复字符的最长子串 {
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("bbtablud"));
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s.length() < 2) {
			return s.length();
		}
		int i = 0, j = 1, max = 1;
		while (j < s.length()) {
			String c = String.valueOf(s.charAt(j));
			String tmp = s.substring(i, j);
			if (tmp.contains(c)) {
				i = tmp.indexOf(c) + i + 1;
			} else {
				max = Math.max(max, j - i + 1);
			}
			j++;
		}
		return max;
	}
}
