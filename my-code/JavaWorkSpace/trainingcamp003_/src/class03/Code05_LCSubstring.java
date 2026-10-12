package class03;

public class Code05_LCSubstring {
	public static int lcs(String str1, String str2) {
		if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
			return 0;
		}
		return process(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length());
	}

	// 以i,j为结尾的最长公共子串长度
	public static int process(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {
			return str1[0] == str2[0] ? 1 : 0;
		}
		if (i == 0) {
			return str1[i] == str2[j] ? 1 : process(str1, str2, i, j - 1);
		}
		if (j == 0) {
			return str1[i] == str2[j] ? 1 : process(str1, str2, i - 1, j);
		}
		if (str1[i] != str2[j]) {
			return 0;
		} else {
			return process(str1, str2, i - 1, j - 1) + 1;
		}
	}

	public static int max = Integer.MIN_VALUE;

	public static int dopway(char[] str1, char[] str2) {
		if (str1 == null || str1.length < 1 || str2 == null || str2.length < 1) {
			return 0;
		}
		int row = 0;
		int col = str2.length - 1;
		int pre = str2[col] == str1[0] ? 1 : 0;
		while (row < str1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < str1.length && j < str2.length) {
				len = str1[i] != str2[j] ? 0 : pre + 1;
				i++;
				j++;
			}
			if (len > max) {
				max = len;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return max;
	}
}
