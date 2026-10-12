package class12;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code06_Coffee {
	public static int minTime1(int[] arr, int n, int a, int b) {
		return process(arr, a, b, 0, 0);
	}

	public static int process(int drink[], int a, int b, int washLine, int index) {
		if (index == drink.length - 1) {
			return Math.min(Math.max(washLine, drink[drink.length - 1]) + a, drink[drink.length - 1] + b);
		}
		int washtime = Math.max(washLine, drink[index]) + a;// 该杯子洗完时间
		int p1 = process(drink, a, b, washtime, index + 1);// 剩下的杯子洗完的时间
		p1 = Math.max(p1, washtime);// 洗杯子所用时间
		int p2 = process(drink, a, b, washLine, index + 1);
		p2 = Math.max(p2, drink[index] + b);
		return Math.min(p1, p2);
	}

	// 机器 可以使用的时间 洗杯子所用的时间
	public static class Machine {
		public int timePoint;
		public int workTime;

		public Machine(int t, int w) {
			timePoint = t;
			workTime = w;
		}
	}

	public static class myComparator implements Comparator<Machine> {

		@Override
		public int compare(Machine o1, Machine o2) {
			return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
		}

	}

	public static int[] drinkTime(int arr[], int n) {
		PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new myComparator());
		int[] res = new int[n];
		for (int i = 0; i < arr.length; i++) {
			heap.add(new Machine(0, arr[i]));
		}
		for (int i = 0; i < n; i++) {
			Machine m = heap.poll();
			m.timePoint += m.workTime;
			res[i] = m.timePoint;
			heap.add(m);
		}
		return res;
	}

//	动态规划
	public static int dp(int drink[], int a, int b) {
		int maxWashTime = 0;
		int n = drink.length;
		for (int i = 0; i < drink.length; i++) {
			maxWashTime = Math.max(maxWashTime, drink[i]) + a;
		}
		int[][] dp = new int[n][maxWashTime + 1];
		for (int washLine = 0; washLine < maxWashTime; washLine++) {
			dp[n - 1][washLine] = Math.min(Math.max(washLine, drink[n - 1]) + a, drink[n - 1] + b);
		}
		for (int index = n - 2; index >= 0; index--) {
			for (int washline = 0; washline < maxWashTime; washline++) {
				int p1 = Integer.MAX_VALUE;// washline遍历maxWashTime有可能越界
				int washtime = Math.max(washline, drink[index]) + a;// 该杯子洗完时间
				if (washtime <= maxWashTime) {
					p1 = dp[index + 1][washtime];
				}
				p1 = Math.max(p1, washtime);// 洗杯子所用时间
				int p2 = dp[index + 1][washline];
				p2 = Math.max(p2, drink[index] + b);
				dp[index][washline] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	// for test
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		System.out.print("arr : ");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + ", ");
		}
		System.out.println();
	}

	// 复习
	public static int Wash(int[] drinks, int a, int b) {
		if (a >= b) {
			return drinks[drinks.length - 1] + b;
		}
		return processWH(drinks, a, b, 0, 0);
	}

	public static int processWH(int[] drinks, int a, int b, int index, int washLine) {
		if (index == drinks.length - 1) {
			return Math.max(washLine, drinks[index]) + a;
		}
		int washTime = Math.max(washLine, drinks[index]) + a;// 获得洗杯时间
		int washNext = Math.max(washTime, processWH(drinks, a, b, index + 1, washTime));// 洗完该杯和后序杯子取大的
		int dry = Math.max(processWH(drinks, a, b, index + 1, washLine), drinks[index] + b);
		return Math.min(washNext, dry);
	}

	// 5.1洗咖啡杯dp
	public static int WashDP(int[] drinks, int a, int b) {
		if (a >= b) {
			return drinks[drinks.length - 1] + b;
		}
		int maxWashTime = 0;
		for (int i = 0; i < drinks.length; i++) {
			maxWashTime = Math.max(maxWashTime, drinks[i]) + a;
		}
		int N = drinks.length;
		int[][] dp = new int[N][maxWashTime + 1];
		for (int i = 0; i <= maxWashTime; i++) {
			dp[N - 1][i] = Math.max(i, drinks[N - 1]) + a;
		}
		for (int index = N - 2; index >= 0; index--) {
			for (int washLine = 0; washLine <= maxWashTime; washLine++) {
				int washTime = Math.max(washLine, drinks[index]) + a;// 获得洗杯时间
				if (washTime <= maxWashTime) {
					washTime = Math.max(washTime, dp[index + 1][washTime]);// 洗完该杯和后序杯子取大的
				}
				int dry = Math.max(dp[index + 1][washLine], drinks[index] + b);
				dp[index][washLine] = Math.min(washTime, dry);
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] test = { 1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15 };
		int a1 = 3;
		int b1 = 10;
		System.out.println(WashDP(test, a1, b1));
		System.out.println(dp(test, a1, b1));

		int len = 5;
		int max = 9;
		int testTime = 50000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(len, max);
			int n = (int) (Math.random() * 5) + 1;
			int a = (int) (Math.random() * 5) + 1;
			int b = (int) (Math.random() * 10) + 1;
			int ans1 = minTime1(arr, n, a, b);
//				int ans2 = minTime2(arr, n, a, b);
//				int ans3 = minTime3(arr, n, a, b);
//				if (ans1 != ans2 || ans2 != ans3) {
//					printArray(arr);
//					System.out.println("n : " + n);
//					System.out.println("a : " + a);
//					System.out.println("b : " + b);
//					System.out.println(ans1 + " , " + ans2 + " , " + ans3);
//					System.out.println("===============");
//					break;
//				}
		}

	}
}
