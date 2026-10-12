package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {
	public static class Line {
		private int s;
		private int e;

		public Line(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	public static class MyCompeterS implements Comparator<Line> {
		@Override
		public int compare(Line o1, Line o2) {
			return o1.s - o2.s;
		}
	}

	public static class MyCompeterE implements Comparator<Line> {
		@Override
		public int compare(Line o1, Line o2) {
			return o1.e - o2.e;
		}
	}

	public static int maxCover2(int[][] arr) {
		Line[] lines = new Line[arr.length];
		for (int i = 0; i < arr.length; i++) {
			lines[i] = new Line(arr[i][0], arr[i][1]);
		}
		Arrays.sort(lines, new MyCompeterS());// 按线段起始排序组合
		PriorityQueue<Line> curLines = new PriorityQueue<Line>(new MyCompeterE());// 每次考察的线段组
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			while (!curLines.isEmpty() && curLines.peek().e <= lines[i].s) {
				curLines.poll();// 弹出不重合线段
			}
			curLines.add(lines[i]);
			max = Math.max(max, curLines.size());
		}
		return max;
	}

	// for test
	public static int maxCover1(int[][] lines) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lines.length; i++) {
			min = Math.min(min, lines[i][0]);
			max = Math.max(max, lines[i][1]);
		}
		int cover = 0;
		for (double p = min + 0.5; p < max; p += 1) {
			int cur = 0;
			for (int i = 0; i < lines.length; i++) {
				if (lines[i][0] < p && lines[i][1] > p) {
					cur++;
				}
			}
			cover = Math.max(cover, cur);
		}
		return cover;
	}

	public static int[][] generateLines(int N, int L, int R) {
		int size = (int) (Math.random() * N) + 1;
		int[][] ans = new int[size][2];
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			if (a == b) {
				b = a + 1;
			}
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover1(lines);
			int ans2 = maxCover2(lines);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}
}
