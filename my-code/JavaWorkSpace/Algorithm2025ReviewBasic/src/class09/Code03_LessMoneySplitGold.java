package class09;

import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {
	// 贪心法 每次把最小两个黄金合并
	public static int splitGold(int[] golds) {
		if (golds == null || golds.length < 1) {
			return 0;
		}
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for (Integer gold : golds) {
			heap.add(gold);
		}
		int cost = 0;
		while (heap.size() > 1) {
			int g1 = heap.poll();
			int g2 = heap.poll();
			heap.add(g1 + g2);
			cost += g1 + g2;
		}
		return cost;
	}

	// 所有金条的组合方式
	public static int splitGold1(int[] golds) {
		if (golds == null || golds.length < 1) {
			return 0;
		}
		return processG(golds, 0);
	}

	public static int processG(int[] golds, int cost) {
		if (golds.length == 1) {
			return cost;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < golds.length; i++) {
			for (int j = i + 1; j < golds.length; j++) {// 因为只要组合不需要排序
				min = Math.min(min, processG(mergeTwo(golds, i, j), cost + golds[i] + golds[j]));
			}
		}
		return min;
	}

	public static int[] mergeTwo(int[] arr, int i, int j) {
		int[] res = new int[arr.length - 1];
		int idx = 0;
		for (int k = 0; k < arr.length; k++) {
			if (k != i && k != j) {
				res[idx++] = arr[k];
			}
		}
		res[idx] = arr[i] + arr[j];
		return res;
	}
}
