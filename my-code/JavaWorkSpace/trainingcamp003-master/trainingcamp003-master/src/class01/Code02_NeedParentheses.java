package class01;

public class Code02_NeedParentheses {

	public static boolean valid(String s) {
		char[] str = s.toCharArray();
		int count = 0;
		for (int i = 0; i < str.length; i++) {
			count += str[i] == '(' ? 1 : -1;
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

	public static int needParentheses(String s) {
		char[] str = s.toCharArray();
		int count = 0;
		int need = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				count++;
			} else { // 遇到的是')'
				if (count == 0) {
					need++;
				} else {
					count--;
				}
			}
		}
		return count + need;
	}

	// 测试
	// 2.括号匹配问题 1)是否符合 2)需要补几个
	public static int bracketsMatch(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		int count = 0;
		int need = 0;
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == '(') {
				count++;
			} else if (count == 0) {
				need++;
			} else {
				count--;
			}
		}
		return count + need;
	}
}
