package class01;

public class Code03_maxVaildSub {
	// 最长有效括号子串
	public static int maxVaild(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		int ans = 0;
		char[] str = s.toCharArray();
		int[] dp = new int[str.length];
		for (int i = 1; i < str.length; i++) {
			if (str[i] == ')') {// (直接为0不需处理
				int pre = i - 1 - dp[i - 1];
				if (pre >= 0 && str[pre] == '(') {// 可以匹配
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
					ans = Math.max(ans, dp[i]);
				}
			}
		}
		return ans;
	}
}
