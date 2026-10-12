package class04;

public class Code03_IsRotation {

	public static boolean isRotation(String a, String b) {
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}
		String b2 = b + b;
		return getIndexOf(b2, a) != -1;
	}

	// KMP Algorithm
	public static int getIndexOf(String s, String m) {
		if (s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (next[mi] == -1) {
				si++;
			} else {
				mi = next[mi];
			}
		}
		return mi == ms.length ? si - mi : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while (pos < next.length) {
			if (ms[pos - 1] == ms[cn]) {
				next[pos++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[pos++] = 0;
			}
		}
		return next;
	}

	// 测试
	// 1.字符串匹配
	public static int KMP(String str, String match) {
		if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
			return -1;
		}
		char[] s = str.toCharArray();
		char[] m = match.toCharArray();
		int sIndex = 0;
		int mIndex = 0;
		int[] next = getNexts(m);
		while (sIndex < str.length() && mIndex < match.length()) {
			if (s[sIndex] == m[mIndex]) {
				sIndex++;
				mIndex++;
			} else if (mIndex > 0) {
				mIndex = next[mIndex];
			} else {
				sIndex++;
			}
		}
		return mIndex < match.length() ? -1 : sIndex - mIndex;
	}

	// 得到next数组
	public static int[] getNexts(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int curMatch = 0;
		while (index < match.length) {
			if (match[index - 1] == match[curMatch]) {
				next[index++] = ++curMatch;// curMatch既表示当前前缀比较位置又表示当前前后缀相同部分长度
			} else if (curMatch > 0) {
				curMatch = next[curMatch];
			} else {
				index++;// 此时curIndex==0
			}
		}
		return next;
	}

	// 2.是否为旋转数组
	public static boolean isRotation1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}
		String all = s1 + s1;
		return KMP(all, s2) != -1;
	}

	public static void main(String[] args) {
		String str1 = "yunzuocheng";
		String str2 = "zuochengyun";
		System.out.println(isRotation1(str1, str2));

	}

}
