package class03;

public class Code05_LCSubstring {

	public static String lcst1(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2);
		int end = 0;
		int max = 0;
		for (int i = 0; i < chs1.length; i++) {
			for (int j = 0; j < chs2.length; j++) {
				if (dp[i][j] > max) {
					end = i;
					max = dp[i][j];
				}
			}
		}
		return str1.substring(end - max + 1, end + 1);
	}

	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		for (int i = 0; i < str1.length; i++) {
			if (str1[i] == str2[0]) {
				dp[i][0] = 1;
			}
		}
		for (int j = 1; j < str2.length; j++) {
			if (str1[0] == str2[j]) {
				dp[0][j] = 1;
			}
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		return dp;
	}

	public static String lcst2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
			return "";
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int row = 0; // 出发点的行号
		int col = str2.length - 1; // 出发点的列号
		int max = 0;
		int end = 0;
		while (row < str1.length) {
			int i = row;
			int j = col;
			int len = 0;
			// 向右下方移动的这一轮
			while (i < str1.length && j < str2.length) {
				if (str1[i] != str2[j]) {
					len = 0;
				} else {
					len++;
				}
				// len
				if (len > max) {
					end = i;
					max = len;
				}
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return s1.substring(end - max + 1, end + 1);
	}

	public static int max = Integer.MIN_VALUE;

	// 测试
	public static int lcs(String str1, String str2) {
		if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
			return 0;
		}
		process(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1);
		return max;
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
			return process(str1, str2, i - 1, j - 1);
		} else {
			int cur = process(str1, str2, i - 1, j - 1) + 1;
			max = Math.max(max, cur);
			return cur;
		}
	}

	public static String dpway(String s1, String s2) {
		if (s1 == null || s1.equals("") || s2 == null || s2.equals("")) {
			return null;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int row = 0;
		int col = str2.length - 1;
		int end = 0;
		int max = Integer.MIN_VALUE;
		while (row < str1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < str1.length && j < str2.length) {
				if (str1[i] != str2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					max = len;
					end = i;
				}
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return s1.substring(end - max + 1, end + 1);// 开始和结束下标
	}

	// 测试
	// 5.str1,str2最长共子串
	public static String LCSubString(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
			return null;
		}
		int end = 0;
		int max = 0;
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int n = str1.length;
		int m = str2.length;
		int row = 0;
		int col = m - 1;
		while (row < n) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < n && j < m) {
				if (str1[i] != str2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					end = i;
					max = len;
				}
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return s1.substring(end - max + 1, end + 1);
	}

	public static void main(String[] args) {
		String str1 = "ABC1234567DEFG";
		String str2 = "HIJKL1234567MNOP";
		System.out.println(lcst1(str1, str2));
		System.out.println(lcst2(str1, str2));
		System.out.println(LCSubString(str1, str2));
//		System.out.println(dpway(str1, str2));
	}

}