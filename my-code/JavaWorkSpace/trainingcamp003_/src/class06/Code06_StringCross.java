package class06;

public class Code06_StringCross {
	public static boolean stringCross(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length()) {
			return false;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		char[] str3 = s3.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		boolean[][] dp = new boolean[N][M];// 表示str1：0~i-1 str2；0~j-1 交错组成str3 0~i+j-1
		dp[0][0] = true;
		for (int row = 1; row < N; row++) {// 第一列
			if (str1[row - 1] != str3[row - 1]) {
				break;
			}
			dp[row][0] = true;
		}
		for (int col = 1; col < M; col++) {// 第一行
			if (str2[col - 1] != str3[col - 1]) {
				break;
			}
			dp[0][col] = true;
		}
		for (int row = 1; row < N; row++) {
			for (int col = 1; col < M; col++) {
				if (str1[row - 1] == str3[row + col - 1]) {// 交错组成只可能以i或j结尾
					dp[row][col] = dp[row - 1][col];
				}
				if (str2[col - 1] == str3[row + col - 1]) {
					dp[row][col] = dp[row][col - 1];
				}
			}
		}
		return dp[N - 1][M - 1];
	}

	public static void main(String[] args) {
		String str1 = "1234";
		String str2 = "abcd";
		String aim = "1a23bcd4";
		System.out.println(stringCross(str1, str2, aim));

	}
}
