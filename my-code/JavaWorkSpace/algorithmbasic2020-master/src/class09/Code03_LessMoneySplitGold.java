package class09;

import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {

	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0);
	}

	public static int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}

	public static int lessMoney2(int[] arr) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pQ.add(arr[i]);
		}
		int sum = 0;
		int cur = 0;
		while (pQ.size() > 1) {
			cur = pQ.poll() + pQ.poll();
			sum += cur;
			pQ.add(cur);
		}
		return sum;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// 复习2026
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

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoneySplitGoldGreedy(arr) != lessMoney2(arr)) {
				System.out.println("Oops");
			}
		}
		System.out.println("finish!");
	}

}
