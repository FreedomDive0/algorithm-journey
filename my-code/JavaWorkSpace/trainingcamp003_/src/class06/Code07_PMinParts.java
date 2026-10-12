package class06;

public class Code07_PMinParts {
	// 将String划分的每个部分都为回文串 返回最少划分部分
	public static int minP(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		char[] chs = s.toCharArray();
		int N = chs.length;
		boolean[][] isP = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			isP[i][i] = true;
		}
		for (int i = 0; i < N - 1; i++) {// 填写回文表 需要左下的值
			isP[i][i + 1] = chs[i] == chs[i + 1] ? true : false;
		}
		for (int row = N - 3; row >= 0; row--) {// 填写L...R范围回文表
			for (int col = row + 2; col < N; col++) {
				isP[row][col] = chs[row] == chs[col] && isP[row + 1][col - 1];
			}
		}
		int[] dp = new int[N + 1];
		for (int i = 0; i < N; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[N] = 0;
		for (int start = N - 1; start >= 0; start--) {// 从后开始填 以start开始到N-1回文部分最少有几个
			for (int end = start; end < N; end++) {
				if (isP[start][end]) {// 找到每个开头最长回文组合更新dp
					dp[start] = 1 + dp[end + 1];
				}
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		String test = "aba12321412321TabaKFK";
		System.out.println(minP(test));
	}
}
