package class04;

public class Code01_KMP {
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || s.length() < m.length()) {
			return -1;
		}
		char[] str = s.toCharArray();
		char[] chs = m.toCharArray();
		int[] nexts = getNexts(chs);
		int matchIdx = 0;
		int idx = 0;
		while (idx < str.length && matchIdx < chs.length) {
			if (str[idx] == chs[matchIdx]) {
				idx++;
				matchIdx++;
			} else if (matchIdx != 0) {
				matchIdx = nexts[matchIdx];
			} else {
				idx++;
			}
		}
		return matchIdx == chs.length ? idx - matchIdx : -1;
	}

	public static int[] getNexts(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] nexts = new int[match.length];
		nexts[0] = -1;
		nexts[1] = 0;
		int cn = 0;// 既表示前缀最后字符下标又可表示前缀长度-1
		int idx = 2;
		while (idx < nexts.length) {
			if (match[cn] == match[idx - 1]) {
				nexts[idx++] = ++cn;
			} else if (cn != 0) {
				cn = nexts[cn];// 相当于把自身前面一部分快速向前对齐
			} else {
				idx++;
			}
		}
		return nexts;
	}

	public static void main(String[] args) {
		String s = "mississippi";
		String m = "issipi";
		getIndexOf(s, m);
	}
}
