package class07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Review {
	// 1.无序数组 在子数组上排序 就能使整个数组有序 求子数组最短长度
	public static int minLengthForSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int lMax = arr[0];
		int rIndex = -1;
		for (int i = 1; i < arr.length; i++) {
			if (lMax > arr[i]) {
				rIndex = i;
			} else {
				lMax = Math.max(lMax, arr[i]);// 不发生错误才调整数值
			}
		}
		if (rIndex == -1) {// 如果没出现错误位置 则不需要调整
			return 0;
		}
		int rMin = arr[arr.length - 1];
		int lIndex = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (rMin < arr[i]) {
				lIndex = i;
			} else {
				rMin = Math.min(rMin, arr[i]);
			}
		}
		return rIndex - lIndex + 1;
	}

	// 2.数组的最小不可组成和
	// (1)背包解决
	public static int SmallestUnFormedSum(int[] arr) {
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

	// 3.给定有序正数数组和范围range 求最少还缺几个数让数组可以累加和范围1~range
	public static int minPatches3(int[] arr, int aim) {
		int patches = 0;// 补丁
		long range = 0;// long类型防越界
		for (int i = 0; i < arr.length; i++) {
			while (arr[i] > range + 1) {
				patches++;
				range += range + 1;
				if (range >= aim) {
					return patches;
				}
			}
			range += arr[i];
			if (range >= aim) {
				return patches;
			}
		}
		while (range < aim) {
			range += range + 1;
			patches++;
		}
		return patches;
	}

	// 4.arr中 两个数的公因子>1 认为两个数之间有通路 return有多少个独立的域
	// (1)直接求所有数的公因数 O(N^2)
	public static int LargestComponentSizeByCommonFactor(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		UnionFind unionFind = new UnionFind(arr.length);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (gcd(arr[i], arr[j]) > 1) {
					unionFind.union(i, j);
				}
			}
		}
		return unionFind.MaxSize();
	}

	// (2)用质数因子合并 O(N*V)
	// (3)用公约数合并 O(N*√v)
	public static int LargestComponentSizeByCommonFactor1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		UnionFind unionFind = new UnionFind(arr.length);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();// 存放公约数和对应下标
		for (int i = 0; i < arr.length; i++) {
			int limit = (int) Math.sqrt(arr[i]);
			for (int j = 1; j <= limit; j++) {// 从1开始 为了不漏掉arr[i]可能是质数
				if (arr[i] % j == 0) {
					if (j != 1) {
						if (!map.containsKey(j)) {
							map.put(j, i);
						} else {
							unionFind.union(i, map.get(j));
						}
					}
					int other = arr[i] / j;
					if (other != 1) {
						if (!map.containsKey(other)) {
							map.put(other, i);
						} else {
							unionFind.union(i, map.get(other));
						}
					}
				}
			}
		}
		return unionFind.MaxSize();
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(a, a % b);
	}

	public static class UnionFind {
		int[] parents;
		int[] sizes;
		int[] help;// 帮助扁平化的数组

		public UnionFind(int N) {
			parents = new int[N];
			sizes = new int[N];
			help = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
				sizes[i] = 1;
			}
		}

		public int findFather(int index) {
			int helpIndex = 0;
			while (index != parents[index]) {
				index = parents[index];
				help[helpIndex++] = index;// 扁平化记录
			}
			for (helpIndex--; helpIndex >= 0; helpIndex--) {// 扁平化处理
				parents[help[helpIndex]] = index;
			}
			return index;
		}

		public void union(int i, int j) {
			int headI = findFather(i);
			int headJ = findFather(j);
			if (headI != headJ) {
				int small = sizes[headI] > sizes[headJ] ? headJ : headI;
				int big = small == headJ ? headI : headJ;
				parents[small] = big;// 小挂大
				sizes[big] += sizes[small];
				sizes[small] = 0;
			}
		}

		public int MaxSize() {
			int max = 0;
			for (int size : sizes) {
				max = Math.max(max, size);
			}
			return max;
		}

	}

	// 5.在一个字符串中 寻找没用重复字符的最长子串
	public static int LongerstNoRepeatSubstring(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		return 1;
	}

	// 6.字符串中没有重复字符的最长子串
	public static int LongestNoRepeatSubString(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		char[] chs = str.toCharArray();
		int max = 0;
		int[] dp = new int[chs.length];
		dp[0] = 1;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put(chs[0], 0);
		for (int i = 1; i < chs.length; i++) {
			dp[i] = Math.min(dp[i - 1] + 1, i - map.getOrDefault(chs[i], -1));// 前一个位置答案 和 上一次出现该字符答案中选小的
			map.put(chs[i], i);
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
