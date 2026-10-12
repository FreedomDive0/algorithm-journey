package class11;

public class Code05_ConvertToLetterString {
	// 一串数字有多少种转换为字符串的方式
	public static int number(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		return processN(str.toCharArray(), 0);
	}

	// ...idx之前的字符串已经完成转换
	public static int processN(char[] str, int idx) {
		if (idx >= str.length) {
			return 1;
		}
		if (str[idx] == '0') {
			return 0;
		}
		int single = processN(str, idx + 1);
		if (idx + 1 < str.length && (str[idx] == '1' || str[idx] == '2' && str[idx + 1] <= '6')) {// 只有10~26可以双位转换
			single += processN(str, idx + 2);
		}
		return single;
	}

	// 动态规划
	public static int numberDP(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			} else {
				dp[i] = dp[i + 1];
				if (i + 1 < N && (str[i] == '1' || str[i] == '2' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2];
				}
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(number("1111111"));
		System.out.println(numberDP("1111111"));
	}
}
