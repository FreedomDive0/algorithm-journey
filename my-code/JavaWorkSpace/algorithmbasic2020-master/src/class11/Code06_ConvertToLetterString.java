package class11;

public class Code06_ConvertToLetterString {

	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0...i-1]已经转化完了，固定了
	// i之前的位置，如何转化已经做过决定了, 不用再关心
	// i... 有多少种转化的结果
	public static int process(char[] str, int i) {
		if (i == str.length) { // base case
			return 1;
		}
		if (str[i] == '0') {
			return 0;
		}
		if (str[i] == '1') {
			int res = process(str, i + 1);
			if (i + 1 < str.length) {
				res += process(str, i + 2);
			}
			return res;
		}
		if (str[i] == '2') {
			int res = process(str, i + 1);
			if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
				res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
			}
			return res;
		}
		return process(str, i + 1);
	}

	public static int dpWays2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			}
			if (str[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < str.length) {
					dp[i] += dp[i + 2];
				}
			}
			if (str[i] == '2') {
				dp[i] = dp[i + 1];
				if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2]; // (i和i+1)作为单独的部分，后续有多少种方法
				}
			}
		}
		return dp[0];
	}

	public static int dpWays(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			} else if (str[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < N) {
					dp[i] += dp[i + 2];
				}
			} else if (str[i] == '2') {
				dp[i] = dp[i + 1];
				if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2];
				}
			} else {
				dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}

	// 测试2026
	// 7.数字对应字符串
	public static int number1(String str) {
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

	// (2)dp
	public static int convertToLetterDP(String nums) {
		int n = nums.length();
		int[] dp = new int[n + 1];
		char[] chs = nums.toCharArray();
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (chs[i] == '0') {
				dp[i] = 0;
			} else {
				dp[i] = dp[i + 1];
				if (i + 1 < n && ((chs[i] == '1') || (chs[i] == '2' && chs[i + 1] >= '0' && chs[i + 1] <= '6'))) {
					dp[i] += dp[i + 2];
				}
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(number1("1111111"));
		System.out.println(dpWays2("1111111"));
	}

}
