package class04;

import java.util.Arrays;

public class Code05_MinBoat {
	// 给定arr[] 是每个人的体重 limit船的限重 每条船最多坐两个人 求最少用几条船可以拉所有人
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
}
