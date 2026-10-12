package topinterviewquestions;

public class Problem_0014_LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length < 1) {
			return "";
		}
		char[] str = strs[0].toCharArray();
		int min = str.length;
		for (int i = 1; i < strs.length; i++) {
			char[] tmp = strs[i].toCharArray();
			int index = 0;
			while (index < str.length && index < tmp.length) {
				if (str[index] != tmp[index]) {
					break;
				}
				index++;
			}
			if (min == 0) {
				return "";
			}
			min = Math.min(min, index);
		}
		return strs[0].substring(0, min);
	}

	public static void main(String[] args) {
		String s = "983046";
		System.out.println(":" + s.substring(0, 0));
	}
}
