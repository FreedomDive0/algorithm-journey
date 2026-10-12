package class01;

import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {
	public static int[] windowMax(int[] arr, int w) {
		if (arr.length < w || arr == null || w < 1) {
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int index = 0;
		for (int R = 0; R < arr.length; R++) {
			// 找到合适位置 添加新值位置
			while (!queue.isEmpty() && arr[R] >= arr[queue.peekLast()]) {
				queue.pollLast();
			}
			queue.addLast(R);
			// 判断窗口大小是否达到要求
			if (R >= w - 1) {
				res[index++] = arr[queue.peekFirst()];
			}
			// 向右更新
			if (R - w + 1 == queue.peekFirst()) {
				queue.pollFirst();
			}
		}
		return res;
	}

	// for test
	public static int[] rightWay(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		int L = 0;
		int R = w - 1;
		while (R < arr.length) {
			int max = arr[L];
			for (int i = L + 1; i <= R; i++) {
				max = Math.max(max, arr[i]);

			}
			res[index++] = max;
			L++;
			R++;
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 复习
	// 1.假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值
	public static int[] SlideWindow(int[] arr, int W) {
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

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int w = (int) (Math.random() * (arr.length + 1));
			int[] ans1 = windowMax(arr, w);
			int[] ans2 = SlideWindow(arr, w);
			if (!isEqual(ans1, ans2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}
}
