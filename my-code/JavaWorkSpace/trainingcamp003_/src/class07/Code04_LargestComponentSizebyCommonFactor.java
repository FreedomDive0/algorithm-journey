package class07;

import java.util.HashMap;

public class Code04_LargestComponentSizebyCommonFactor {
	// 解法一
	public int largestComponentSize(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		UnionFind unionfind = new UnionFind(arr.length);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (gcd(arr[i], arr[j]) != 1) {
					unionfind.union(i, j);
				}
			}
		}
		return unionfind.MaxSize();
	}

	// 辗转相除法 求两数的最大公约数 a，b不能同时为0
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static class Node {
		int value;

		public Node(int value) {
			this.value = value;
		}

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

	// 解法二 以每个数的公约数为合并条件
	public static int largestComponentSize1(int[] num) {
		if (num == null || num.length < 1) {
			return 0;
		}
		UnionFind set = new UnionFind(num.length);
		HashMap<Integer, Integer> map = new HashMap<>();// 存放因子为key的下标
		for (int i = 0; i < num.length; i++) {
			int limit = (int) Math.sqrt(num[i]);// 每次处理一对因子 循环次数减少 不需要求全部因子
			for (int j = 1; j <= limit; j++) {// 从1开始是为了 other另一个因子
				if (num[i] % j == 0) {
					if (j != 1) {
						if (!map.containsKey(j)) {
							map.put(j, i);
						} else {
							set.union(i, map.get(j));// ∵之前已经合并过有相同公约数的部分 所以不用记录全部位置
						}
					}
					int other = num[i] / j;
					if (other != 1) {
						if (!map.containsKey(other)) {
							map.put(other, i);
						} else {
							set.union(i, map.get(other));// ∵之前已经合并过有相同公约数的部分 所以不用记录全部位置
						}
					}
				}
			}
		}
		return set.MaxSize();
	}

	public static void main(String[] args) {
		System.out.println(gcd(123, 456));
	}
}
