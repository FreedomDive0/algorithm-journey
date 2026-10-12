package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {
	// 线段重合问题
	public static int maxCover1(int[][] lines) {
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

	public static class Line {
		public int start;
		public int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

	}

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

}
