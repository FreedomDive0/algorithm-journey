package class01;

import java.util.Stack;

public class Code05_SumOfSubarrayMinimums {
	public static int sumOfSubarrayMinimums(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		// 前缀和
		int[] sum = new int[arr.length];
		int res = 0;
		int size = arr.length;
		Stack<Integer> stack = new Stack<Integer>();
		sum[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}
		// 选定右侧为最小值 每次弹出时判断
		for (int index = 0; index < size; index++) {
//			让每一个弹出的数当最小值进行计算	因为每一个入栈的值都能找到左边离它最近比它小的值
//			如果当前值可以让 栈弹出 弹出的数为当前最小值 如果栈为空说明当前值为目前整体最小值 
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[index]) {
				int i = stack.pop();
				res = Math.max(res, (stack.isEmpty() ? sum[index - 1] : (sum[index - 1] - sum[stack.peek()])) * arr[i]);
			}
			stack.push(index);
		}
		while (!stack.isEmpty()) {
			int i = stack.pop();
			res = Math.max(res, (stack.isEmpty() ? sum[size - 1] : (sum[size - 1] - sum[stack.peek()])) * arr[i]);
		}
		return res;
	}

	public static int max2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int size = arr.length;
		int[] sums = new int[size];
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < size; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int j = stack.pop();
				max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
		}
		return max;
	}

//	for test
	public static int max1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int N = arr.length;
		for (int i = 0; i < N; i++) {// 子数组起始位置
			for (int j = i; j < N; j++) {// 子数组结束位置
				int minNum = Integer.MAX_VALUE;
				int sum = 0;
				for (int k = i; k <= j; k++) {// 列举全部情况
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				max = Math.max(max, sum * minNum);
			}
		}
		return max;
	}

	public static int[] randomArray(int len, int maxValue) {
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * maxValue) + 1;
		}
		return ans;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int maxLen = 100;
		int maxValue = 50;
		int testTime = 100000;
//		int arr1[] = { 7, 7, 6, 2 };
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * maxLen);
			int[] arr = randomArray(len, maxValue);
			int ans1 = max1(arr);
			int ans2 = max2(arr);
//			System.out.println(ans1 + " " + ans2);
//		int ans3 = sumSubarrayMins(arr);
			if (ans1 != ans2) {
				printArray(arr);
				System.out.println(ans1);
				System.out.println(ans2);
//				System.out.println(ans3);
				System.out.println("出错了！");
				break;
			}
		}
		System.out.println("测试结束");
	}
}
