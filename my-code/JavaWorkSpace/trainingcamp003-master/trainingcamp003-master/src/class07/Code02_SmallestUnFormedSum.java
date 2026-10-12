package class07;

import java.util.Arrays;
import java.util.HashSet;

public class Code02_SmallestUnFormedSum {

	public static int unformedSum1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 1;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		process(arr, 0, 0, set);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i != arr.length; i++) {
			min = Math.min(min, arr[i]);
		}
		for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return 0;
	}

	public static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
		if (i == arr.length) {
			set.add(sum);
			return;
		}
		process(arr, i + 1, sum, set);
		process(arr, i + 1, sum + arr[i], set);
	}

	public static int unformedSum2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 1;
		}
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			min = Math.min(min, arr[i]);
		}
		// boolean[][] dp ...
		int N = arr.length;
		boolean[][] dp = new boolean[N][sum + 1];
		for (int i = 0; i < N; i++) {// arr[0..i] 0
			dp[i][0] = true;
		}
		dp[0][arr[0]] = true;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j] || ((j - arr[i] >= 0) ? dp[i - 1][j - arr[i]] : false);
			}
		}
		for (int j = min; j <= sum; j++) {
			if (!dp[N - 1][j]) {
				return j;
			}
		}
		return sum + 1;
	}

	// 已知arr中肯定有1这个数
	public static int unformedSum3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Arrays.sort(arr); // O (N * logN)
		int range = 1;
		// arr[0] == 1
		for (int i = 1; i != arr.length; i++) {
			if (arr[i] > range + 1) {
				return range + 1;
			} else {
				range += arr[i];
			}
		}
		return range + 1;
	}

	public static int[] generateArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) + 1;
		}
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
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
		process1(arr, 0, 0, set);
		for (int i = min + 1; i <= sum; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return sum + 1;
	}

	// 背包问题求出全部可能的结果加入set
	public static void process1(int[] arr, int index, int sum, HashSet<Integer> set) {
		if (index == arr.length) {
			set.add(sum);
			return;
		}
		process1(arr, index + 1, sum, set);
		process1(arr, index + 1, sum + arr[index], set);
	}

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

	// 测试
	// 2.数组的最小不可组成和
	// (1)背包解决
	public static int smallestUnFormedSum(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 1;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		processSUFS(arr, 0, 0, set);
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			sum += arr[i];
		}
		for (int range = min + 1; range < sum; range++) {
			if (!set.contains(range)) {
				return range;
			}
		}
		return sum + 1;
	}

	public static void processSUFS(int[] arr, int index, int sum, HashSet<Integer> set) {// 获取所有数字可能的累加结果
		if (index == arr.length) {
			set.add(sum);
			return;
		}
		processSUFS(arr, index + 1, sum, set);
		processSUFS(arr, index + 1, sum + arr[index], set);
	}

	// (2)如果arr一定存在1
	public static int SmallestUnformedSum1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 1;
		}
		Arrays.sort(arr);
		int range = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] <= range) {
				range += arr[i];
			} else {
				return range + 1;
			}
		}
		return range + 1;
	}

	public static void main(String[] args) {
		int len = 27;
		int max = 30;
		int[] arr = generateArray(len, max);
		printArray(arr);
		long start = System.currentTimeMillis();
		System.out.println(unformedSum2(arr));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + " ms");
		System.out.println("======================================");

		start = System.currentTimeMillis();
//		System.out.println(unformedSum2(arr));
		System.out.println(SmallestUnformedSum1(arr));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + " ms");
		System.out.println("======================================");

		System.out.println("set arr[0] to 1");
		arr[0] = 1;
		start = System.currentTimeMillis();
		System.out.println(unformedSum3(arr));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + " ms");

	}
}
