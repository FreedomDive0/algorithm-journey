package class12;

public class Code03_SplitSumClosed {
	public static int minSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		int n = arr.length;
		for (int num : arr) {
			sum += num;
		}
		sum /= 2;
		int[][] dp = new int[arr.length + 1][sum + 1];
		for (int index = arr.length - 1; index >= 0; index--) {
			for (int rest = 0; rest <= sum; rest++) {
				int p1 = dp[index + 1][rest];
				int p2 = 0;
				if (arr[index] <= rest) {
					p2 = arr[index] + dp[index + 1][rest - arr[index]];
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][sum];
	}

	// 复习
	public static int SplitNum(int[] arr) {
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return processSN(arr, 0, 0, 0, sum);
	}

	public static int processSN(int[] arr, int index, int n1, int n2, int sum) {
		if (index == arr.length) {
			return n1 > n2 ? n2 : n1;
		}
		int r1 = processSN(arr, index + 1, n1 + arr[index], n2, sum);
		int r2 = processSN(arr, index + 1, n1, n2 + arr[index], sum);
		return Math.abs(sum - r1 * 2) > Math.abs(sum - r2 * 2) ? r2 : r1;
	}

	// 6.1分数组 根据业务逻辑分析设计参数
	public static int SplitNumNew(int[] arr) {// 参数过多不合适dp
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return processSN1(arr, 0, 0, sum / 2);
	}

	public static int processSN1(int[] arr, int index, int r1, int sum) {
		if (index == arr.length) {
			return r1 <= sum ? r1 : -1;
		}
		int no = processSN1(arr, index + 1, r1, sum);
		int yes = processSN1(arr, index + 1, r1 + arr[index], sum);
		return Math.max(no, yes);
	}

	// 6.2分数组DP 根据业务逻辑分析设计参数
	public static int SplitNumNewDP(int[] arr) {// 参数过多不合适dp
		if (arr == null) {
			return 0;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;
		int N = arr.length;
		int[][] dp = new int[N + 1][sum + 1];
		for (int i = 0; i < sum + 1; i++) {
			dp[N][i] = i <= sum ? i : -1;
		}
		for (int index = N - 1; index >= 0; index--) {
			for (int r1 = sum; r1 >= 0; r1--) {
				dp[index][r1] = Math.max(dp[index + 1][r1], arr[index] + r1 > sum ? 0 : dp[index + 1][r1 + arr[index]]);
			}
		}
//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < sum + 1; j++) {
//				System.out.print(dp[i][j] + "\t");
//			}
//			System.out.println();
//		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 6, 7, 8, 9, 11, 12 };
		System.out.println(minSum(arr));
		System.out.println(SplitNumNewDP(arr));
	}
}
