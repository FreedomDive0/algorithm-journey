package class03;

public class Code04_LCSubsequence {

	public static int lcs(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		return process(str1, str2, N - 1, M - 1);
	}

	// str1[0....i1] 与 str2[0......i2]的最长公共子序列长度是多少？
	public static int process(char[] str1, char[] str2, int i1, int i2) {
		if (i1 == 0 && i2 == 0) {
			return str1[i1] == str2[i2] ? 1 : 0;
		}
		// i1 和 i2 不同时为0
		if (i1 == 0) { // str1[0..0] str2[0...i2 - 1]
			return ((str1[i1] == str2[i2]) || process(str1, str2, i1, i2 - 1) == 1) ? 1 : 0;
		}

		if (i2 == 0) {
			return ((str1[i1] == str2[i2]) || process(str1, str2, i1 - 1, i2) == 1) ? 1 : 0;
		}
		// i1 和 i2 都不是0
		// 最长公共子序列结尾，不是以str1[i1]与str2[i2]结尾的
		int p1 = process(str1, str2, i1 - 1, i2 - 1);
		int p2 = process(str1, str2, i1, i2 - 1);
		int p3 = process(str1, str2, i1 - 1, i2);
		int p4 = 0;
		if (str1[i1] == str2[i2]) {
			p4 = p1 + 1;
		}
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	public static int dp(String s1, String s2) {

		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;

		int[][] dp = new int[N][M];

		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int j = 1; j < M; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < N; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		return dp[N - 1][M - 1];
	}

	public static String lcse(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2);
		int m = chs1.length - 1;
		int n = chs2.length - 1;
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;
		while (index >= 0) {
			if (n > 0 && dp[m][n] == dp[m][n - 1]) {
				n--;
			} else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
				m--;
			} else {
				res[index--] = chs1[m];
				m--;
				n--;
			}
		}
		return String.valueOf(res);
	}

	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < str1.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		for (int j = 1; j < str2.length; j++) {
			dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp;
	}

	// 测试
	public static int lcs1(String str1, String str2) {
		if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
			return 0;
		}
		return process1(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1);
	}

	// 当str1以i结尾 str2以j结尾时最长公共子序列长度
	public static int process1(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {// 到结尾位置最多有1种
			return str1[i] == str2[j] ? 1 : 0;
		}
		if (i == 0) {// 其中一个位置到达结尾最多多一个
			return (str1[i] == str2[j] ? 1 : process1(str1, str2, i, j - 1));
		}
		if (j == 0) {
			return (str1[i] == str2[j] ? 1 : process1(str1, str2, i - 1, j));
		}
		// 普遍位置需要分类讨论
		int p1 = process1(str1, str2, i - 1, j - 1);// 既不以i结尾也不以j结尾
		int p2 = process1(str1, str2, i - 1, j);// 以j结尾
		int p3 = process1(str1, str2, i, j - 1);// 以i结尾
		int p4 = Integer.MIN_VALUE;
		if (str1[i] == str2[j]) {// i,j位置相同才能以i,j结尾
			p4 = process1(str1, str2, i - 1, j - 1) + 1;
		}
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	public static int dpway(char[] str1, char[] str2) {
		if (str1 == null || str1.length < 1 || str2 == null || str2.length < 1) {
			return 0;
		}
		int[] dp = new int[str2.length];
		dp[0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < dp.length; i++) {// 将第一行填好
			dp[i] = dp[i - 1] == 0 ? str1[0] == str2[i] ? 1 : 0 : 1;
		}
		for (int i = 1; i < str1.length; i++) {
			dp[0] = str1[i] == str2[0] ? 1 : dp[0];
			int tmpLeftUp = dp[0];
			for (int j = 1; j < dp.length; j++) {
				int p3 = Integer.MIN_VALUE;// 以i,j结尾
				if (i > 0 && j > 0) {// 左上
					if (str1[i] == str2[j]) {// 使用过左上角的值才能向后移动
						p3 = tmpLeftUp + 1;
					}
					tmpLeftUp = dp[j];
				}
				dp[j] = Math.max(j > 0 ? dp[j - 1] : Integer.MIN_VALUE, dp[j]);
				dp[j] = Math.max(p3, dp[j]);
			}
			for (int j = 0; j < dp.length; j++) {
				System.out.print(dp[j] + " ");
			}
			System.out.println();
		}
		return dp[str2.length - 1];
	}

	// 测试
	// 4.str1,str2最长公共子序列
	public static int LCSubsequnce(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int n = str1.length;
		int m = str2.length;
		int[][] dp = new int[n][m];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < n; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int j = 1; j < m; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	public static void main(String[] args) {
		String str1 = "bsbininm";
		String str2 = "jmjkbkjkv";
//		System.out.println(lcse(str1, str2));
//		System.out.println(lcs(str1, str2));
//		System.out.println(dp(str1, str2));
		System.out.println(LCSubsequnce(str1, str2));
//		System.out.println(dpway(str1.toCharArray(), str2.toCharArray()));
	}
}