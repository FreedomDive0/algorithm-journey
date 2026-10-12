package class04;

import java.util.PriorityQueue;

public class Code05_SortArrayDistanceLessK {
	public static void sortArrayDistanceLessK(int[] arr, int k) {
		if (arr == null || arr.length < 2 || k < 1) {
			return;
		}
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int i = 0;
		for (; i <= Math.min(k, arr.length - 1); i++) {
			heap.add(arr[i]);
		}
		int j = 0;
		for (; i < arr.length; i++) {
			arr[j++] = heap.poll();
			heap.add(arr[i]);
		}
		while (!heap.isEmpty()) {
			arr[j++] = heap.poll();
		}
	}
}
