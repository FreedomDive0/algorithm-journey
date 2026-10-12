package class01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Review {
	// 1.1假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值
	public static int[] SlideWindow1(int[] arr, int W) {
		if (arr == null || arr.length < 1 || W < 1 || W > arr.length) {
			return null;
		}
		int L = 0;
		int[] res = new int[arr.length - W + 1];
		int index = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		for (int R = 0; R < arr.length; R++) {
			// 放入R位置元素
			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[R]) {
				queue.pollLast();
			}
			queue.addLast(R);
			if (R + 1 >= W) {// 判断窗口大小是否达到要求
				res[index++] = arr[queue.peekFirst()];
				if (L == queue.peekFirst()) {
					queue.pollFirst();
				}
				L++;
			}
		}
		return res;
	}

	// 1.2假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值
	public static int[] SlideWindow(int[] arr, int W) {
		if (arr.length < W || arr == null || W < 1) {
			return null;
		}
		int[] res = new int[arr.length - W + 1];
		int index = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		for (int R = 0; R < arr.length; R++) {
			// 放入R位置元素
			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[R]) {
				queue.pollLast();
			}
			queue.addLast(R);
			if (R + 1 >= W) {// 判断窗口大小是否达到要求
				res[index++] = arr[queue.peekFirst()];
				if (R + 1 - W == queue.peekFirst()) {
					queue.pollFirst();
				}
			}
		}
		return res;
	}

	// 2.arr num 子数组满足 subMax-subMin<=num 求满足条件的子数组个数
	public static int AllLessNumSubArr(int[] arr, int num) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int res = 0;
		int R = 0;
		int L = 0;
		LinkedList<Integer> queueMax = new LinkedList<>();
		LinkedList<Integer> queueMin = new LinkedList<>();
		while (L < arr.length) {
			while (R < arr.length) {
				// 构建大小双端队列
				while (!queueMax.isEmpty() && arr[queueMax.peekLast()] <= arr[R]) {
					queueMax.pollLast();
				}
				queueMax.addLast(R);
				while (!queueMin.isEmpty() && arr[queueMin.peekLast()] >= arr[R]) {
					queueMin.pollLast();
				}
				queueMin.addLast(R);
				if (arr[queueMax.peekFirst()] - arr[queueMin.peekFirst()] > num) {// index到达不符合条件的位置
					break;
				}
				R++;
			}
			if (L == queueMax.peekFirst()) {// 弹出开头
				queueMax.pollFirst();
			}
			if (L == queueMin.peekFirst()) {
				queueMin.pollFirst();
			}
			res += R - L;
			L++;// L变化必须在外侧循环判断否则一直在内循环死循环
		}
		return res;
	}

	// 3.返回数组每个位置左右离它最近比它小的值 单调栈实现
	public static int[][] MonotonousStack(int[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		Stack<List<Integer>> stack = new Stack<>();
		int[][] res = new int[arr.length][2];
		int R = 0;
		while (R < arr.length) {
			// 添加arr[R]
			while (!stack.isEmpty() && arr[R] < arr[stack.peek().get(0)]) {
				List<Integer> list = stack.pop();
				for (Integer index : list) {
					res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
					res[index][1] = R;
				}
			}
			if (!stack.isEmpty() && arr[R] == arr[stack.peek().get(0)]) {
				stack.peek().add(R);
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(R);
				stack.push(list);
			}
			R++;
		}
		while (!stack.isEmpty()) {
			List<Integer> list = stack.pop();
			for (Integer index : list) {
				res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
				res[index][1] = -1;
			}
		}
		return res;
	}

	// 4.arr 任何subArr 求Max[SumSub*SubMin]
	public static int MaxSumMultiSubMin(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		int[] sumArr = new int[arr.length];// 前缀和帮助计算
		sumArr[0] = arr[0];
		for (int i = 1; i < sumArr.length; i++) {
			sumArr[i] = sumArr[i - 1] + arr[i];
		}
		for (int i = 0; i < sumArr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {// 弹出时结算∵右边界为自身 左边界为栈顶中元素or无
				int index = stack.pop();
				res = Math.max(res, arr[index] * (sumArr[i - 1] - (stack.isEmpty() ? 0 : sumArr[stack.peek()])));
			}
			stack.push(i);
		}
		int size = arr.length;
		while (!stack.isEmpty()) {// 弹出时结算∵右边界为自身 左边界为栈顶中元素or无
			int index = stack.pop();
			res = Math.max(res, arr[index] * (sumArr[size - 1] - (stack.isEmpty() ? 0 : sumArr[stack.peek()])));
		}
		return res;
	}
}
