package class08;

public class Code01_RegularExpressionMatch {

	public static boolean isValid(char[] s, char[] e) {
		// s中不能有'.' or '*'
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '*' || s[i] == '.') {
				return false;
			}
		}
		// 开头的e[0]不能是'*'，没有相邻的'*'
		for (int i = 0; i < e.length; i++) {
			if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isMatch(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		return isValid(s, e) && process(s, e, 0, 0);
	}

	// e[ei....] 能否变成 s[si...]
	// 重要限制：e[ei]不能压中'*'
	public static boolean process(char[] s, char[] e, int si, int ei) {
		if (ei == e.length) { // base case exp已经耗尽了 ""
			return si == s.length;
		}
		// si == s.length 没有讨论
		// exp[ei]有字符的 exp[ei] != "*"
		// 可能性一，ei+1位置，不是*
		if (ei + 1 == e.length || e[ei + 1] != '*') {
			// s[si...]必须有东西 && s[si] e[ei] && 后续还得能对上
			return si != s.length && (e[ei] == s[si] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
		}
		// ei + 1 位置是*
		// 尝试 [ei][ei+1]共同的部分，匹配str可能的前缀
		while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
			if (process(s, e, si, ei + 2)) {
				return true;
			}
			si++;
		}
		return process(s, e, si, ei + 2);
	}

	public static boolean isMatchDP(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		if (!isValid(s, e)) {
			return false;
		}
		// initDPMap 做出一张表，而且填好倒数两列，和最后一行
		boolean[][] dp = initDPMap(s, e);
		for (int i = s.length - 1; i > -1; i--) {
			for (int j = e.length - 2; j > -1; j--) {
				if (e[j + 1] != '*') {
					dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
				} else {
					int si = i;
					while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
						if (dp[si][j + 2]) {
							dp[i][j] = true;
							break;
						}
						si++;
					}
					if (dp[i][j] != true) {
						dp[i][j] = dp[si][j + 2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean[][] initDPMap(char[] s, char[] e) {
		int slen = s.length;
		int elen = e.length;
		boolean[][] dp = new boolean[slen + 1][elen + 1];
		dp[slen][elen] = true;
		for (int j = elen - 2; j > -1; j = j - 2) {
			if (e[j] != '*' && e[j + 1] == '*') {
				dp[slen][j] = true;
			} else {
				break;
			}
		}
		if (slen > 0 && elen > 0) {
			if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
				dp[slen - 1][elen - 1] = true;
			}
		}
		return dp;
	}

	// 测试
	public static boolean isMatchDP1(String str, String pattern) {
		if (str == null || pattern == null) {
			return false;
		}
		int n = str.length();
		int m = pattern.length();
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 1; j >= 0; j--) {
			dp[n][j] = (j + 1 < m && p[j + 1] == '*') && dp[n][j + 2];
		}
		if (n > 0 && m > 0) {
			dp[n - 1][m - 1] = s[n - 1] == p[m - 1] || p[m - 1] == '.';
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 2; j >= 0; j--) {
				if (p[j + 1] != '*') {// 先用好划分区间去划分 剩下的就是不好的区间
					dp[i][j] = (p[j] == s[i] || p[j] == '.') && dp[i + 1][j + 1];
				} else {
					if ((p[j] == s[i] || p[j] == '.') && dp[i + 1][j]) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i][j + 2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		String str = "abcccdefg";
		String exp = "ab.*d.*e.*";
		System.out.println(isMatch(str, exp));
		System.out.println(isMatchDP1(str, exp));

	}

}
