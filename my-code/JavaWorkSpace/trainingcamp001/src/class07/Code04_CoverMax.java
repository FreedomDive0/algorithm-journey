package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Code04_CoverMax {
	public static class Rectangle {
		public int up;
		public int down;
		public int left;
		public int right;

		public Rectangle(int up, int down, int left, int right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}
	}

	public static class downComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.down - o2.down;
		}
	}

	public static class leftComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.left - o2.left;
		}
	}

	public static class rightComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.right - o2.right;
		}
	}

	public static int maxCover2(Rectangle[] rectangles) {
		Arrays.sort(rectangles, new downComparator());// 先将矩形按底边排序
		TreeSet<Rectangle> readyRectangle = new TreeSet<Rectangle>(new leftComparator());// 竖直方向有重合的矩形小组 左边按序排列
		int max = 0;
		for (int i = 0; i < rectangles.length; i++) {
			do {// 小加速 将底相同的矩形全部加入
				readyRectangle.add(rectangles[i++]);
			} while (i < rectangles.length && rectangles[i - 1].down == rectangles[i].down);
			removeDown(readyRectangle, rectangles[i - 1].down);// 剔除不符合的矩形
			TreeSet<Rectangle> curRectangle = new TreeSet<Rectangle>(new rightComparator());// 符合重合条件的矩形组
			for (Rectangle rectangle : readyRectangle) {
				removeRight(curRectangle, rectangle.left);
				curRectangle.add(rectangle);
				max = Math.max(max, curRectangle.size());
			}
		}
		return max;
	}

	public static void removeDown(TreeSet<Rectangle> r, int curDown) {
		List<Rectangle> list = new ArrayList<Rectangle>();
		for (Rectangle rectangle : r) {
			if (rectangle.up <= curDown) {
				list.add(rectangle);
			}
		}
		for (Rectangle rectangle : list) {
			r.remove(rectangle);
		}
	}

	public static void removeRight(TreeSet<Rectangle> r, int curLeft) {
		List<Rectangle> list = new ArrayList<Rectangle>();
		for (Rectangle rectangle : r) {
			if (rectangle.right > curLeft) {
				break;
			}
			list.add(rectangle);
		}
		for (Rectangle rectangle : list) {
			r.remove(rectangle);
		}
	}
}
