package topinterviewquestions;

public class Problem_0010_RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		return isVaild(str, pattern) && process(str, pattern, 0, 0);
	}

	public boolean isVaild(char[] str, char[] pattern) {
		for (char s : str) {
			if (s == '.' || s == '*') {
				return false;
			}
		}
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
				return false;
			}
		}
		return true;
	}

	public boolean process(char[] str, char[] pattern, int si, int pi) {
		if (si == str.length) {
			if (pi == pattern.length) {
				return true;
			}
			if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
				return process(str, pattern, si, pi + 2);
			}
			return false;
		}
		if (pi == pattern.length) {
			return false;
		}
		if (pi == pattern.length - 1 || pattern[pi + 1] != '*') {
			return (pattern[pi] == str[si] || pattern[pi] == '.') && process(str, pattern, si + 1, pi + 1);
		}
		if (process(str, pattern, si, pi + 2)) {
			return true;
		}
		while (si < str.length && (pattern[pi] == '.' || pattern[pi] == str[si])) {
			if (process(str, pattern, si + 1, pi + 2)) {
				return true;
			}
			si++;
		}
		return false;
	}

	// 动态规划版本
	public static boolean isMatchDP(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		int n = str.length;
		int m = pattern.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 1; j >= 0; j--) {
			dp[n][j] = (j + 1 < m && pattern[j + 1] == '*') && dp[n][j + 2];// ∵j==m-1时 j + 1 < m不成立 不会触发dp[n][j + 2]越界
		}
		dp[n - 1][m - 1] = str[n - 1] == pattern[m - 1] || pattern[m - 1] == '.';
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 2; j >= 0; j--) {// 因为m-1时只有 pattern和str最后一个字符相同或pattern为.时才成立
				if (pattern[j + 1] != '*') {
					dp[i][j] = (str[i] == pattern[j] || pattern[j] == '.') && dp[i + 1][j + 1];
				} else {
					if ((str[i] == pattern[j] || pattern[j] == '.') && dp[i + 1][j]) {
						dp[i][j] = true;// dp[i+1][j]包含了除了不使用pattern[j]*所有能匹配的可能性
					} else {
						dp[i][j] = dp[i][j + 2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		String s = "aaa";
		String p = "a*aaa";
		System.out.println(isMatchDP(s, p));
	}
//	public boolean isMatch(String s, String p) {
//		if (s == null || p == null) {
//			return false;
//		}
//		return isValid(s, p) && process(s.toCharArray(), p.toCharArray(), 0, 0);
//	}
//
//	public boolean process(char[] s, char[] p, int si, int pi) {
//		if (si == s.length) {
//			if (pi == p.length) {
//				return true;
//			}
//			if (pi + 1 < p.length && p[pi + 1] == '*') {
//				return process(s, p, si, pi + 2);
//			}
//			return false;
//		}
//		if (pi == p.length) {
//			return false;
//		}
//		if (pi + 1 >= p.length || p[pi + 1] != '*') {// 先用好划分区间去划分 剩下的就是不好的区间
//			return (p[pi] == s[si] || p[pi] == '.') && process(s, p, si + 1, pi + 1);
//		}
//		if (s[si] != p[pi] && p[pi] != '.') {
//			return process(s, p, si, pi + 2);
//		}
//		if (process(s, p, si, pi + 2)) {
//			return true;
//		}
//		while (si < s.length && (p[pi] == s[si] || p[pi] == '.')) {
//			if (process(s, p, si + 1, pi + 2)) {
//				return true;
//			}
//			si++;
//		}
//		return false;
//	}
//
//	public boolean isValid(String s, String p) {
//		if (s.contains("*") || s.contains(".")) {
//			return false;
//		}
//		char[] ps = p.toCharArray();
//		if (ps[0] == '*') {
//			return false;
//		}
//		for (int i = 1; i < ps.length; i++) {
//			if (ps[i] == '*' && ps[i - 1] == '*') {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public boolean isMatchDP(String str, String pattern) {
//		if (str == null || pattern == null) {
//			return false;
//		}
//		int n = str.length();
//		int m = pattern.length();
//		char[] s = str.toCharArray();
//		char[] p = pattern.toCharArray();
//		boolean[][] dp = new boolean[n + 1][m + 1];
//		dp[n][m] = true;
//		for (int j = m - 1; j >= 0; j--) {
//			dp[n][j] = (j + 1 < m && p[j + 1] == '*') && dp[n][j + 2];
//		}
//		if (n > 0 && m > 0) {
//			dp[n - 1][m - 1] = s[n - 1] == p[m - 1] || p[m - 1] == '.';
//		}
//		for (int i = n - 1; i >= 0; i--) {
//			for (int j = m - 2; j >= 0; j--) {
//				if (p[j + 1] != '*') {// 先用好划分区间去划分 剩下的就是不好的区间
//					dp[i][j] = (p[j] == s[i] || p[j] == '.') && dp[i + 1][j + 1];
//				} else {
//					if ((p[j] == s[i] || p[j] == '.') && dp[i + 1][j]) {
//						dp[i][j] = true;
//					} else {
//						dp[i][j] = dp[i][j + 2];
//					}
//				}
//			}
//		}
//		return dp[0][0];
//	}
}
