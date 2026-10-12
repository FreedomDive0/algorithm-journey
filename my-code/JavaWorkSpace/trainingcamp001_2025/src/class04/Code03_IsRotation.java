package class04;

public class Code03_IsRotation {
	public static boolean isRotation(String a, String b) {
		if (a == null || b == null) {
			return true;
		}
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}
		String combine = a + a;
		return KMP(combine, b) != -1;
	}

	public static int KMP(String s, String m) {
		if (s == null || m == null || s.length() < m.length()) {
			return -1;
		}
		int[] nexts = getNexts(m.toCharArray());
		char[] chs = m.toCharArray();
		char[] str = s.toCharArray();
		int matchIdx = 0;
		int idx = 0;
		while (idx < s.length() && matchIdx < m.length()) {
			if (chs[matchIdx] == str[idx]) {
				matchIdx++;
				idx++;
			} else if (matchIdx != 0) {
				matchIdx = nexts[matchIdx];
			} else {
				idx++;
			}
		}
		return matchIdx == m.length() ? idx - matchIdx : -1;
	}

	public static int[] getNexts(char[] match) {
		if (match.length < 2) {
			return new int[] { -1 };
		}
		int n = match.length;
		int[] nexts = new int[n];
		nexts[0] = -1;
		nexts[1] = 0;
		int idx = 2;
		int cn = 0;
		while (idx < n) {
			if (match[idx - 1] == match[cn]) {// 与前一个字符相比
				nexts[idx++] = ++cn;
			} else if (cn != 0) {
				cn = nexts[cn];
			} else {
				idx++;
			}
		}
		return nexts;
	}

	public static void main(String[] args) {
		String str1 = "yunzuocheng";
		String str2 = "zuochengyun";
		System.out.println(isRotation(str1, str2));

	}
}
