package class03;

public class Code04_LCSubsquence {
	public static int lcs(String str1, String str2) {
		if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
			return 0;
		}
		return process(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1);
	}

	// 当str1以i结尾 str2以j结尾时最长公共子序列长度
	public static int process(char[] str1, char[] str2, int i, int j) {
		if (i == 0 && j == 0) {// 到结尾位置最多有1种
			return str1[i] == str2[j] ? 1 : 0;
		}
		if (i == 0) {// 其中一个位置到达结尾最多多一个
			return (str1[i] == str2[j] ? 1 : process(str1, str2, i, j - 1));
		}
		if (j == 0) {
			return (str1[i] == str2[j] ? 1 : process(str1, str2, i - 1, j));
		}
		// 普遍位置需要分类讨论
		int p1 = process(str1, str2, i - 1, j - 1);// 既不以i结尾也不以j结尾
		int p2 = process(str1, str2, i - 1, j);// 以j结尾
		int p3 = process(str1, str2, i, j - 1);// 以i结尾
		int p4 = Integer.MIN_VALUE;
		if (str1[i] == str2[j]) {// i,j位置相同才能以i,j结尾
			p4 = process(str1, str2, i - 1, j - 1) + 1;
		}
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	// 空间优化DP
	public int longestCommonSubsequence(String s1, String s2) {
		if (s1 == null || s1.equals("") || s2 == null || s2.equals("")) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
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
		}
		return dp[str2.length - 1];
	}
}
