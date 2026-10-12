package class01;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {
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
				minQueue.addLast(r);// 记住添加下标
				if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
					break;
				}
				r++;
			}
			res += r - l;// 因为r位置已经不符合所以不用+1
			if (!maxQueue.isEmpty() && l == maxQueue.peekFirst()) {
				maxQueue.pollFirst();
			}
			if (!minQueue.isEmpty() && l == minQueue.peekFirst()) {
				minQueue.pollFirst();
			}
			l++;
//				r = l;//子数组都不符合条件所以子数组不需要在验证
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

	public static void main(String[] args) {
		int[] arr = getRandomArray(30);
		int num = 5;
		printArray(arr);
		System.out.println(allLessNumSubArray(arr, num));
//		System.out.println(allLessNumSubArray(arr, num));
	}
}
