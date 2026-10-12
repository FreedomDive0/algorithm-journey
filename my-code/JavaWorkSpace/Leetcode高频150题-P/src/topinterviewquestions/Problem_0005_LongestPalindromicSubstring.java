package topinterviewquestions;

public class Problem_0005_LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		char[] str = manacherString(s.toCharArray());
		int[] pArr = new int[str.length];
		int R = -1;
		int C = -1;
		int max = Integer.MIN_VALUE;
		int index = 0;
		for (int i = 0; i < pArr.length; i++) {
			pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] >= 0) {
				if (str[i + pArr[i]] == str[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			if (pArr[i] > max) {
				max = pArr[i];
				index = i;
			}
		}
		return s.substring((index - max + 1) / 2, (index - max + 1) / 2 + max - 1);
	}

	public char[] manacherString(char[] s) {
		char[] mS = new char[s.length * 2 + 1];
		int index = 0;
		for (int i = 0; i < mS.length; i++) {
			mS[i] = (i & 1) == 0 ? '#' : s[index++];
		}
		return mS;
	}

	public static void main(String[] args) {

	}
}
