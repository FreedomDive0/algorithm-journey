package 多维动态规划;

import java.util.List;

public class Problem_0120_Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = triangle.get(n - 1).get(i);
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);// 从前往后不会影响
			}
		}
		return dp[0];
	}

	public int minimumTotal2(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[n - 1][i] = triangle.get(n - 1).get(i);
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
			}
		}
		return dp[0][0];
	}

	public int minimumTotal1(List<List<Integer>> triangle) {
		return process1(triangle, 0, 0, 0);
	}

	public int process1(List<List<Integer>> triangle, int level, int index, int sum) {
		if (level == triangle.size()) {
			return sum;
		}
		int sum1 = process1(triangle, level + 1, index, sum + triangle.get(level).get(index));
		int sum2 = process1(triangle, level + 1, index + 1, sum + triangle.get(level).get(index));
		return Math.min(sum1, sum2);
	}
}
