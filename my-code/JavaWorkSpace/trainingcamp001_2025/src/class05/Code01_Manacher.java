package class05;

public class Code01_Manacher {
	// 最长回文子串
	public static int manacher(String s) {
		if (s == null) {
			return -1;
		}
		char[] manacherStr = manacherString(s);
		int R = -1;
		int c = -1;
		int[] radiusArr = new int[manacherStr.length];
		int max = 0;
		for (int idx = 0; idx < radiusArr.length; idx++) {
			int radius = idx < R ? Math.min(R - c, radiusArr[2 * c - idx]) : 1;
			while (idx - radius >= 0 && idx + radius < manacherStr.length) {
				if (manacherStr[idx - radius] == manacherStr[idx + radius]) {
					radius++;
				} else {
					break;
				}
			}
			radiusArr[idx] = radius;
			max = Math.max(max, radius);
			if (idx + radius > R) {
				R = idx + radius;
				c = idx;
			}
		}
		return max - 1;
	}

	public static char[] manacherString(String s) {
		char[] manacherStr = new char[s.length() * 2 + 1];
		char[] str = s.toCharArray();
		int idx = 0;
		for (int i = 0; i < manacherStr.length; i++) {
			manacherStr[i] = i % 2 == 0 ? '#' : str[idx++];
		}
		return manacherStr;
	}

	public static String longestPalindrome(String s) {
		if (s == null) {
			return null;
		}
		char[] manacherStr = manacherString(s);
		int R = -1;
		int c = -1;
		int[] radiusArr = new int[manacherStr.length];
		int max = 0;
		int mid = 0;
		for (int idx = 0; idx < radiusArr.length; idx++) {
			radiusArr[idx] = idx < R ? Math.min(R - idx, radiusArr[2 * c - idx]) : 1;
			while (idx - radiusArr[idx] >= 0 && idx + radiusArr[idx] < manacherStr.length
					&& manacherStr[idx - radiusArr[idx]] == manacherStr[idx + radiusArr[idx]]) {
				radiusArr[idx]++;
			}
			if (idx + radiusArr[idx] > R) {
				R = idx + radiusArr[idx];
				c = idx;
			}
			if (radiusArr[idx] > max) {
				max = radiusArr[idx];
				mid = idx;
			}
		}
		max -= 1;
		mid = (mid - 1) / 2;
		int start = (max & 1) == 0 ? mid - max / 2 + 1 : mid - max / 2;
		return s.substring(start, start + max);
	}

	public static void main(String[] args) {
		String s = "babadada";
		System.out.println(longestPalindrome(s));
	}
}
