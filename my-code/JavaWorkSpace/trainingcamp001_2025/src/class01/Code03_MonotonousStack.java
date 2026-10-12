package class01;

import java.util.ArrayList;
import java.util.Stack;

public class Code03_MonotonousStack {
	// 1.无重复数字的数组找到每个位置左侧比自己小离自己最近的位置 找到右侧比自己小离自己最近的位置
	public static int[][] getNearLessNoRepeat1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < res.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {// 弹出时记录答案
				int idx = stack.pop();
				int leftLessIdx = stack.isEmpty() ? -1 : stack.peek();
				res[idx][0] = leftLessIdx;
				res[idx][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int idx = stack.pop();
			int leftLessIdx = stack.isEmpty() ? -1 : stack.peek();
			res[idx][0] = leftLessIdx;
			res[idx][1] = -1;
		}
		return res;
	}

	// 2. 有重复数字的数组找到每个位置左侧比自己小离自己最近的位置 找到右侧比自己小离自己最近的位置
	public static int[][] getNearLess1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		int[][] res = new int[arr.length][2];
		Stack<ArrayList<Integer>> stack = new Stack<ArrayList<Integer>>();
		for (int i = 0; i < res.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				for (Integer idx : stack.pop()) {
					int leftLessIdx = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
					res[idx][0] = leftLessIdx;
					res[idx][1] = i;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(i);
			} else {
				ArrayList<Integer> list = new ArrayList<>();// new ArrayList<>(count) //这里赋值是对数组大小赋值
				list.add(i);
				stack.add(list);
			}
		}
		while (!stack.isEmpty()) {
			for (Integer idx : stack.pop()) {
				int leftLessIdx = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
				res[idx][0] = leftLessIdx;
				res[idx][1] = -1;
			}
		}
		return res;
	}
}
