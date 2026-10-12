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

//	如果两个矩形的 down 相同，而比较器只返回 0，那么这两个对象就被认为是“相等”。
//	在一些场景下（比如 TreeSet、TreeMap），如果 compare() 返回 0，会认为是同一个元素 → 导致丢数据。
//	所以需要一个 次要比较条件，确保不同对象不会被当成“相等”。
//	用 toString() 是一个偷懒写法：反正每个 Rectangle 的 toString() 一般会包含坐标信息（唯一性），就能当 tie-breaker。
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
//		System.out.println("-----------------removeUpLowRectangle---------------------");
		for (Rectangle rectangle : removeList) {
			set.remove(rectangle);
//			System.out.println(rectangle);
		}
//		System.out.println("-----------------removeUpLowRectangle---------------------");
	}

	public static void removeRightRectangle(PriorityQueue<Rectangle> heap, int left) {
//		System.out.println("-----------------removeRightRectangle---------------------");
		while (!heap.isEmpty() && heap.peek().right <= left) {
//			System.out.println(heap.poll());
			heap.poll();
		}
//		System.out.println("-----------------removeRightRectangle---------------------");
	}

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

	// 测试
	public static int maxCoverRectangleBruteForce(Rectangle[] recs) {
		if (recs == null || recs.length == 0)
			return 0;
		int n = recs.length;
		int maxCover = 0;

		// 枚举所有矩形边界形成的候选点
		List<Integer> xPoints = new ArrayList<>();
		List<Integer> yPoints = new ArrayList<>();
		for (Rectangle r : recs) {
			xPoints.add(r.left);
			xPoints.add(r.right);
			yPoints.add(r.down);
			yPoints.add(r.up);
		}

		// 对候选点进行双重循环，统计覆盖数
		for (int x : xPoints) {
			for (int y : yPoints) {
				int count = 0;
				for (Rectangle r : recs) {
					if (x >= r.left && x <= r.right && y >= r.down && y <= r.up) {
						count++;
					}
				}
				maxCover = Math.max(maxCover, count);
			}
		}

		return maxCover;
	}

	public static void main(String[] args) {
		System.out.println(maxCoverRectangleBruteForce(new Rectangle[] {}));
		// 预期: 0

		// Case 2: 只有一个矩形
		Rectangle[] case2 = { new Rectangle(1, 3, 1, 3) };
		System.out.println(maxCoverRectangleBruteForce(case2));
		// 预期: 1

		// Case 3: 两个矩形不重叠
		Rectangle[] case3 = { new Rectangle(1, 2, 1, 2), new Rectangle(3, 4, 3, 4) };
		System.out.println(maxCoverRectangleBruteForce(case3));
		// 预期: 1

		// Case 4: 两个矩形部分重叠
		Rectangle[] case4 = { new Rectangle(1, 4, 1, 4), new Rectangle(2, 5, 2, 5) };
		System.out.println(maxCoverRectangleBruteForce(case4));
		// 预期: 2

		// Case 5: 三个矩形完全重叠
		Rectangle[] case5 = { new Rectangle(1, 5, 1, 5), new Rectangle(2, 4, 2, 4), new Rectangle(3, 5, 3, 5) };
		System.out.println(maxCoverRectangleBruteForce(case5));
		// 预期: 3

		// Case 6: 多个交错矩形
		Rectangle[] case6 = { new Rectangle(1, 3, 1, 3), new Rectangle(2, 4, 2, 5), new Rectangle(3, 6, 1, 4),
				new Rectangle(5, 7, 2, 6), new Rectangle(6, 8, 1, 3) };
		System.out.println(maxCoverRectangleBruteForce(case6));
	}
}
