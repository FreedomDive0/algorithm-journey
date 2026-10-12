package class07;

import java.util.Arrays;
import java.util.HashSet;

public class Code02_SmallestUnFormedSum {
	public static int SmallestUnFormedSum(int[] arr) {
		if (arr == null | arr.length < 1) {
			return 0;
		}
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			min = Math.min(min, arr[i]);
		}
		HashSet<Integer> set = new HashSet<>();
		process(arr, 0, 0, set);
		for (int i = min + 1; i <= sum; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return sum + 1;
	}

	// 背包问题求出全部可能的结果加入set
	public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
		if (index == arr.length) {
			set.add(sum);
			return;
		}
		process(arr, index + 1, sum, set);
		process(arr, index + 1, sum + arr[index], set);
	}

	// 如果数组中存在1 即可优化 1可以让范围最小粒度变化 所以可以优化
	public static int SmallestUnFormedSum1(int[] arr) {
		if (arr == null | arr.length < 1) {
			return 0;
		}
		Arrays.sort(arr);
		int range = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > range + 1) {// 为什么是range+1 因为range表示1~range 如果下一个数为range+1 单独使用下一个数也可以构成
				return range + 1;
			} else {
				range += arr[i];// 原范围：1~range所以到i位置新范围 1~range+arr[i]
			}
		}
		return range + 1;
	}

}
