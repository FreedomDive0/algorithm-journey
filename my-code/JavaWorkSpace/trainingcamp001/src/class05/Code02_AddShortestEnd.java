package class05;

public class Code02_AddShortestEnd {
	public static String shortestEnd(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		char[] str = manacherString(s);
		int[] pArr = new int[str.length];// 每个字符的回文半径
		int C = -1;// 最右回文子串中心
		int R = -1;// 最右回文子串边界+1 为了方便计算
		int maxContainsEnd = -1;// 包含R的最长回文子序列半径
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
			if (R == str.length) {
				maxContainsEnd = pArr[i];
				break;
			}
		}
		// maxContainsEnd - 1为回文子序列长度
		char[] res = new char[s.length() - maxContainsEnd + 1];
		for (int i = 0; i < res.length; i++) {
			res[res.length - 1 - i] = str[i * 2 + 1];// 倒填正填
		}
		return String.valueOf(res);
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

	// 复习
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

	public static char[] toManaString(char[] chs) {
		char[] res = new char[chs.length * 2 + 1];
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			res[i] = i % 2 == 0 ? '#' : chs[index++];
		}
		return res;
	}

	// for test
	public static void main(String[] args) {
		String str1 = "abcd123321";
		System.out.println(shortestEnd(str1));
		System.out.println(addMinStr(str1));
	}
}
