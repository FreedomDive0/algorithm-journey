package 动态规划专题;

public class LC91_解码方法 {
	public int numDecodings(String s) {
		char[] chs = s.toCharArray();
		int n = chs.length;
		int[] dp = new int[n + 1];// 有多少就用多少
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (chs[i] == '0') {
				dp[i] = 0;
			} else if (chs[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < chs.length) {
					dp[i] += dp[i + 2];
				}
			} else if (chs[i] == '2') {
				dp[i] = dp[i + 1];
				if (i + 1 < chs.length && chs[i + 1] >= '0' && chs[i + 1] <= '6') {
					dp[i] += dp[i + 2];
				}
			} else {
				dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}

	public int process(char[] s, int index) {
		if (index == s.length) {
			return 1;
		}
		if (s[index] == '0') {
			return 0;
		}
		if (s[index] == '1') {
			int ways = process(s, index + 1);
			if (index + 1 < s.length) {
				ways += process(s, index + 2);
			}
			return ways;
		}
		if (s[index] == '2') {
			int ways = process(s, index + 1);
			if (index + 1 < s.length && s[index + 1] >= '0' && s[index + 1] <= '6') {
				ways += process(s, index + 2);
			}
			return ways;
		}
		return process(s, index + 1);
	}

}
