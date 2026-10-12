package class08;

public class Code06_palindromeMinAdd {
	public int minInsertions(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];// i~j范围变为回文串最小添加字符数
		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
		}
		for (int i = N - 3; i >= 0; i--) {
			for (int j = i + 2; j < N; j++) {
				if (str[i] == str[j]) {// i与j位置字符相同 与其他两种情况结果一样 就算分别归为另一种也会将这个字符添加
					dp[i][j] = dp[i + 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
				}
			}
		}
		return dp[0][N - 1];
	}

	// 通过dp表构建答案
	public static String getMinString(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int[][] dp = getDP(s);
		char[] chs = s.toCharArray();
		int N = s.length();
		int all = s.length() + dp[0][s.length() - 1];
		char[] ans = new char[all];
		int i = 0;
		int j = N - 1;
		int ansl = 0;
		int ansr = all - 1;
		while (i <= j) {
			if (chs[i] == chs[j]) {// 生成结果从得到dp位置分类讨论 i与j位置相同
				ans[ansl++] = chs[i++];
				ans[ansr--] = chs[j--];
			} else if (dp[i + 1][j] < dp[i][j - 1]) {// 谁小取谁 如果相同出现分支答案都可以生成最小答案
				ans[ansl++] = chs[i];
				ans[ansr--] = chs[i++];
			} else {
				ans[ansl++] = chs[j];
				ans[ansr--] = chs[j--];
			}
		}
		return String.valueOf(ans);
	}

	public static int[][] getDP(String s) {
		if (s == null || s.length() < 2) {
			return null;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];// i~j范围变为回文串最小添加字符数
		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
		}
		for (int i = N - 3; i >= 0; i--) {
			for (int j = i + 2; j < N; j++) {
				if (str[i] == str[j]) {// i与j位置字符相同 与其他两种情况结果一样 就算分别归为另一种也会将这个字符添加
					dp[i][j] = dp[i + 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		String str = "AB1CD2EFG3H43IJK2L1MN";
		System.out.println(getMinString(str).length());
		System.out.println("ABNM1CDL2EFGKJI3H4H3IJKGFE2LDC1MNBA".length());
	}
}
