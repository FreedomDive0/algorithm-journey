package class08;

public class Code05_MininumCostToMergeStones {
	// 给定一个数组arr 和 一个整数k 每次将k个数合成为1个数 代价为k个数累加和 返回将arr所有数合成为1个数最小代价 无法合成返回-1
	public static int minCost(int[] arr, int k) {
//			if (arr == null || arr.length < k) {// arr.length < k 如果长度为1不需要合成
//				return -1;
//			}
		int n = arr.length;
		if ((n - 1) % (k - 1) > 0) {// 过滤条件
			return -1;
		}
		int[] preSum = new int[n + 1];
		for (int i = 0; i < n; i++) {// 前缀和数组
			preSum[i + 1] = preSum[i] + arr[i];
		}
		return process(arr, 0, n - 1, 1, k, preSum);
	}

	// 递归含义在L~R范围上 将arr[]合成为part份的最小代价
	public static int process(int[] arr, int L, int R, int part, int k, int[] preSum) {
		if (L == R) {// 只剩一个数字 part不为1 不能合成
			return part == 1 ? 0 : -1;// 一个数合成代价为0
		}
		if (part == 1) {// 只剩一个部分要合成
			int next = process(arr, L, R, k, k, preSum);// 将所有部分合称为k个
			return next == -1 ? -1 : next + preSum[R + 1] - preSum[L];
		} else {// 合成多个部分
			int ans = Integer.MAX_VALUE;
			for (int mid = L; mid < R; mid += k - 1) {// 尝试每个位置合成第一个部分 所有情况都会尝试
				int next1 = process(arr, L, mid, 1, k, preSum);
				int next2 = process(arr, mid + 1, R, part - 1, k, preSum);
				if (next1 != -1 && next2 != -1) {
					ans = Math.min(ans, next1 + next2);
				}
			}
			return ans;
		}
	}

	// 记忆化搜索
	public static int minCostMem(int[] arr, int k) {
		int n = arr.length;
		if ((n - 1) % (k - 1) > 0) {// 过滤条件
			return -1;
		}
		int[] preSum = new int[n + 1];
		for (int i = 0; i < n; i++) {// 前缀和数组
			preSum[i + 1] = preSum[i] + arr[i];
		}
		int[][][] dp = new int[n][n][k + 1];
		return processDP(arr, 0, n - 1, 1, k, preSum, dp);
	}

	public static int processDP(int[] arr, int L, int R, int part, int k, int[] preSum, int[][][] dp) {
		if (dp[L][R][part] != 0) {
			return dp[L][R][part];
		}
		if (L == R) {// 只剩一个数字 part不为1 不能合成
			dp[L][R][part] = part == 1 ? 0 : -1;
			return dp[L][R][part];// 一个数合成代价为0
		}
		if (part == 1) {// 只剩一个部分要合成
			int next = process(arr, L, R, k, k, preSum);// 将所有部分合称为k个
			dp[L][R][part] = next == -1 ? -1 : next + preSum[R + 1] - preSum[L];
			return dp[L][R][part];
		} else {// 合成多个部分
			int ans = Integer.MAX_VALUE;
			for (int mid = L; mid < R; mid += k - 1) {// 尝试每个位置合成第一个部分 所有情况都会尝试
				int next1 = process(arr, L, mid, 1, k, preSum);
				int next2 = process(arr, mid + 1, R, part - 1, k, preSum);
				if (next1 != -1 && next2 != -1) {
					ans = Math.min(ans, next1 + next2);
				}
			}
			dp[L][R][part] = ans;
			return ans;
		}
	}
}
