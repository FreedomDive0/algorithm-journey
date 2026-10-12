package class05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Code01_DeleteMinCost {
	// 解法一：当s2 长度短时
	// 求s2子序列中最长且与s1子串匹配
	public static int deleteMin1(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		List<String> list = new ArrayList<>();
		process(s2.toCharArray(), 0, "", list);
		list.sort(new LenComparator());
		for (String str : list) {
			if (s1.indexOf(str) != -1) {
				return s2.length() - str.length();
			}
		}
		return s2.length();
	}

	public static void process(char[] s2, int index, String cur, List<String> list) {
		if (index == s2.length) {
			list.add(cur);
			return;
		}
		process(s2, index + 1, cur, list);
		process(s2, index + 1, cur + s2[index], list);
	}

	public static class LenComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return o2.length() - o1.length();
		}

	}

	// 解法二：当s2长度不短
	// 生成s1的子串 子串与s2的最短编辑距离
	public static int deleteMin2(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		int min = Integer.MAX_VALUE;
		char[] str2 = s2.toCharArray();
		for (int start = 0; start < s1.length(); start++) {
			for (int end = start + 1; end <= s1.length(); end++) {
				min = Math.min(min, minCostD(str2, s1.substring(start, end).toCharArray()));// s2和s1子串做只有删除的最小编辑距离
			}
		}
		return min == Integer.MAX_VALUE ? s2.length() : min;// 不存在结果就全删除
	}

	public static int minCostD(char str2[], char[] s1Sub) {
		int N = str2.length;
		int M = s1Sub.length;
		int[][] dp = new int[N][M];// i代表位置
		dp[0][0] = str2[0] == s1Sub[0] ? 0 : Integer.MAX_VALUE;
		for (int i = 1; i < M; i++) {// i位置表示长度
			dp[0][i] = Integer.MAX_VALUE;// 第一行 str2只有1个字符 无法通过删除变为s1Sub
		}
		for (int i = 1; i < N; i++) {
			dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1Sub[0]) ? i : Integer.MAX_VALUE;// 之前是否有子串是否有相同部分
																											// 有的话可以通过删除得到s1Sub[0]
		}
		for (int i = 1; i < N; i++) {// i,j表示长度
			for (int j = 1; j < M; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				if (dp[i - 1][j] != Integer.MAX_VALUE) {// dp[i-1][j]包含了前面所有的位置dp[i-2,i-3...0][j]
					dp[i][j] = dp[i - 1][j] + 1;// 如果dp[i-1][j]可以搞定 把i位置删除即可
				}
				if (str2[i] == s1Sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {// 1.保留i-1位置
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);// 前面可以搞定并且最后一位相同则不需删除
				}
			}
		}
		return dp[N - 1][M - 1];
	}

	// 解法二优化
	// 原方法 需要每次全部重新填写整张表 优化只填需要的部分 每次减少一列 0~0 0~1 0填完后 下一次就从1开始
	public static int deleteMin3(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		char[] str2 = s2.toCharArray();
		char[] str1 = s1.toCharArray();
		int M = str2.length;
		int N = str1.length;
		int[][] dp = new int[M][N];// 且dp含义发生改变 dp[i][j]表示str2->s1Sub所需只含删除行为的最短编辑距离 ∴每一列最后一行为答案不停抓取答案
		int ans = M;
		for (int start = 0; start < N; start++) {// 开始的列数 开始匹配s1Sub子串的开头
			dp[0][start] = str2[0] == str1[start] ? 0 : M;
			for (int row = 1; row < M; row++) {// 初始化start开始的第一列
				dp[row][start] = (dp[row - 1][start] != M || str2[row] == str1[start]) ? row : M;// 初始化第一列比较
																									// str2[row]和str1[start]
			}
			ans = Math.min(ans, dp[M - 1][start]);
			for (int end = start + 1; end < N && end - start < M; end++) {// end-start为s1Sub串长度
																			// 如果超过N(也就是str2长度)就无法编辑到s1Sub
				int first = end - start;// 每次新列的开头位置 如果str2长度<s1Sub长度怎么删除都变不成s1Sub
				dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] == 0) ? 0 : M;
				for (int row = first + 1; row < M; row++) {
					dp[row][end] = M;
					if (dp[row - 1][end] != M) {// dp[i-1][j]包含了前面所有的位置dp[i-2,i-3...0][j]
						dp[row][end] = dp[row - 1][end] + 1;// 如果dp[i-1][j]可以搞定 把i位置删除即可
					}
					if (str2[row] == str1[end] && dp[row - 1][end - 1] != M) {// 1.保留i-1位置
						dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);// 前面可以搞定并且最后一位相同则不需删除
					}
				}
				ans = Math.min(ans, dp[M - 1][end]);
			}
		}
		return ans;
	}
}
