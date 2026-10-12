package class02;

public class Code04_DistinctSubsequences {
	public static int distinctSubsequences(String S, String T) {
		char[] s = S.toCharArray();
		char[] t = T.toCharArray();
		int[] dp = new int[t.length + 1];// 子序列有空
		dp[0] = 1;// 只有一种全部删除
		for (int i = 1; i <= s.length; i++) {
			for (int j = t.length; j > 0; j--) {// 查看依赖关系 正序没法填 就倒序填
				dp[j] += s[i - 1] == t[j - 1] ? dp[j - 1] : 0;
			}
		}
		return dp[t.length];
	}
}
