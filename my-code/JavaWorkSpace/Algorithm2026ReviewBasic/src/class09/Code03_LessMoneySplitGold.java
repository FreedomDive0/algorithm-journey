package class09;

import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {
	// 1.分黄金全排列
	public static int lessMoneySplitGoldPermutation(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return lMSGProcess(arr, 0);
	}

	public static int lMSGProcess(int[] arr, int cost) {
		if (arr.length == 1) {
			return cost;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i == j) {
					continue;
				}
				min = Math.min(min, lMSGProcess(mergeAndDelete(arr, i, j), cost + arr[i] + arr[j]));
			}
		}
		return min;
	}

	public static int[] mergeAndDelete(int[] arr, int i, int j) {
		int[] res = new int[arr.length - 1];
		int idx = 0;
		for (int k = 0; k < arr.length; k++) {
			if (k == i || k == j) {
				continue;
			}
			res[idx++] = arr[k];
		}
		res[res.length - 1] = arr[i] + arr[j];
		return res;
	}

	// 2.分黄金贪心
	public static int lessMoneySplitGoldGreedy(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		PriorityQueue<Integer> golds = new PriorityQueue<Integer>();
		for (int i = 0; i < arr.length; i++) {
			golds.add(arr[i]);
		}
		int cost = 0;
		while (golds.size() > 1) {
			int gold1 = golds.poll();
			int gold2 = golds.poll();
			cost += gold1 + gold2;
			golds.add(gold1 + gold2);
		}
		return cost;
	}
}
