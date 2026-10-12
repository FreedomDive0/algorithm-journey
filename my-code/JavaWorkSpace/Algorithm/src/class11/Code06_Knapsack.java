package class11;

public class Code06_Knapsack {
	public static int maxValue(int[] weight, int[] value, int bag) {
		return process1(0, 0, weight, value, bag);
	}

	public static int process1(int index, int nowWeight, int[] weight, int[] value, int bag) {
//		能不能装
		if (nowWeight > bag) {
			return -1;
		}
//		还有没有
		if (index == weight.length) {
			return 0;
		}
		// p1和p2是当前选择的价值
		int p1 = process1(index + 1, nowWeight, weight, value, bag);
		// p2Next 是判断该物品可不可以装
		int p2Next = process1(index + 1, nowWeight + weight[index], weight, value, bag);
		int p2 = -1;
		if (p2Next != -1) {
			p2 = value[index] + p2Next;
		}
		return Math.max(p1, p2);
	}

	public static int maxValue1(int[] weight, int[] value, int bag) {
		return process2(0, bag, weight, value);
	}

	public static int process2(int index, int rest, int[] weight, int[] value) {
		if (rest < 0) {
			return -1;
		}
		if (index == weight.length) {
			return 0;
		}
		int p1 = process2(index + 1, rest, weight, value);
		int p2Next = process2(index + 1, rest - weight[index], weight, value);
		int p2 = -1;
		if (p2Next != -1) {
			p2 = p2Next + value[index];
		}
		return Math.max(p1, p2);
	}

//	动态规划
	public static int dpWay(int[] weight, int[] value, int bag) {
		int N = weight.length;
		int dp[][] = new int[N + 1][bag + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= bag; rest++) {
//				int p1 = dp[index+1][rest];
//				int p2 = rest >= weight[index] ? dp[index+1][rest-weight[index]] + value[index] : -1; 
//				dp[index][rest] = Math.max(p1, p2);
				dp[index][rest] = dp[index + 1][rest];
				if (rest >= weight[index]) {
					dp[index][rest] = Math.max(dp[index][rest], dp[index + 1][rest - weight[index]] + value[index]);
				}
			}
		}
		return dp[0][bag];
	}

	// 复习
	public static int bag(int[] values, int[] weights, int bag) {
		return processB(values, weights, 0, bag);
	}

	public static int processB(int[] values, int[] weights, int index, int rest) {
		if (rest <= 0) {
			return 0;// 代表方案无效
		}
		if (index == values.length) {
			return 0;
		}
		int no = processB(values, weights, index + 1, rest);
		int yes = Integer.MIN_VALUE;
		if (rest >= weights[index]) {
			yes = values[index] + processB(values, weights, index + 1, rest - weights[index]);
		}
		return Math.max(no, yes);
	}

	// 8.背包问题 动态规划
	public static int bagDP(int[] values, int[] weights, int bag) {
		int N = values.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= bag; j++) {
				int no = dp[i + 1][j];
				int yes = j >= weights[i] ? values[i] + dp[i + 1][j - weights[i]] : Integer.MIN_VALUE;
				dp[i][j] = Math.max(no, yes);
			}
		}
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(maxValue1(weights, values, bag));
		System.out.println(dpWay(weights, values, bag));
		System.out.println(bagDP(values, weights, bag));
	}
}
