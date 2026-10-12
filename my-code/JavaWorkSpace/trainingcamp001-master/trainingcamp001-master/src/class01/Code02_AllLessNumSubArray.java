package class01;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

	public static int getNum(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qmin = new LinkedList<Integer>();
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int L = 0;
		int R = 0;
		// [L..R) -> [0,0) 窗口内无数 [1,1)
		// [0,1) -> [0~0]
		int res = 0;
		while (L < arr.length) { // L是开头位置，尝试每一个开头

			// 如果此时窗口的开头是L,下面的while工作:R向右扩到违规为止

			while (R < arr.length) { // R是最后一个达标位置的再下一个
				while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
					qmin.pollLast();
				}
				qmin.addLast(R);
				// R -> arr[R],
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
					qmax.pollLast();
				}
				qmax.addLast(R);

				if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
					break;
				}
				R++;
			}

			// R是最后一个达标位置的再下一个，第一个违规的位置
			res += R - L;

			if (qmin.peekFirst() == L) {
				qmin.pollFirst();
			}
			if (qmax.peekFirst() == L) {
				qmax.pollFirst();
			}

			L++;

		}
		return res;
	}

	// for test
	public static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	// 复习2025
	// 2.arr[]的子数组中sub[]满足max-min<=num的个数
	public static int allLessNumSubArray(int[] arr, int num) {
		if (arr == null || arr.length < 1 || num < 0) {
			return 0;
		}
		LinkedList<Integer> maxQueue = new LinkedList<Integer>();
		LinkedList<Integer> minQueue = new LinkedList<Integer>();
		int res = 0;
		int l = 0;
		int r = 0;
		while (l < arr.length) {
			while (r < arr.length) {
				while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[r]) {
					maxQueue.pollLast();
				}
				maxQueue.addLast(r);
				while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[r]) {
					minQueue.pollLast();
				}
				minQueue.addLast(r);
				if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
					break;
				}
				r++;
			}
			res += r - l;
			if (!maxQueue.isEmpty() && l == maxQueue.peekFirst()) {
				maxQueue.pollFirst();
			}
			if (!minQueue.isEmpty() && l == minQueue.peekFirst()) {
				minQueue.pollFirst();
			}
			l++;
//			r = l;//子数组都不符合条件所以子数组不需要在验证
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = getRandomArray(30);
		int num = 5;
		printArray(arr);
		System.out.println(getNum(arr, num));
		System.out.println(allLessNumSubArray(arr, num));
	}

}
