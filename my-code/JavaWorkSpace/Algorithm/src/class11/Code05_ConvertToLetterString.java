package class11;

public class Code05_ConvertToLetterString {
	public static int convertToLetterString(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process1(0, str.toCharArray());
	}

	public static int process1(int index, char[] chs) {
		if (index == chs.length) {
			return 1;
		}
		if (chs[index] == '0') {
			return 0;
		}
		if (chs[index] == '1') {
			int res = process1(index + 1, chs);
			if (index + 1 < chs.length) {
				res += process1(index + 2, chs);// 不同分支
			}
			return res;// 情况走完返回
		}
		if (chs[index] == '2') {
			int res = process1(index + 1, chs);
			if (index + 1 < chs.length && chs[index + 1] < '7') {
				res += process1(index + 2, chs);
			}
			return res;
		}
		return process1(index + 1, chs);
	}

//	动态规划
	public static int dpWay(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		int N = str.length();
		char[] chs = str.toCharArray();
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {// 这几个选项每次只能中一个
			if (chs[i] == '0') {
				dp[i] = 0;
			} else if (chs[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < chs.length) {
					dp[i] += dp[i + 2];
				}
			} else if (chs[i] == '2') {
				dp[i] = dp[i + 1];
				if (i + 1 < chs.length && chs[i + 1] < '7') {
					dp[i] += dp[i + 2];
				}
			} else {
				dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}

	// 复习
	public static int ConvertNumTOString(String str) {
		if (str == null) {
			return 0;
		}
		return processCNS(str.toCharArray(), 0);
	}

	public static int processCNS(char[] arr, int index) {
		if (index == arr.length) {
			return 1;
		}
		if (arr[index] == '0') {// 0
			return 0;
		}
		if (arr[index] == '1') {// 1 or 10~19
			int res = processCNS(arr, index + 1);
			if (index + 1 < arr.length) {
				res += processCNS(arr, index + 2);
			}
			return res;
		}
		if (arr[index] == '2') {
			int res = processCNS(arr, index + 1);
			if (index + 1 < arr.length && arr[index + 1] < '7') {
				res += processCNS(arr, index + 2);
			}
			return res;
		}
		return processCNS(arr, index + 1);// 3~9
	}

	// 7.1数字对应问题 动态规划
	public static int ConvertNumTOStringDP(String str) {
		if (str == null) {
			return 0;
		}
		int N = str.length();
		char[] chs = str.toCharArray();
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (chs[i] == '0') {
				dp[i] = 0;
				continue;
			} else if (chs[i] == '1') {
				if (i + 1 < N) {
					dp[i] += dp[i + 2];
				}
			} else if (chs[i] == '2') {
				if (i + 1 < N && chs[i + 1] < '7') {
					dp[i] += dp[i + 2];
				}
			}
			dp[i] += dp[i + 1];

		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(convertToLetterString("1111111111"));
		System.out.println(ConvertNumTOStringDP("1111111111"));
		System.out.println(dpWay("1111111111"));
	}
}
