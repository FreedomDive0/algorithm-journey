package 数组字符串;

public class Problem_0028_Find_the_Index_of_the_First_Occurrence_in_a_String {
	// KMP
	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null || haystack.length() < needle.length()) {
			return -1;
		}
		if (needle.length() < 1) {
			return 0;
		}
		char[] s = haystack.toCharArray();
		char[] ms = needle.toCharArray();
		int[] next = getNextArr(ms);
		int indexS = 0;
		int indexM = 0;
		while (indexS < s.length && indexM < ms.length) {
			if (s[indexS] == ms[indexM]) {
				indexS++;
				indexM++;
			} else if (indexM > 0) {
				indexM = next[indexM];
			} else {
				indexS++;
			}
		}
		return indexM == ms.length ? indexS - indexM : -1;
	}

	public int[] getNextArr(char[] ms) {
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
				index++;
			}
		}
		return next;
	}
}
