package 动态规划专题;

import java.util.List;

public class LC120_三角形最小路径和 {
	public static void main(String[] args) {
//		minimumTotal(triangle);
	}

	public static int minimumTotal(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[] dp = new int[n + 1];// 优化1 按层遍历 不需要记录每一层结果
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {// 优化2 i值正好与每层数字数量相同
				dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
			}
		}
		return dp[0];
	}
}
