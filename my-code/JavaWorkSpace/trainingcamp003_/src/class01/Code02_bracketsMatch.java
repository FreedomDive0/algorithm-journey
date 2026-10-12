package class01;

public class Code02_bracketsMatch {
	// 括号匹配
	public static boolean isMatch(String s) {
		if (s == null || s.length() < 2) {
			return false;
		}
		char[] str = s.toCharArray();
		int count = 0;
		for (int i = 0; i < str.length; i++) {
			count += str[i] == '(' ? 1 : -1;
			if (count < 0) {
				return false;
			}
		}
		return count == 0 ? true : false;
	}

	// 需要补充几个括号
	public static int minNeed(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		char[] str = s.toCharArray();
		int count = 0;
		int need = 0;
		for (int i = 0; i < str.length; i++) {
			count += str[i] == '(' ? 1 : -1;
			if (count < 0) {
				need++;
				count = 0;
			}
		}
		return need + count;
	}

	public static void main(String[] args) {
		String s = "(())()()()()(";
		System.out.println(isMatch(s));
		System.out.println(minNeed(s));
	}
}
