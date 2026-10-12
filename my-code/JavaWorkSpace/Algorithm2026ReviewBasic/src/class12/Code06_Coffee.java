package class12;

import java.util.Arrays;

//题目
//数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
//现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
//认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
//每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
//洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
//四个参数：arr, n, a, b
//假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
public class Code06_Coffee {
	// 方法一：暴力尝试方法 arr有序
	public static int washCup(int[] arr, int n, int a, int b) {
		Arrays.sort(arr);
		return wCProcess(arr, 0, 0, a, b);
	}

	public static int wCProcess(int[] drinks, int idx, int washLine, int a, int b) {
		if (idx == drinks.length - 1) {
			return Math.min(drinks[idx] + b, Math.max(drinks[idx], washLine) + a);
		}
		int noNext = wCProcess(drinks, idx + 1, washLine, a, b);
		int yesNext = wCProcess(drinks, idx + 1, Math.max(washLine, drinks[idx]) + a, a, b);
		int no = Math.max(drinks[idx] + b, noNext);
		int yes = Math.max(Math.max(drinks[idx], washLine) + a, yesNext);
		return Math.min(no, yes);
	}

	public static int washCupDP(int[] drinks, int a, int b) {
		if (drinks == null || drinks.length == 0) {
			return 0;
		}
		Arrays.sort(drinks);
		if (a >= b) {
			return drinks[drinks.length - 1] + b;
		}
		int washLimit = 0;
		for (int i = 0; i < drinks.length; i++) {
			washLimit = Math.max(washLimit, drinks[i]) + a;
		}
		int n = drinks.length;
		int[][] dp = new int[n][washLimit + 1];
		for (int washLine = 0; washLine < washLimit; washLine++) {
			dp[n - 1][washLine] = Math.min(drinks[n - 1] + b, Math.max(drinks[n - 1], washLine) + a);
		}
		for (int idx = n - 2; idx >= 0; idx--) {
			for (int washLine = 0; washLine < washLimit; washLine++) {
				int yes = Integer.MAX_VALUE;
				int washTime = Math.max(washLine, drinks[idx]) + a;
				if (washTime <= washLimit) {
					yes = Math.max(dp[idx + 1][washTime], washTime);
//					yes = dp[idx + 1][washTime];//错误
				}
				int no = Math.max(drinks[idx] + b, dp[idx + 1][washLine]);
				dp[idx][washLine] = Math.min(no, yes);
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 3, 6, 8, 9 };
		int a = 3;
		int b = 5;
		System.out.println(washCup(arr, 5, a, b));
		System.out.println(washCupDP(arr, a, b));
	}
}
