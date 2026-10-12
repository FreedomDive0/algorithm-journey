package class08;

import java.util.HashSet;

public class Code05_LongestIntegratedLength {
	public static int longestIntegratedLength(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int ans = 0;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.clear();
			int max = arr[i];
			int min = arr[i];
			set.add(arr[i]);
			for (int j = i + 1; j < arr.length; j++) {
				if (set.contains(arr[j])) {// 过滤条件1 没有重复数字
					break;
				}
				set.add(arr[j]);
				max = Math.max(max, arr[j]);
				min = Math.min(min, arr[j]);
				if (max - min == j - i) {// 条件2 当前子数组可以整合了
					ans = Math.max(j - i + 1, ans);
				}
			}
		}
		return ans;
	}

	// 测试
	// 5.可整合数组
	public static int LongestIntegratedLength(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		int len = 0;
		for (int L = 0; L < arr.length; L++) {
			set.clear();
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int R = L; R < arr.length; R++) {
				if (set.contains(arr[R])) {
					break;
				}
				set.add(arr[R]);
				min = Math.min(min, arr[R]);
				max = Math.max(max, arr[R]);
				if (max - min == R - L) {
					len = Math.max(len, R - L + 1);
				}
			}
		}
		return len;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
		System.out.println(longestIntegratedLength(arr));
		System.out.println(LongestIntegratedLength(arr));
	}
}
