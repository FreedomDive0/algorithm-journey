package class07;

import java.util.Arrays;

public class Code03_MinPatches {

	// arr请保证有序，且正数
	public static int minPatches(int[] arr, int aim) {
		int patches = 0; // 缺多少个数字
		long range = 0; // 已经完成了1 ~ range的目标
		Arrays.sort(arr);
		for (int i = 0; i != arr.length; i++) {
			// 1~range
			// 1 ~ arr[i]-1
			while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
				range += range + 1; // range + 1 是缺的数字
				patches++;
				if (range >= aim) {
					return patches;
				}
			}
			range += arr[i];
			if (range >= aim) {
				return patches;
			}
		}
		while (aim >= range + 1) {
			range += range + 1;
			patches++;
		}
		return patches;
	}

	public static int minPatches2(int[] arr, int K) {
		int patches = 0; // 缺多少个数字
		int range = 0; // 已经完成了1 ~ range的目标
		for (int i = 0; i != arr.length; i++) {
			// 1~range
			// 1 ~ arr[i]-1
			while (arr[i] > range + 1) { // arr[i] 1 ~ arr[i]-1

				if (range > Integer.MAX_VALUE - range - 1) {
					return patches + 1;
				}

				range += range + 1; // range + 1 是缺的数字
				patches++;
				if (range >= K) {
					return patches;
				}
			}
			if (range > Integer.MAX_VALUE - arr[i]) {
				return patches;
			}
			range += arr[i];
			if (range >= K) {
				return patches;
			}
		}
		while (K >= range + 1) {
			if (K == range && K == Integer.MAX_VALUE) {
				return patches;
			}
			if (range > Integer.MAX_VALUE - range - 1) {
				return patches + 1;
			}
			range += range + 1;
			patches++;
		}
		return patches;
	}

	// 测试
	public static int minPatches1(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 1) {
			return -1;
		}
		long range = 0;
		int patches = 0;
		for (int i = 0; i < arr.length; i++) {
			while (range + 1 < arr[i]) {
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
		while (range + 1 <= aim) {
			range += range + 1;
			patches++;
		}
		return patches;
	}

	// 测试
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

	public static void main(String[] args) {
		int[] test = { 1, 2, 31, 33 };
		int n = 2147483647;
		System.out.println(minPatches1(test, n));
		System.out.println(minPatches3(test, n));

	}

}
