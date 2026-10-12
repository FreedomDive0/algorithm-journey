package class08;

public class Code05_MinimumCostToMergeStones {

	public static int mergeStones1(int[] stones, int K) {
		int n = stones.length;
		if ((n - 1) % (K - 1) > 0) {
			return -1;
		}
		int[] presum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			presum[i + 1] = presum[i] + stones[i];
		}
		return process1(0, n - 1, 1, stones, K, presum);
	}

	// part >= 1
	// arr[L..R] 一定要弄出part份，返回最低代价
	// arr、K、presum（前缀累加和数组，求i..j的累加和，就是O(1)了）
	public static int process1(int L, int R, int part, int[] arr, int K, int[] presum) {
		if (L == R) { // arr[L..R]
			return part == 1 ? 0 : -1;
		}
		// L ... R 不只一个数
		if (part == 1) {
			int next = process1(L, R, K, arr, K, presum);
			if (next == -1) {
				return -1;
			} else {
				return next + presum[R + 1] - presum[L];
			}
		} else { // P > 1
			int ans = Integer.MAX_VALUE;
			// L...mid是第1块，剩下的是part-1块
			for (int mid = L; mid < R; mid += K - 1) {
				// L..mid(一份) mid+1...R(part - 1)
				int next1 = process1(L, mid, 1, arr, K, presum);
				int next2 = process1(mid + 1, R, part - 1, arr, K, presum);
				if (next1 != -1 && next2 != -1) {
					ans = Math.min(ans, next1 + next2);
				}
			}
			return ans;
		}
	}

	public static int mergeStones2(int[] stones, int K) {
		int n = stones.length;
		if ((n - 1) % (K - 1) > 0) {
			return -1;
		}
		int[] presum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			presum[i + 1] = presum[i] + stones[i];
		}
		int[][][] dp = new int[n][n][K + 1];
		return process2(0, n - 1, 1, stones, K, presum, dp);
	}

	public static int process2(int i, int j, int part, int[] arr, int K, int[] presum, int[][][] dp) {
		if (dp[i][j][part] != 0) {
			return dp[i][j][part];
		}
		if (i == j) {
			return part == 1 ? 0 : -1;
		}
		if (part == 1) {
			int next = process2(i, j, K, arr, K, presum, dp);
			if (next == -1) {
				dp[i][j][part] = -1;
				return -1;
			} else {
				dp[i][j][part] = next + presum[j + 1] - presum[i];
				return next + presum[j + 1] - presum[i];
			}
		} else {
			int ans = Integer.MAX_VALUE;
			// i...mid是第1块，剩下的是part-1块
			for (int mid = i; mid < j; mid += K - 1) {
				int next1 = process2(i, mid, 1, arr, K, presum, dp);
				int next2 = process2(mid + 1, j, part - 1, arr, K, presum, dp);
				if (next1 == -1 || next2 == -1) {
					dp[i][j][part] = -1;
					return -1;
				} else {
					ans = Math.min(ans, next1 + next2);
				}
			}
			dp[i][j][part] = ans;
			return ans;
		}
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (maxSize * Math.random()) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
	// 给定一个数组arr 和 一个整数k 每次将k个数合成为1个数 代价为k个数累加和 返回将arr所有数合成为1个数最小代价 无法合成返回-1
	public static int minCost(int[] arr, int k) {
//		if (arr == null || arr.length < k) {// arr.length < k 如果长度为1不需要合成
//			return -1;
//		}
		int n = arr.length;
		if ((n - 1) % (k - 1) > 0) {// 过滤条件
			return -1;
		}
		int[] preSum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			preSum[i + 1] = preSum[i] + arr[i];
		}
		return process(arr, 0, n - 1, 1, k, preSum);
	}

	// 递归含义在L~R范围上 将arr[]合成为part份的最小代价
	public static int process(int[] arr, int L, int R, int part, int k, int[] preSum) {
		if (L == R) {
			return part == 1 ? 0 : -1;
		}
		if (part == 1) {
			int next = process(arr, L, R, k, k, preSum);
			return next == -1 ? -1 : next + preSum[R + 1] - preSum[L];
		} else {
			int ans = Integer.MAX_VALUE;
			for (int mid = L; mid < R; mid += k - 1) {
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

	public static void main(String[] args) {
		int maxSize = 12;
		int maxValue = 100;
		System.out.println("Test begin");
		for (int testTime = 0; testTime < 100000; testTime++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int K = (int) (Math.random() * 7) + 2;
			int ans1 = minCostMem(arr, K);
			int ans2 = mergeStones2(arr, K);
			if (ans1 != ans2) {
				System.out.println(ans1);
				System.out.println(ans2);
			}
		}

	}

}
