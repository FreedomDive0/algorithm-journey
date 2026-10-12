package 字符串专题;

public class LC5_最长回文子串 {
	public String longestPalindrome(String s) {
		if (s.equals("")) {
			return s;
		}
		String orgin = s;
		String resver = new StringBuffer(s).reverse().toString();
		int[][] arr = new int[s.length()][s.length()];
		int maxLen = 0;
		int maxEnd = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (orgin.charAt(i) == resver.charAt(j)) {
					if (i == 0 || j == 0) {
						arr[i][j] = 1;
					} else {
						arr[i][j] = arr[i - 1][j - 1] + 1;
					}
					if (arr[i][j] > maxLen) {
						int breIndex = s.length() - 1 - j;
						if (breIndex + arr[i][j] - 1 == i) {
							maxLen = arr[i][j];
							maxEnd = i;
						}
					}
				}
			}
		}
		return orgin.substring(maxEnd - maxLen + 1, maxEnd + 1);
	}
}
