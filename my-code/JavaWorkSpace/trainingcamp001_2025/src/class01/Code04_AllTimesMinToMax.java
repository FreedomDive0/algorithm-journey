package class01;

import java.util.Stack;

public class Code04_AllTimesMinToMax {
	// 4.正整数数组arr[] sub[] 返回数组中Max subSum*subMin
	public static int max2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int N = arr.length;
		int[] preSum = new int[N];// 使用前缀和数组求子数组和
		preSum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			preSum[i] = preSum[i - 1] + arr[i];
		}
		Stack<Integer> stack = new Stack<Integer>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < preSum.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int idx = stack.pop();
				max = Math.max(max, (stack.isEmpty() ? preSum[idx] : preSum[i - 1] - preSum[idx]) * arr[idx]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int idx = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? preSum[idx] : preSum[N - 1] - preSum[idx]) * arr[idx]);
		}
		return max;
	}

	// 1856. 子数组最小乘积的最大值
	public int maxSumMinProduct(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int N = arr.length;
		long[] preSum = new long[N];// 使用前缀和数组求子数组和
		preSum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			preSum[i] = preSum[i - 1] + arr[i];
		}
		Stack<Integer> stack = new Stack<Integer>();
		long max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int idx = stack.pop();
				// * 运算优先级高于 ?: 三元运算符
				max = Math.max(max,
						(stack.isEmpty() ? preSum[i - 1] : (preSum[i - 1] - preSum[stack.peek()])) * arr[idx]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int idx = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? preSum[N - 1] : (preSum[N - 1] - preSum[stack.peek()])) * arr[idx]);
		}
		// Java 7 引入的语法：数值字面量中的下划线 让大数字更可读！
		// final int MOD = 1_000_000_007;
		// return (int)(max % MOD);
		return (int) (max % (long) (Math.pow(10, 9) + 7));// 注意整数转换问题
	}
}
