package class11;

public class Code05_ConvertToLetterString {
	// 数字字符串能转换成多少种字母字符串
	public static int number(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		return nProcess(str.toCharArray(), 0);
	}

	public static int nProcess(char[] chs, int idx) {
		if (idx == chs.length) {
			return 1;
		}
		if (chs[idx] == '0') {
			return 0;
		}
		if (chs[idx] == '1') {
			int one = nProcess(chs, idx + 1);
			if (idx + 1 < chs.length) {
				one += nProcess(chs, idx + 2);
			}
			return one;
		}
		if (chs[idx] == '2') {
			int two = nProcess(chs, idx + 1);
			if (idx + 1 < chs.length && chs[idx + 1] >= '0' && chs[idx + 1] <= '6') {
				two += nProcess(chs, idx + 2);
			}
			return two;
		}
		return nProcess(chs, idx + 1);
	}
}
