package 多维动态规划;

public class Problem_0005_Longest_Palindromic_Substring {
	public String longestPalindrome(String s) {
		char[] manachar = manacharString(s.toCharArray());
		int R = -1;// 回文最右右边界
		int C = -1;// 回文中心
		int max = 0;// 最长回文半径
		int mid = 0;// 最长回文中心
		int[] rArr = new int[manachar.length];
		for (int i = 0; i < manachar.length; i++) {
			rArr[i] = i < R ? Math.min(rArr[2 * C - i], R - i) : 1;
			while (i + rArr[i] < manachar.length && i - rArr[i] > -1) {
				if (manachar[i + rArr[i]] == manachar[i - rArr[i]]) {
					rArr[i]++;
				} else {
					break;
				}
			}
			if (rArr[i] + i > R) {
				R = rArr[i] + i;
				C = i;
			}
			if (max < rArr[i]) {
				max = rArr[i];
				mid = i;
			}
		}
		max = max - 1;
		mid = (mid - 1) / 2;
		return s.substring((max & 1) == 0 ? mid - max / 2 + 1 : mid - max / 2, mid + max / 2 + 1);
	}

	public char[] manacharString(char[] charArray) {
		char[] mana = new char[charArray.length * 2 + 1];
		int index = 0;
		for (int i = 0; i < mana.length; i++) {
			mana[i] = (i & 1) == 0 ? '#' : charArray[index++];
		}
		return mana;
	}
}
