package class11;

public class Code07_Knapsack {

	public static int getMaxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, 0, bag);
	}

	// 不变 ： w[] v[] bag
	// index... 最大价值
	// 0..index-1上做了货物的选择，使得你已经达到的重量是多少alreadyW
	// 如果返回-1，认为没有方案
	// 如果不返回-1，认为返回的值是真实价值
	public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
		if (alreadyW > bag) {
			return -1;
		}
		// 重量没超
		if (index == w.length) {
			return 0;
		}
		int p1 = process(w, v, index + 1, alreadyW, bag);
		int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
		int p2 = -1;
		if (p2next != -1) {
			p2 = v[index] + p2next;
		}
		return Math.max(p1, p2);

	}

	public static int maxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, bag);
	}

	// 只剩下rest的空间了，
	// index...货物自由选择，但是剩余空间不要小于0
	// 返回 index...货物能够获得的最大价值
	public static int process(int[] w, int[] v, int index, int rest) {
		if (rest < 0) { // base case 1
			return -1;
		}
		// rest >=0
		if (index == w.length) { // base case 2
			return 0;
		}
		// 有货也有空间
		int p1 = process(w, v, index + 1, rest);
		int p2 = -1;
		int p2Next = process(w, v, index + 1, rest - w[index]);
		if (p2Next != -1) {
			p2 = v[index] + p2Next;
		}
		return Math.max(p1, p2);
	}

	public static int dpWay(int[] w, int[] v, int bag) {
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 1; rest <= bag; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest >= w[index]) {
					dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index + 1][rest - w[index]]);
				}
			}
		}
		return dp[0][bag];
	}

	// 测试
	// 背包问题
	public static int bag2(int[] w, int[] v, int bag) {
		return processG(w, v, 0, bag);
	}

	public static int processG(int[] w, int[] v, int idx, int rest) {
		if (idx == w.length) {
			return 0;
		}
		int no = processG(w, v, idx + 1, rest);
		int yes = 0;
		if (rest >= w[idx]) {
			yes = processG(w, v, idx + 1, rest - w[idx]) + v[idx];
		}
		return Math.max(no, yes);
	}

	public static int bag1(int[] values, int[] weights, int bag) {
		return processB1(values, weights, 0, bag);
	}

	public static int processB1(int[] values, int[] weights, int index, int rest) {
		if (rest <= 0) {
			return 0;
		}
		if (index == values.length) {
			return 0;
		}
		int p1 = processB1(values, weights, index + 1, rest);
		int p2 = 0;
		if (rest >= weights[index]) {
			p2 = values[index] + processB1(values, weights, index + 1, rest - weights[index]);
		}
		return Math.max(p1, p2);
	}

	// (3)dp
	public static int bag2DP(int[] values, int[] weights, int bag) {
		int[][] dp = new int[values.length + 1][bag + 1];
		for (int row = values.length - 1; row >= 0; row--) {
			for (int col = 1; col <= bag; col++) {
				dp[row][col] = dp[row + 1][col];
				if (col >= weights[row]) {
					dp[row][col] = Math.max(dp[row][col], values[row] + dp[row + 1][col - weights[row]]);
				}
			}
		}
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(bag2(weights, values, bag));
		System.out.println(dpWay(weights, values, bag));
	}

}
