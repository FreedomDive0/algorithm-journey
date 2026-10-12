package class04;

import java.util.PriorityQueue;

public class Code05_SortArrayDistanceLessK {
	public static void SortArrayDistanceLessK(int[] arr, int k) {
		if (arr == null || arr.length < 2 || k < 1) {// || arr.length < k//题目有问题 不可能出现k>arr.length长度的情况
			return;
		}
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int idx = 0;
		for (; idx <= Math.min(k, arr.length - 1); idx++) {
			heap.add(arr[idx]);
		}
		int i = 0;
		for (; idx < arr.length; idx++, i++) {
			arr[i] = heap.poll();
			heap.add(arr[idx]);
		}
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}
}
