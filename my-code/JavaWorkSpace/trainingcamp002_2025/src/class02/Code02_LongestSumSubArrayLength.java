package class02;

import java.util.HashMap;

public class Code02_LongestSumSubArrayLength {
	// 正负零 数组求最长和为k的子数组长度
	// 以xx结尾解决问题
	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		HashMap<Integer, Integer> firstSumIdx = new HashMap<Integer, Integer>();
		firstSumIdx.put(0, -1);
		int len = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (firstSumIdx.containsKey(sum - k)) {
				len = Math.max(len, i - firstSumIdx.get(sum - k));
			}
			if (!firstSumIdx.containsKey(sum)) {
				firstSumIdx.put(sum, i);
			}
		}
		return len;
	}
}
