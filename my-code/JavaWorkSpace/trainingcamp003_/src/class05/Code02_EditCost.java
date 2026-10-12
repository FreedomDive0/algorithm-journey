package class05;

public class Code02_EditCost {
	public static int minCost(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		int[][] dp = new int[N][M];
		for (int i = 1; i < M; i++) {// i位置表示长度
			dp[0][i] = ic * i;// 第一行str1-->str2 插入
		}
		for (int i = 1; i < N; i++) {
			dp[i][0] = dc * i;// 第一行str1-->str2 删除
		}
		for (int i = 1; i < N; i++) {// i,j表示长度
			for (int j = 1; j < M; j++) {
				if (str1[i - 1] == str2[j - 1]) {// 1.保留i-1位置
					dp[i][j] = dp[i - 1][j - 1];// (1)i-1位置相同 copy
				} else {
					dp[i][j] = dp[i - 1][j - 1] + rc;// (2)i-1位置不相同 搞定前面 最后replace
				}
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);// (3)长度i-1搞定前面 插入一个元素
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);// 2.不保留i-1位置 删除
			}
		}
		return dp[N - 1][M - 1];
	}
}
