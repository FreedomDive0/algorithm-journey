package class12;

import java.util.Arrays;

public class Code04_SplitSumClosedHalf {
	public static int minSum1(int[] arr) {
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int n : arr) {
			sum += n;
		}
		if ((arr.length & 1) == 0) {
			return process(arr, 0, sum / 2, arr.length / 2);
		} else {
			return Math.max(process(arr, 0, sum / 2, arr.length / 2), process(arr, 0, sum / 2, arr.length / 2 + 1));
		}
	}

	public static int process(int[] arr, int index, int rest, int picks) {
		if (index == arr.length) {
			return picks == 0 ? 0 : -1;
		}
		int p1 = process(arr, index + 1, rest, picks);
		int p2 = -1;
		int next = -1;
		if (arr[index] <= rest) {
			next = process(arr, index + 1, rest - arr[index], picks - 1);
		}
		if (next != -1) {
			p2 = next + arr[index];
		}
		return Math.max(p1, p2);
	}

	public static int minSum(int[] arr) {
		int sum = 0;
		for (int n : arr) {
			sum += n;
		}
//		向上取整技巧 (n+1)/2
		int picks = (arr.length + 1) / 2;
		int n = arr.length;
		sum /= 2;
		int[][][] dp = new int[n + 1][sum + 1][picks + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
				for (int k = 0; k <= picks; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int i = 0; i <= sum; i++) {
			dp[n][i][0] = 0;
		}
		for (int index = n - 1; index >= 0; index--) {
			for (int p = 0; p <= picks; p++) {
				for (int rest = 0; rest <= sum; rest++) {
					int p1 = dp[index + 1][rest][p];
					int p2 = -1;
					int next = -1;
					// 这一层不能选下一层一定不能选
					if (p - 1 >= 0 && arr[index] <= rest) {
						next = dp[index + 1][rest - arr[index]][p - 1];
					}
					if (next != -1) {
						p2 = arr[index] + next;
					}
					dp[index][rest][p] = Math.max(p1, p2);
				}
			}
		}
		// 测试
//		System.out.println("数组长度：" + n + " sum：" + sum + " 数组长度half：" + picks);
//		for (int i = 0; i < dp.length; i++) {
//			System.out.println("level：" + i);
//			for (int j = 0; j < dp[0].length; j++) {
//				for (int k = 0; k < dp[0][0].length; k++) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//		}
		return (arr.length & 1) == 0 ? dp[0][sum][picks] : Math.max(dp[0][sum][picks], dp[0][sum][picks - 1]);
	}

	// 复习
	// 7.分数组长度也平分
	public static int SplitEqualNum(int[] arr) {
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;
		int size = arr.length / 2;
		if ((arr.length & 1) == 0) {
			return processSEN(arr, 0, sum, size);
		} else {
			return Math.max(processSEN(arr, 0, sum, size), processSEN(arr, 0, sum, size + 1));
		}
	}

	public static int processSEN(int[] arr, int index, int rest, int size) {
		if (index == arr.length) {
			return size == 0 ? 0 : -1;
		}
		int no = processSEN(arr, index + 1, rest, size);
		int yes = -1;
		int yesNext = -1;
		if (arr[index] <= rest) {
			yesNext = processSEN(arr, index + 1, rest - arr[index], size - 1);
		}
		if (yesNext != -1) {
			yes = yesNext + arr[index];
		}
		return Math.max(no, yes);
	}

	// 7.1分数组长度也平分DP
	public static int SplitEqualNumDP(int[] arr) {
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;
		int n = arr.length;
		int size = (n + 1) / 2;
		int[][][] dp = new int[n + 1][size + 1][sum + 1];
		for (int i = 0; i < dp.length; i++) {// 初始化
			for (int j = 0; j < dp[0].length; j++) {
				for (int k = 0; k < dp[0][0].length; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int rest = 0; rest <= sum; rest++) {// base case
			dp[n][0][rest] = 0;
		}
		for (int index = n - 1; index >= 0; index--) {
			for (int sizes = 0; sizes <= size; sizes++) {
				for (int rest = 0; rest <= sum; rest++) {
					int yes = -1;
					int yesNext = -1;
					if (sizes - 1 >= 0 && arr[index] <= rest) {
						yesNext = dp[index + 1][sizes - 1][rest - arr[index]];
					}
					if (yesNext != -1) {
						yes = yesNext + arr[index];
					}
					dp[index][sizes][rest] = Math.max(dp[index + 1][sizes][rest], yes);
				}
			}
		}
//		System.out.println("数组长度：" + n + " sum：" + sum + " 数组长度half：" + size);
//		for (int i = 0; i < dp.length; i++) {
//			System.out.println("level：" + i);
//			for (int j = 0; j < dp[0].length; j++) {
//				for (int k = 0; k < dp[0][0].length; k++) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//		}
		return (n & 1) == 0 ? dp[0][size][sum] : Math.max(dp[0][size][sum], dp[0][size + 1][sum]);

	}

	// 对数器
	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		// Math.random() [0,1)
		// Math.random() * N [0,N)
		// (int)(Math.random() * N) [0, N-1]
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			// [-? , +?]
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
//		int testTime = 5;
//		int maxSize = 50;
//		int maxValue = 10;
//		boolean succeed = true;
//		for (int i = 0; i < testTime; i++) {
//			int[] arr1 = generateRandomArray(maxSize, maxValue);
//			if (minSum1(arr1) != minSum1(arr1)) {
//				succeed = false;
//				break;
//			}
//		}
//		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
		int[] arr = new int[] { 9, 8, 3, 4, 6, 8, 7, 7, 4, 5, 6, 4, 5, 7, 8, 7, 5, 7 };
		System.out.println(minSum(arr));
		System.out.println(SplitEqualNumDP(arr));
	}
}
