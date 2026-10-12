package LeQu;

public class 字符串匹配 {
	public static boolean isRegularMatching(String rule, String str) {
		int IRule = rule.length();
		int IStr = str.length();
		int iRule = 0;
		int iStr = 0;
		while (iRule < IRule && iStr < IStr) {
			switch (rule.charAt(iRule)) {// 1
			case '*': {
				iRule++;
				if (iRule >= IRule) {
					return true;
				} else {
					for (int i = iStr; i < IStr; i++) {
						if (isRegularMatching(rule.substring(iRule + 1), str.substring(i + 1))) {// 2
							return true;
						}
					}
				}
				break;
			}
			case '$': {
				if ((str.charAt(iStr) < '0') || (str.charAt(iStr) > '9')) {// 3不是数字
					return false;
				}
				while ((iStr < IStr) && (str.charAt(iStr) >= '0') && (str.charAt(iStr) <= '9')) {
					iStr++;
				}
				iRule++;
				break;
			}
			default: {
				if (rule.charAt(iRule) != str.charAt(iStr)) {
					return false;// 4
				}
				iRule++;
				iStr++;
				break;
			}
			}
		}
		if (iRule < IRule && iStr >= IStr) {
			if (rule.charAt(iRule) == '*') {
				return true;
			} else {
				return isRegularMatching(rule.substring(iRule), str);// 5
			}
		}
		return false;
	}
}
