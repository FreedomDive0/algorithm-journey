package topinterviewquestions;

public class Problem_0028_ImplementStrStr {
	public static int strStr(String s, String m) {
		if (s == null || m == null || s.length() < m.length()) {
			return -1;
		}
		if (m.length() < 1) {
			return 0;
		}
		char[] match = m.toCharArray();
		char[] str = s.toCharArray();
		int[] next = getNextArray(match);
		int indexM = 0;
		int index = 0;
		while (index < s.length() && indexM < m.length()) {
			if (str[index] == match[indexM]) {
				index++;
				indexM++;
			} else if (indexM > 0) {
				indexM = next[indexM];
			} else {
				index++;
			}
		}
		return indexM == m.length() ? index - indexM : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int cn = 0;
		while (index < ms.length) {
			if (ms[index - 1] == ms[cn]) {
				next[index++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[index++] = 0;
			}
		}
		return next;
	}
}
