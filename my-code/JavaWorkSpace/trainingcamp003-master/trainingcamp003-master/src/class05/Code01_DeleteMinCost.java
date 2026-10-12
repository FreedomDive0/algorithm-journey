package class05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code01_DeleteMinCost {

	// 题目：
	// 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
	// 比如 s1 = "abcde"，s2 = "axbc"
	// 返回 1

	// 解法一，来自群里的解法：
	// 求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
	// 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
	// 分析：
	// 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
	// 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
	public static int minCost1(String s1, String s2) {
		List<String> s2Subs = new ArrayList<>();
		process(s2.toCharArray(), 0, "", s2Subs);
		s2Subs.sort(new LenComp());
		for (String str : s2Subs) {
			if (s1.indexOf(str) != -1) { // indexOf底层和KMP算法代价几乎一样，也可以用KMP代替
				return s2.length() - str.length();
			}
		}
		return s2.length();
	}

	public static void process(char[] str2, int index, String path, List<String> list) {
		if (index == str2.length) {
			list.add(path);
			return;
		}
		process(str2, index + 1, path, list);
		process(str2, index + 1, path + str2[index], list);
	}

	public static class LenComp implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o2.length() - o1.length();
		}

	}

	// 解法二
	// 我的方法，看的时间比较短，希望同学们积极反馈
	// 生成所有s1的子串
	// 然后考察每个子串和s2的编辑距离(假设编辑距离只有删除动作且删除一个字符的代价为1)
	// 如果s1的长度较小，s2长度较大，这个方法比较合适
	public static int minCost2(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		int ans = Integer.MAX_VALUE;
		char[] str2 = s2.toCharArray();
		for (int start = 0; start < s1.length(); start++) {
			for (int end = start + 1; end <= s1.length(); end++) {
				// str1[start....end]
				// substring -> [ 0,1 )
				ans = Math.min(ans, distance(str2, s1.substring(start, end).toCharArray()));
			}
		}
		return ans == Integer.MAX_VALUE ? s2.length() : ans;
	}

	// 求str2到s1sub的编辑距离
	// 假设编辑距离只有删除动作且删除一个字符的代价为1
	public static int distance(char[] str2, char[] s1sub) {
		int row = str2.length;
		int col = s1sub.length;
		int[][] dp = new int[row][col];
		// dp[i][j]的含义：
		// str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
		// 可能性一：
		// str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
		// 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
		// 可能性二：
		// str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
		// 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
		// 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
		dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
		for (int j = 1; j < col; j++) {
			dp[0][j] = Integer.MAX_VALUE;
		}
		for (int i = 1; i < row; i++) {
			dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				if (dp[i - 1][j] != Integer.MAX_VALUE) {
					dp[i][j] = dp[i - 1][j] + 1;
				}
				if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
				}
			}
		}
		return dp[row - 1][col - 1];
	}

	// 解法二的优化
	public static int minCost3(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		char[] str2 = s2.toCharArray();
		char[] str1 = s1.toCharArray();
		int M = str2.length;
		int N = str1.length;
		int[][] dp = new int[M][N];
		int ans = M;
		for (int start = 0; start < N; start++) { // 开始的列数
			dp[0][start] = str2[0] == str1[start] ? 0 : M;
			for (int row = 1; row < M; row++) {
				dp[row][start] = (str2[row] == str1[start] || dp[row - 1][start] != M) ? row : M;
			}
			ans = Math.min(ans, dp[M - 1][start]);
			// 以上已经把start列，填好
			// 以下要把dp[...][start+1....N-1]的信息填好
			// start...end end - start +2
			for (int end = start + 1; end < N && end - start < M; end++) {
				// 0... first-1 行 不用管
				int first = end - start;
				dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] == 0) ? 0 : M;
				for (int row = first + 1; row < M; row++) {
					dp[row][end] = M;
					if (dp[row - 1][end] != M) {
						dp[row][end] = dp[row - 1][end] + 1;
					}
					if (dp[row - 1][end - 1] != M && str2[row] == str1[end]) {
						dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);
					}
				}
				ans = Math.min(ans, dp[M - 1][end]);
			}
		}
		return ans;
	}

	// 来自学生的做法，时间复杂度O(N * M平方)
	// 复杂度和方法三一样，但是思路截然不同
	public static int minCost4(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		HashMap<Character, ArrayList<Integer>> map1 = new HashMap<>();
		for (int i = 0; i < str1.length; i++) {
			ArrayList<Integer> list = map1.getOrDefault(str1[i], new ArrayList<Integer>());
			list.add(i);
			map1.put(str1[i], list);
		}
		int ans = 0;
		// 假设删除后的str2必以i位置开头
		// 那么查找i位置在str1上一共有几个，并对str1上的每个位置开始遍历
		// 再次遍历str2一次，看存在对应str1中i后续连续子串可容纳的最长长度
		for (int i = 0; i < str2.length; i++) {
			if (map1.containsKey(str2[i])) {
				ArrayList<Integer> keyList = map1.get(str2[i]);
				for (int j = 0; j < keyList.size(); j++) {
					int cur1 = keyList.get(j) + 1;
					int cur2 = i + 1;
					int count = 1;
					for (int k = cur2; k < str2.length && cur1 < str1.length; k++) {
						if (str2[k] == str1[cur1]) {
							cur1++;
							count++;
						}
					}
					ans = Math.max(ans, count);
				}
			}
		}
		return s2.length() - ans;
	}

	public static String generateRandomString(int l, int v) {
		int len = (int) (Math.random() * l);
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ('a' + (int) (Math.random() * v));
		}
		return String.valueOf(str);
	}

	// 测试
	// 解法一
	public static int deleteMin1(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		List<String> list = new ArrayList<>();
		process1(s2.toCharArray(), 0, "", list);
		list.sort(new LenComparator());
		for (String str : list) {
			if (s1.indexOf(str) != -1) {
				return s2.length() - str.length();
			}
		}
		return s2.length();
	}

	public static void process1(char[] s2, int index, String cur, List<String> list) {
		if (index == s2.length) {
			list.add(cur);
			return;
		}
		process1(s2, index + 1, cur, list);
		process1(s2, index + 1, cur + s2[index], list);
	}

	public static class LenComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return o2.length() - o1.length();
		}

	}

	// 解法二
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

	// 测试
	// 1.s1 s2 s2最少删除多少==>s1子串
	// (1)s2长度较短时
	public static int deleteMinCost1(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		ArrayList<String> subS2 = new ArrayList<String>();
		processDMC(s2.toCharArray(), 0, "", subS2);
		subS2.sort(new LenComparator());
		for (String str : subS2) {
			if (s1.indexOf(str) != -1) {
				return s2.length() - str.length();
			}
		}
		return s2.length();
	}

	public static void processDMC(char[] str, int index, String path, ArrayList<String> ans) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		processDMC(str, index + 1, path, ans);
		processDMC(str, index + 1, path + str[index], ans);
	}

	// (2)s1子串 只有插入操作==>s2
	public static int deleteMinCost2(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		char[] str2 = s2.toCharArray();
		int minCost = s2.length();
		for (int start = 0; start < s1.length(); start++) {
			for (int end = start + 1; end <= s1.length(); end++) {
				minCost = Math.min(minCost, minCostDelete(str2, s1.substring(start, end).toCharArray()));
			}
		}
		return minCost;
	}

	public static int minCostDelete(char[] str2, char[] str1) {
		int n = str2.length;
		int m = str1.length;
		int[][] dp = new int[n][m];
		dp[0][0] = str2[0] == str1[0] ? 0 : Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == str1[0]) ? i : Integer.MAX_VALUE;// 之前匹配成功或这次字符相同删除其他部分
		}
		for (int j = 1; j < m; j++) {
			dp[0][j] = Integer.MAX_VALUE;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {// i位置可能性只有删除or不删除
				dp[i][j] = Integer.MAX_VALUE;
				if (dp[i - 1][j] != Integer.MAX_VALUE) {// 0~i-1可以搞定 把i位置删除即可
					dp[i][j] = dp[i - 1][j] + 1;
				}
				if (str2[i] == str1[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {// 相同不用删除
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	public static void main(String[] args) {
		int str1Len = 20;
		int str2Len = 10;
		int v = 5;
		int testTime = 10000;
		boolean pass = true;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			String str1 = generateRandomString(str1Len, v);
			String str2 = generateRandomString(str2Len, v);
			int ans1 = minCost1(str1, str2);
			int ans2 = deleteMinCost1(str1, str2);
			int ans3 = deleteMinCost2(str1, str2);
			int ans4 = deleteMin2(str1, str2);
			if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
				pass = false;
				System.out.println(str1);
				System.out.println(str2);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				System.out.println(ans4);
				break;
			}
		}
		System.out.println("test pass : " + pass);
	}

}
