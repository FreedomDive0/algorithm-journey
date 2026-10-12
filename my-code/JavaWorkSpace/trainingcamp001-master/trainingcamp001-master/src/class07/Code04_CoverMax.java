package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Code04_CoverMax {

	public static class Rectangle {
		public int left;
		public int right;
		public int down;
		public int up;

		public Rectangle(int left, int right, int down, int up) {
			this.left = left;
			this.right = right;
			this.down = down;
			this.up = up;
		}

		@Override
		public String toString() {
			return "left: " + left + " right: " + right + " down: " + down + " up: " + up;
		}

	}

	public static class DownComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.down != o2.down ? (o1.down - o2.down) : o1.toString().compareTo(o2.toString());
		}
	}

	public static class LeftComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.left != o2.left ? (o1.left - o2.left) : o1.toString().compareTo(o2.toString());
		}
	}

	public static class RightComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.right != o2.right ? (o1.right - o2.right) : o1.toString().compareTo(o2.toString());
		}
	}

	// 矩形数量是N
	// O(N*LogN)
	// +
	// O(N) * [ O(N) + O(N *LogN) ]
	public static int maxCover(Rectangle[] recs) {
		if (recs == null || recs.length == 0) {
			return 0;
		}
		// 根据down（底）排序
		Arrays.sort(recs, new DownComparator());
		// 可能会对当前底边的公共局域，产生影响的矩形
		// list -> treeSet(有序表表达)
		TreeSet<Rectangle> leftOrdered = new TreeSet<>(new LeftComparator());
		int ans = 0;
		// O(N)
		for (int i = 0; i < recs.length;) { // 依次考察每一个矩形的底边
			// 同样底边的矩形一批处理
			do {
				leftOrdered.add(recs[i++]);
			} while (i < recs.length && recs[i].down == recs[i - 1].down);
			// 清除顶<=当前底的矩形
			removeLowerOnCurDown(leftOrdered, recs[i - 1].down);
			// 维持了右边界排序的容器
			TreeSet<Rectangle> rightOrdered = new TreeSet<>(new RightComparator());
			for (Rectangle rec : leftOrdered) { // O(N)
				removeLeftOnCurLeft(rightOrdered, rec.left);
				rightOrdered.add(rec);// O(logN)
				ans = Math.max(ans, rightOrdered.size());
			}
		}
		return ans;
	}

	public static void removeLowerOnCurDown(TreeSet<Rectangle> set, int curDown) {
		List<Rectangle> removes = new ArrayList<>();
		for (Rectangle rec : set) {
			if (rec.up <= curDown) {
				removes.add(rec);
			}
		}
		for (Rectangle rec : removes) {
			set.remove(rec);
		}
	}

	public static void removeLeftOnCurLeft(TreeSet<Rectangle> rightOrdered, int curLeft) {
		List<Rectangle> removes = new ArrayList<>();
		for (Rectangle rec : rightOrdered) {
			if (rec.right > curLeft) {
				break;
			}
			removes.add(rec);
		}
		for (Rectangle rec : removes) {
			rightOrdered.remove(rec);
		}
	}

	// 暴力方法1
	public static int maxCoverRectangleBruteForce(Rectangle[] recs) {
		if (recs == null || recs.length == 0)
			return 0;
		int max = 0;
		for (int i = 0; i < recs.length; i++) {
			int cur = 0;
			for (int j = 0; j < recs.length; j++) {
				if (i == j)
					continue;
				if (isRectangleOverlap(recs[i], recs[j])) {
					cur++;
				}
			}
			max = Math.max(max, cur);
		}
		return max;
	}

	// 暴力方法1
	public static int maxCoverRectangleBruteForce1(Rectangle[] recs) {
		if (recs == null || recs.length == 0)
			return 0;
		int maxCover = 0;

		// 对候选点进行双重循环，统计覆盖数
		for (int i = 0; i < recs.length; i++) {
			int count = 0;
			for (int j = 0; j < recs.length; j++) {
				if (i == j)
					continue;
				if (isRectangleOverlap(recs[i], recs[j])) {
					count++;
				}
			}
			maxCover = Math.max(maxCover, count);
		}

		return maxCover;
	}

	public static boolean isRectangleOverlap(Rectangle r1, Rectangle r2) {
		return !(r1.right <= r2.left || r2.right <= r1.left || r1.up <= r2.down || r2.up <= r1.down);
	}

	// for test
	public static Rectangle[] generateRectangle(int N, int L, int R, int D, int U) {
		int size = (int) (Math.random() * N) + 1;
		Rectangle[] ans = new Rectangle[size];
		for (int i = 0; i < size; i++) {
			int a = L + (int) ((R - L + 1) * Math.random());
			int b = L + (int) ((R - L + 1) * Math.random());
			int c = D + (int) ((U - D + 1) * Math.random());
			int d = D + (int) ((U - D + 1) * Math.random());
			if (a == b) {
				b = a + 1;
			}
			if (c == d) {
				d = c + 1;
			}
			ans[i] = new Rectangle(Math.min(a, b), Math.max(a, b), Math.min(c, d), Math.max(c, d));
		}
		return ans;
	}

	// 复习2025
	public static class RectangelDownCom implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.down != o2.down ? o1.down - o2.down : o1.toString().compareTo(o2.toString());
		}
	}

	public static class RectangleLeftCom implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.left != o2.left ? o1.left - o2.left : o1.toString().compareTo(o2.toString());
		}
	}

	public static class RectangleRightCom implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.right != o2.right ? o1.right - o2.right : o1.toString().compareTo(o2.toString());
		}
	}

	public static int maxCoverRectangle(Rectangle[] recs) {
		if (recs == null || recs.length < 1) {
			return 0;
		}
		Arrays.sort(recs, new RectangelDownCom());
		TreeSet<Rectangle> list = new TreeSet<Rectangle>(new RectangleLeftCom());
		int max = 0;
		for (int i = 0; i < recs.length;) {
			int curDown = recs[i].down;
			removeUpLowRectangle(list, curDown);
			do {
				list.add(recs[i++]);
			} while (i < recs.length && recs[i].down == curDown);
			PriorityQueue<Rectangle> heap = new PriorityQueue<Rectangle>(new RectangleRightCom());
			for (Rectangle rectangle : list) {
				removeRightRectangle(heap, rectangle.left);
				heap.add(rectangle);
				max = Math.max(max, heap.size());
			}
		}
		return max;
	}

	public static void removeUpLowRectangle(TreeSet<Rectangle> set, int down) {
		List<Rectangle> removeList = new ArrayList<Rectangle>();
		for (Rectangle rectangle : set) {
			if (rectangle.up <= down) {
				removeList.add(rectangle);
			}
		}
		for (Rectangle rectangle : removeList) {
			set.remove(rectangle);
		}
	}

	public static void removeRightRectangle(PriorityQueue<Rectangle> heap, int left) {
		while (!heap.isEmpty() && heap.peek().right <= left) {
			heap.poll();
		}
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int D = 80;
		int U = 390;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			Rectangle[] reces = generateRectangle(N, L, R, D, U);
//			System.out.println();
			int ans1 = maxCover(reces);
			int ans2 = maxCoverRectangle(reces);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				System.out.println("ans1: " + ans1 + " ans2: " + ans2);
			}
		}
		System.out.println("test end");
	}
}
