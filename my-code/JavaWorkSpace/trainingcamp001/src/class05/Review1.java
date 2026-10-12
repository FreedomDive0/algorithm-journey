package class05;

public class Review1 {
	public static int manacher1(String s) {
		if (s == null) {
			return 0;
		}
		char[] manacherString = manacherString(s.toCharArray());
		int[] pArr = new int[manacherString.length];
		int c = -1;
		int r = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < pArr.length; i++) {
			pArr[i] = i < r ? Math.min(pArr[2 * c - i], r - i) : 1;
			while (i + pArr[i] < pArr.length && i - pArr[i] > -1) {
				if (manacherString[i + pArr[i]] == manacherString[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			if (i + pArr[i] > r) {
				r = i + pArr[i];
				c = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max;
	}

	public static char[] manacherString(char[] chs) {
		char[] res = new char[chs.length * 2 + 1];
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : chs[index++];
		}
		return res;
	}

}
