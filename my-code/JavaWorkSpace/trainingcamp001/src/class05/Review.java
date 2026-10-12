package class05;

public class Review {
	// 1.最长回文子串
	public static int Manacher(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		char[] strs = toManaString(str.toCharArray());
		int R = -1;
		int c = -1;
		int max = Integer.MIN_VALUE;
		int[] pArr = new int[strs.length];
		for (int i = 0; i < pArr.length; i++) {
			pArr[i] = i >= R ? 1 : Math.min(pArr[2 * c - i], R - i);
			while (i + pArr[i] < strs.length && i - pArr[i] >= 0) {
				if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				c = i;
			}
			max = Math.max(max, pArr[i] - 1);
		}
		return max;
	}

	public static char[] toManaString(char[] chs) {
		char[] res = new char[chs.length * 2 + 1];
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			res[i] = i % 2 == 0 ? '#' : chs[index++];
		}
		return res;
	}

	// 2.String 后添加str 使String变为回文串 求最短str
	public static String addMinStr(String str) {
		if (str == null || str.length() < 1) {
			return null;
		}
		char[] strs = toManaString(str.toCharArray());
		int R = -1;
		int c = -1;
		int[] pArr = new int[strs.length];
		for (int i = 0; i < pArr.length; i++) {
			pArr[i] = i >= R ? 1 : Math.min(pArr[2 * c - i], R - i);
			while (i + pArr[i] < strs.length && i - pArr[i] >= 0) {
				if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				c = i;
			}
			if (R == strs.length) {
				break;
			}
		}
		int length = str.length() - pArr[c] + 1;
		char[] chs = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = length - 1; i >= 0; i--) {
			sb.append(chs[i]);
		}
		return sb.toString();
	}
}
