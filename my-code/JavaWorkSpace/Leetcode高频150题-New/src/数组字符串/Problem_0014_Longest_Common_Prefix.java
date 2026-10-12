package 数组字符串;

public class Problem_0014_Longest_Common_Prefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length < 1) {
			return "";
		}
		char[] chs = strs[0].toCharArray();
		int min = chs.length;
		for (int i = 1; i < chs.length; i++) {
			char[] tmp = strs[i].toCharArray();
			int index = 0;
			while (index < tmp.length && index < chs.length) {
				if (tmp[index] != chs[index]) {
					break;
				}
				index++;
			}
			min = Math.min(min, index);
			if (min == 0) {
				return "";
			}
		}
		return strs[0].substring(0, min + 1);
	}
}
