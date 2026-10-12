package class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code03_MonotonousStack {
	public static int[][] monotonousStack(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<List<Integer>>();
		int index = 0;
		while (index < arr.length) {
			// 添加新数字 如果新值比栈中值小 则可以更新栈值
			while (!stack.isEmpty() && arr[index] < arr[stack.peek().get(0)]) {
				List<Integer> list = stack.pop();
				for (int i : list) {
					res[i][0] = stack.isEmpty() ? -1 : arr[stack.peek().get(stack.peek().size() - 1)];
					res[i][1] = arr[index];
				}
			}
			// 栈中有这个值 添加到数组之后
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[index]) {
				stack.peek().add(Integer.valueOf(index));
			} else {
				// 不存在该数字 创建新数组
				List<Integer> list = new ArrayList<Integer>();
				list.add(index);
				stack.push(list);
			}
			index++;
		}
		// 处理剩余数字
		while (!stack.isEmpty()) {
			List<Integer> list = stack.pop();
			for (int i : list) {
				res[i][0] = stack.isEmpty() ? -1 : arr[stack.peek().get(stack.peek().size() - 1)];
				res[i][1] = -1;
			}
		}
		return res;
	}

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
				return false;
			}
		}

		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 复习
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
					res[index][0] = stack.isEmpty() ? -1 : arr[stack.peek().get(stack.peek().size() - 1)];
					res[index][1] = arr[R];
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
				res[index][0] = stack.isEmpty() ? -1 : arr[stack.peek().get(stack.peek().size() - 1)];
				res[index][1] = -1;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
		int testTimes = 2000000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
//			if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
//				System.out.println("Oops!");
//				printArray(arr1);
//				break;
//			}
			if (!isEqual(monotonousStack(arr2), MonotonousStack(arr2))) {
				System.out.println("Oops!");
				printArray(arr2);
				break;
			}
		}
	}
}
