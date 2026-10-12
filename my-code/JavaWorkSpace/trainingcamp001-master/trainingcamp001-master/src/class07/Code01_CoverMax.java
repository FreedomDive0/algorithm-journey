package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {

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

	public static int maxCover2(int[][] m) {
		Line[] lines = new Line[m.length];
		for (int i = 0; i < m.length; i++) {
			lines[i] = new Line(m[i][0], m[i][1]);
		}
		Arrays.sort(lines, new StartComparator());
		PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			while (!heap.isEmpty() && heap.peek().end <= lines[i].start) {
				heap.poll();
			}
			heap.add(lines[i]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

	public static class Line {
		public int start;
		public int end;

		public Line(int s, int e) {
			start = s;
			end = e;
		}
	}

	public static class StartComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.start - o2.start;
		}

	}

	public static class EndComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.end - o2.end;
		}

	}

	// for test
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

	// 复习
	// 2.最大线段重合问题
	public static int maxCover(int[][] lines) {
		if (lines == null || lines.length < 1) {
			return 0;
		}
		Line[] Lines = new Line[lines.length];
		int max = 0;
		int index = 0;
		for (int[] line : lines) {
			Lines[index++] = new Line(line[0], line[1]);
		}
		Arrays.sort(Lines, new startCom());// 按线段开头大小排序
		PriorityQueue<Line> queue = new PriorityQueue<>(new endCom());
		for (Line line : Lines) {
			int curStart = line.start;
			while (!queue.isEmpty() && curStart >= queue.peek().end) {
				queue.poll();
			}
			queue.add(line);
			max = Math.max(max, queue.size());
		}
		return max;
	}

	public static class startCom implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			// TODO Auto-generated method stub
			return o1.start - o2.start;
		}

	}

	public static class endCom implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			// TODO Auto-generated method stub
			return o1.end - o2.end;
		}

	}

	// 复习2025
	public static int maxCover3(int[][] lines) {
		if (lines == null || lines.length < 1) {
			return 0;
		}
		Line[] lineArr = new Line[lines.length];
		for (int i = 0; i < lineArr.length; i++) {
			lineArr[i] = new Line(lines[i][0], lines[i][1]);
		}
		Arrays.sort(lineArr, new LineStartCom());
		int max = 0;
		PriorityQueue<Line> heap = new PriorityQueue<Line>(new LineEndCom());
		for (int i = 0; i < lineArr.length; i++) {
			while (!heap.isEmpty() && heap.peek().end <= lineArr[i].start) {
				heap.poll();
			}
			heap.offer(lineArr[i]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

//	public static class Line {
//		public int start;
//		public int end;
//
//		public Line(int start, int end) {
//			this.start = start;
//			this.end = end;
//		}
//
//	}

	public static class LineStartCom implements Comparator<Line> {
		@Override
		public int compare(Line o1, Line o2) {
			return o1.start - o2.start;
		}
	}

	public static class LineEndCom implements Comparator<Line> {
		@Override
		public int compare(Line o1, Line o2) {
			return o1.end - o2.end;
		}
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover3(lines);
			int ans2 = maxCover2(lines);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}

}
