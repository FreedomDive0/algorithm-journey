package class04;

import java.util.PriorityQueue;

public class heapCase {
	public static void heapSortCase(int arr[], int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int index = 0;
		for (; index <= k; index++) {// 加入k+1个元素
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; index++) {// 弹一个 加一个
			arr[i++] = heap.poll();
			heap.add(arr[index]);
		}
		while (!heap.isEmpty()) {// 堆内剩余元素加入数组
			arr[i++] = heap.poll();
		}
	}

	public static void main(String[] args) {

	}
}
