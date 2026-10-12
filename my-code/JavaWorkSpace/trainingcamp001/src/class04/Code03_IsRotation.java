package class04;

public class Code03_IsRotation {
	public static boolean isRotation(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}
		String str = str1 + str1;
		return KMP(str, str2) >= 0 ? true : false;
	}

	public static int KMP(String str, String match) {
		if (str == null || match == null || str.length() < 1 || match.length() > str.length()) {
			return -1;
		}
		char[] string = str.toCharArray();
		char[] matchS = match.toCharArray();
		int[] next = getNext(matchS);
		int x = 0;
		int y = 0;
		while (x < string.length && y < matchS.length) {
			if (string[x] == matchS[y]) {
				x++;
				y++;
			} else if (y > 0) {
				y = next[y];
			} else {
				x++;
			}
		}
		return y == matchS.length ? x - y : -1;
	}

	public static int[] getNext(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		int cn = 0;// 表示当前下标 所对应next应该参考的下标
		int index = 2;
		next[0] = -1;
		next[1] = 0;
		while (index < match.length) {
			if (match[index - 1] == match[cn]) {// 比较位置为index-1
				next[index++] = ++cn;// 1.更新next 2.index后移 3.cn更新位置
			} else if (cn > 0) {// 不相等且cn没到0位置 向前更新cn
				cn = next[cn];
			} else {// cn到了0 index无法匹配任何子串 后移 并且记录当前next为0
				next[index++] = 0;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str1 = "yunzuocheng";
		String str2 = "zuochengyun";
		System.out.println(isRotation(str1, str2));

	}
}
