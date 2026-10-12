package class05;

public class Code01_Manacher {
	public static int manacher(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		int[] pArr = new int[str.length];// 每个字符的回文半径
		int C = -1;// 最右回文子串中心
		int R = -1;// 最右回文子串边界+1 为了方便计算
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < str.length; i++) {
			pArr[i] = i >= R ? 1 : Math.min(pArr[C * 2 - i], R - i);// C-(i-C)=C*2-i
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;// 不满足条件结束
				}
			}
			if (i + pArr[i] > R) {// 更新范围
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;// 边界值-1
	}

//	扩展化
	public static char[] manacherString(String s) {
		char[] str = new char[s.length() * 2 + 1];
		char[] string = s.toCharArray();
		int index = 0;
		for (int i = 0; i < str.length; i++) {
			str[i] = (i & 1) == 0 ? '#' : string[index++];
		}
		return str;
	}

	// for test
	public static int right(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			int L = i - 1;
			int R = i + 1;
			while (L >= 0 && R < str.length && str[L] == str[R]) {
				L--;
				R++;
			}
			max = Math.max(max, R - L - 1);
		}
		return max / 2;
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
	// 1.最长回文子串
	public static int Manacher(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		char[] strs = manacherString(str.toCharArray());
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

	// 测试
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

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			if (manacher(str) != Manacher(str)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
