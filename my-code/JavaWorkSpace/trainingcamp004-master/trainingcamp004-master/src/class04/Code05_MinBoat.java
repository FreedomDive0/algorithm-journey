package class04;

import java.util.Arrays;

public class Code05_MinBoat {

	// 请保证arr有序
	public static int minBoat(int[] arr, int limit) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		// Arrays.sort(arr);
		if (arr[N - 1] > limit) {
			return -1;
		}
		int lessR = -1;
		// 所有的人体重都不超过limit，继续讨论, <= limit / 2 最右的位置
		for (int i = N - 1; i >= 0; i--) {
			if (arr[i] <= (limit / 2)) {
				lessR = i;
				break;
			}
		}
		if (lessR == -1) {
			return N;
		}
		// <= limit / 2
		int L = lessR;
		int R = lessR + 1;
		int noUsed = 0; // 画X的数量统计，画对号的量(加工出来的)
		while (L >= 0) {
			int solved = 0; // 此时的[L]，让R画过了几个数
			while (R < N && arr[L] + arr[R] <= limit) {
				R++;
				solved++;
			}
			// R来到又不达标的位置
			if (solved == 0) {
				noUsed++;
				L--;
			} else { // 此时的[L]，让R画过了solved（>0）个数
				L = Math.max(-1, L - solved);
			}
		}
		int all = lessR + 1;// 左半区总个数 <= limit /2 的区域
		int used = all - noUsed; // 画对号的量
		int moreUnsolved = (N - all) - used; // > limit/2 区中，没搞定的数量
		return used + ((noUsed + 1) >> 1) + moreUnsolved;
	}

	// 测试
	public static int MinBoat(int[] arr, int limit) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		Arrays.sort(arr);
		int N = arr.length;
		if (arr[N - 1] > limit) {// 人的重量超过载重
			return -1;
		}

		int lessHalf = N - 1;
		for (; lessHalf >= 0; lessHalf--) {// 求出体重小于船载重一半最右的位置
			if (arr[lessHalf] <= limit / 2) {
				break;
			}
		}
		if (lessHalf == -1) {// 所有的人体重都超过limit的一半
			return arr.length;
		}
		int noUsed = 0;// X号
		int R = lessHalf + 1;
		int L = lessHalf;
		while (L >= 0) {
			int solved = 0;// 对号
			while (R < N && arr[L] + arr[R] <= limit) {// 两个能一起坐船
				solved++;
				R++;
			}
			if (solved == 0) {// 都不可以 L左移
				L--;
				noUsed++;
			} else {// 有的话 看左边能提供多少个
				L = Math.max(-1, L - solved);
			}
		}
		int leftAll = lessHalf + 1;// 左侧全部
		int leftUsed = leftAll - noUsed;// 左侧能搞定多少个
		int rightNoUsed = N - leftAll - leftUsed;// 右侧不能搞定多少
		return (noUsed + 1) / 2 + leftUsed + rightNoUsed;// 左侧不能搞定+1/2 + 左侧搞定 + 右侧不能搞定
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5 };
		int weight = 6;
		System.out.println(minBoat(arr, weight));
		System.out.println(MinBoat(arr, weight));
	}

}
