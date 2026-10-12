package topinterviewquestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_0045_JumpGameIIFollowUpOnClass {
	// 宽度优先遍历 最佳解
	public static int jumpBFS(int N, int start, int end, int[] arr) {
		if (start < 1 || start > N || end < 1 || end > N || arr == null || arr.length < 1) {
			return -1;
		}
		Queue<Integer> queue = new LinkedList<>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(start, 0);
		queue.add(start);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int level = map.get(cur);
			if (cur == end) {
				return level;
			} else {
				int left = cur - arr[cur - 1];
				int right = cur + arr[cur - 1];
				if (left > 0 && !map.containsKey(left)) {
					queue.add(left);
					map.put(left, level + 1);
				}
				if (right <= N && !map.containsKey(right)) {
					queue.add(right);
					map.put(right, level + 1);
				}
			}
		}
		return -1;
	}

	// 暴力尝试 参数包含数组
	public static int jump1(int N, int start, int end, int[] arr) {
		boolean[] walk = new boolean[N];
		return process1(N, start, end, arr, walk);
	}

	public static int process1(int N, int i, int end, int[] arr, boolean[] walk) {
		if (i < 1 || i > N || walk[i - 1]) {
			return -1;
		}
		if (i == end) {
			return 0;
		}
		int left = i - arr[i - 1];
		int right = i + arr[i - 1];
		walk[i - 1] = true;
		int ans1 = process1(N, left, end, arr, walk);
		int ans2 = process1(N, right, end, arr, walk);
		int next = -1;
		if (ans1 != -1 && ans2 != -1) {
			next = Math.min(ans1, ans2);
		} else if (ans1 != -1) {
			next = ans1;
		} else if (ans2 != -1) {
			next = ans2;
		}
		walk[i - 1] = false;
		if (next != -1) {
			return next + 1;
		}
		return -1;
	}

	// 暴力递归 修改参数
	public static int jump2(int N, int start, int end, int[] arr) {
		return process2(N, start, end, arr, 0);
	}

	public static int process2(int N, int i, int end, int[] arr, int step) {
		if (i < 1 || i > N || step > N - 1) {
			return -1;
		}
		if (i == end) {
			return step;
		}
		int left = i - arr[i - 1];
		int right = i + arr[i - 1];
		int ans1 = process2(N, left, end, arr, step + 1);
		int ans2 = process2(N, right, end, arr, step + 1);
		int next = -1;
		if (ans1 != -1 && ans2 != -1) {
			next = Math.min(ans1, ans2);
		} else if (ans1 != -1) {
			next = ans1;
		} else if (ans2 != -1) {
			next = ans2;
		}
		return next;
	}

	// 记忆化搜索
	public static int jump2M(int N, int start, int end, int[] arr) {
		int[][] dp = new int[N + 1][N];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -2;
			}
		}
		return process2M(N, start, end, arr, 0, dp);
	}

	public static int process2M(int N, int i, int end, int[] arr, int step, int[][] dp) {
		if (i < 1 || i > N || step > N - 1) {
			return -1;
		}
		if (dp[i - 1][step] != -2) {
			return dp[i - 1][step];
		}
		if (i == end) {
			dp[i - 1][step] = step;
			return step;
		}
		int left = i - arr[i - 1];
		int right = i + arr[i - 1];
		int ans1 = process2M(N, left, end, arr, step + 1, dp);
		int ans2 = process2M(N, right, end, arr, step + 1, dp);
		dp[i - 1][step] = -1;
		if (ans1 != -1 && ans2 != -1) {
			dp[i - 1][step] = Math.min(ans1, ans2);
		} else if (ans1 != -1) {
			dp[i - 1][step] = ans1;
		} else if (ans2 != -1) {
			dp[i - 1][step] = ans2;
		}
		return dp[i - 1][step];
	}

}
