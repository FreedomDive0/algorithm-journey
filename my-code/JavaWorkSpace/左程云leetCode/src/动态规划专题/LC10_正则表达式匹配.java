package 动态规划专题;

public class LC10_正则表达式匹配 {
	public boolean isMatch(String s, String p) {
		int M = s.length();
		int N = p.length();
		int[][] dp = new int[M + 1][N + 1];
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j <= N; j++) {
				dp[i][j] = -1;
			}
		}
		return process(s.toCharArray(), p.toCharArray(), 0, 0, dp);
	}

	public boolean isVailed(char[] s, char[] p) {// 字符串和匹配规则合法性检验
		for (int i = 0; i < s.length; i++) {// 字符串中不能包含.or*
			if (s[i] == '.' && s[i] == '*') {
				return false;
			}
		}
		for (int i = 0; i < p.length; i++) {// 匹配规则首位不能是* 且不能双*或多*相连
			if (i == 0 && p[i] == '*') {
				return false;
			}
			if (p[i] == '*' && p[i - 1] == '*') {
				return false;
			}
		}
		return true;
	}

	public boolean process(char[] s, char[] p, int si, int pi, int[][] dp) {
		if (dp[si][pi] != -1) {// 计算过该位置
			return dp[si][pi] == 1;
		}
		if (si == s.length) {// 字符串越界
			if (pi == p.length) {
				dp[si][pi] = 1;
				return true;
			}
			if (pi + 1 < p.length && p[pi + 1] == '*') {
				boolean ans = process(s, p, si, pi + 2, dp);
				dp[si][pi] = ans ? 1 : 0;
				return ans;
			}
			dp[si][pi] = 0;
			return false;
		}
		if (pi == p.length) {// 匹配规则越界
			boolean ans = si == s.length;
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		if (pi + 1 >= p.length || p[pi + 1] != '*') {// 以pi+1是否为*做划分
			boolean ans = (s[si] == p[pi] || p[pi] == '.') && process(s, p, si + 1, pi + 1, dp);
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		// if (p[pi] != '.' && s[si] != p[pi]) {//该情况为 x* x!=.且 x!=s的字符 所以要把该点消除
		// 但与下方使用0次的尝试重复了 可以省略
		// return process(s, p, si, pi + 2,dp);
		// }
		// 处理x*
		if (process(s, p, si, pi + 2, dp)) {// 1.先尝试自身消除 注意容易忽略这一点
			dp[si][pi] = 1;
			return true;
		}
		while (si < s.length && (p[pi] == s[si] || p[pi] == '.')) {// 2.一直向后尝试
			if (process(s, p, si + 1, pi + 2, dp)) {
				dp[si][pi] = 1;
				return true;// 只有有一种情况符合条件就直接返回
			}
			si++;// 字符串向后移动
		}
		dp[si][pi] = 0;
		return false;// 所有的可能都尝试了 没符合返回false
	}

	// 暴力递归尝试
	public boolean isMatch1(String s, String p) {
		return process1(s.toCharArray(), p.toCharArray(), 0, 0);
	}

	public boolean process1(char[] s, char[] p, int si, int pi) {// 潜规则 进入递归的匹配规则不以*开头
		if (si == s.length) {// 字符串越界
			if (pi == p.length) {
				return true;
			}
			if (pi + 1 < p.length && p[pi + 1] == '*') {
				return process1(s, p, si, pi + 2);
			}
			return false;
		}
		if (pi == p.length) {// 匹配规则越界
			return si == s.length;
		}
		if (pi + 1 >= p.length || p[pi + 1] != '*') {// 以pi+1是否为*做划分
			return (s[si] == p[pi] || p[pi] == '.') && process1(s, p, si + 1, pi + 1);
		}
		// if (p[pi] != '.' && s[si] != p[pi]) {
		// return process1(s, p, si, pi + 2);
		// }
		// 处理x*
		if (process1(s, p, si, pi + 2)) {// 1.先尝试自身消除 注意容易忽略这一点
			return true;
		}
		while (si < s.length && (p[pi] == s[si] || p[pi] == '.')) {// 2.一直向后尝试
			if (process1(s, p, si + 1, pi + 2)) {
				return true;// 只有有一种情况符合条件就直接返回
			}
			si++;// 字符串向后移动
		}
		return false;// 所有的可能都尝试了 没符合返回false
	}
}
