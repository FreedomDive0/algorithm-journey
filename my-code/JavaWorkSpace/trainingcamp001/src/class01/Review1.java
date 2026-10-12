package class01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Review1 {
	// 1.返回w大小窗口划过数组的最大值情况
	public static int[] slidingWindowMaxArray(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for (int r = 0; r < arr.length; r++) {
			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[r]) {// L->R最大值顺序 L,R移动方向单调
				queue.pollLast();
			}
			queue.addLast(r);
			if (queue.peekFirst() == r - w) {// 先处理窗口
				queue.pollFirst();
			}
			if (r >= w - 1) {
				res[index++] = arr[queue.peekFirst()];
			}

		}
		return res;
	}

	// 2.arr[]的子数组中sub[]满足max-min<=num的个数
	public static int allLessNumSubArray(int[] arr, int num) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int res = 0;
		int L = 0;
		int R = 0;
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		LinkedList<Integer> qMin = new LinkedList<Integer>();
		while (L < arr.length) {
			while (R < arr.length) {
				while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
					qMax.pollLast();
				}
				qMax.addLast(R);
				while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
					qMin.pollLast();
				}
				qMin.addLast(R);
				if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
					break;
				}
				R++;
			}
			res += R - L;
			if (qMax.peekFirst() == L) {
				qMax.pollFirst();
			}
			if (qMin.peekFirst() == L) {
				qMin.pollFirst();
			}
			L++;
		}
		return res;
	}

	// 3.单调栈
	// 3.返回数组每个位置左右离它最近比它小的值 单调栈
	public static int[][] monotonusStack(int[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				for (Integer index : stack.pop()) {
					res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
					res[index][1] = i;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(i);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				stack.add(list);
			}
		}
		while (!stack.isEmpty()) {
			for (Integer index : stack.pop()) {
				res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
				res[index][1] = -1;
			}
		}
		return res;
	}

	// 测试
	// 4.正整数数组arr[] sub[] 返回数组中Max subSum*subMin
	public static int sumOfSubarrayMinimums1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int[] preSum = new int[arr.length];// 前缀和
		preSum[0] = arr[0];
		for (int i = 1; i < preSum.length; i++) {
			preSum[i] = preSum[i - 1] + arr[i];
		}
		int max = Integer.MIN_VALUE;
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				for (Integer index : stack.pop()) {// 加入阶段stack为空 说明左侧没有比他小的
					int sum = stack.isEmpty() ? preSum[i - 1]
							: preSum[i - 1] - preSum[stack.peek().get(stack.peek().size() - 1)];
					max = Math.max(max, sum * arr[index]);
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(i);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				stack.add(list);
			}
		}
		while (!stack.isEmpty()) {// 最后还在栈中的元素右侧没有比他小的
			for (Integer index : stack.pop()) {
				int sum = stack.isEmpty() ? preSum[arr.length - 1]
						: preSum[arr.length - 1] - preSum[stack.peek().get(stack.peek().size() - 1)];
				max = Math.max(max, sum * arr[index]);
			}
		}
		return max;
	}
}
