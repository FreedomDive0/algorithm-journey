package class05;

public class Code02_AddShortestEnd {
	public static String shortestEnd(String s) {
		if (s == null || s.length() < 2) {
			return "";
		}
		char[] manacherStr = manacherString(s);
		int c = -1;
		int R = -1;
		int len = 0;
		int[] radiusArr = new int[manacherStr.length];
		for (int i = 0; i < radiusArr.length; i++) {
			radiusArr[i] = i < R ? Math.min(radiusArr[2 * c - i], R - i) : 1;
			while (i - radiusArr[i] >= 0 && i + radiusArr[i] < manacherStr.length
					&& manacherStr[i - radiusArr[i]] == manacherStr[i + radiusArr[i]]) {
				radiusArr[i]++;
			}
			if (i + radiusArr[i] > R) {
				R = i + radiusArr[i];
				c = i;
			}
			if (R == manacherStr.length) {
				len = radiusArr[i] - 1;
				break;
			}
		}
		char[] replenishStr = new char[s.length() - len];
		for (int i = 0; i < replenishStr.length; i++) {
			replenishStr[replenishStr.length - 1 - i] = manacherStr[2 * i + 1];
		}
		return String.valueOf(replenishStr);
	}

	public static char[] manacherString(String s) {
		char[] manacherStr = new char[s.length() * 2 + 1];
		char[] str = s.toCharArray();
		int idx = 0;
		for (int i = 0; i < manacherStr.length; i++) {
			manacherStr[i] = (i & 1) == 0 ? '#' : str[idx++];
		}
		return manacherStr;
	}

	// leetcode 214最短回文串
	public static String shortestStart(String s) {
		if (s == null || s.length() < 2) {
			return "";
		}
		char[] manacherStr = manacherString(s);
		int c = manacherStr.length;
		int R = manacherStr.length;
		int len = 0;
		int[] radiusArr = new int[manacherStr.length];
		for (int i = radiusArr.length - 1; i >= 0; i--) {
			radiusArr[i] = i > R ? Math.min(radiusArr[2 * c - i], i - R) : 1;
			while (i - radiusArr[i] >= 0 && i + radiusArr[i] < manacherStr.length
					&& manacherStr[i - radiusArr[i]] == manacherStr[i + radiusArr[i]]) {
				radiusArr[i]++;
			}
			if (i - radiusArr[i] < R) {
				R = i - radiusArr[i];
				c = i;
			}
			if (R < 0) {
				len = radiusArr[i] - 1;
				break;
			}
		}
		char[] replenishStr = new char[s.length() - len];
		System.out.println(len);
		for (int i = 0; i < replenishStr.length; i++) {
			replenishStr[i] = manacherStr[manacherStr.length - 2 * (i + 1)];
		}
		return String.valueOf(replenishStr);
	}

	public static void main(String[] args) {
		String str1 = "aacecaaa";
		System.out.println(shortestStart(str1));
	}
}
