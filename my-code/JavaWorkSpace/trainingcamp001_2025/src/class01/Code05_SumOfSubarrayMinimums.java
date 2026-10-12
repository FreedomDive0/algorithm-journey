package class01;

import java.util.Stack;

public class Code05_SumOfSubarrayMinimums {
	// leetcode907. 子数组的最小值之和
	public static int sumSubarrayMins(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int n = arr.length;
		long ans = 0;// 注意提示范围
		final int MOD = 1_000_000_007;
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int idx = stack.pop();
				int subLeftCount = stack.isEmpty() ? idx + 1 : idx - stack.peek();
				int subRightCount = i - idx;
				ans += (long) subLeftCount * subRightCount * arr[idx];// 有可能在加的过程中就越界了
				ans %= MOD;// 提前处理
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int idx = stack.pop();
			int subLeftCount = stack.isEmpty() ? idx + 1 : idx - stack.peek();
			int subRightCount = n - idx;
			ans += (long) subLeftCount * subRightCount * arr[idx];
			ans %= MOD;
		}
		return (int) ans;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 1, 2, 4 };
		sumSubarrayMins(arr);
	}
}
