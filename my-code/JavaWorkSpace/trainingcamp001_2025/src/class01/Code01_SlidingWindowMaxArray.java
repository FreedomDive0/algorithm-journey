package class01;

import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {
	// leetcode239 滑动窗口最大值
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr.length < w) {// 窗口比数组大直接返回最大值
			int max = arr[0];
			for (int num : arr) {
				max = Math.max(max, num);
			}
			int[] res = new int[1];
			res[0] = max;
			return res;
		}
		int[] window = new int[arr.length - w + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int l = 0;
		int r = 0;// [l,r)
		int idx = 0;
		while (r < arr.length) {
			// 加入队列
			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[r]) {
				queue.pollLast();
			}
			queue.addLast(r);
			r++;
			// 弹出队列
			if (r >= w) {
				window[idx++] = arr[queue.peekFirst()];
				if (l == queue.peekFirst()) {
					queue.pollFirst();
				}
				l++;
			}
		}
		return window;
	}
}
