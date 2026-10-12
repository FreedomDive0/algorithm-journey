package class04;

public class Code01_KMP {
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

	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	// 复习
	// 1.KMP算法
	public static int KMP1(String str, String match) {
		if (str == null || match == null || match.length() > str.length()) {
			return -1;
		}
		char[] strs = str.toCharArray();
		char[] matchs = match.toCharArray();
		int[] next = getArr(matchs);
		int index = 0;
		int indexM = 0;
		while (index < str.length() && indexM < match.length()) {
			if (strs[index] == matchs[indexM]) {
				index++;
				indexM++;
			} else if (next[indexM] == -1) {
				index++;
			} else {
				indexM = next[indexM];
			}
		}
		return indexM == matchs.length ? index - indexM : -1;
	}

	public static int[] getArr(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int jumpIndex = 0;
		while (index < match.length) {
			if (match[index - 1] == match[jumpIndex]) {
				next[index++] = ++jumpIndex;
			} else if (next[jumpIndex] == -1) {// jumpIndex来到0位置 该位置没有能匹配的前后缀
				index++;
			} else {
				jumpIndex = next[jumpIndex];
			}
		}
		return next;
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int matchSize = 5;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			String match = getRandomString(possibilities, matchSize);
			if (KMP1(str, match) != str.indexOf(match)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
