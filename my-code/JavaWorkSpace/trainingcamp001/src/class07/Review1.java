package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Review1 {
	// 1.线段树
	public static class SegmentTree {
		int maxN;
		int[] arr;
		int[] sum;
		int[] lazy;
		boolean[] update;
		int[] change;

		public SegmentTree(int[] origin) {
			maxN = origin.length + 1;// 4倍大小足够满足所有情况 最差情况N == 2^x+1 2*(N-1)+2*(N-1)-1==>4*N-5
			arr = new int[maxN];
			for (int i = 1; i < maxN; i++) {
				arr[i] = origin[i - 1];
			}
			sum = new int[maxN << 2];
			lazy = new int[maxN << 2];
			update = new boolean[maxN << 2];
			change = new int[maxN << 2];
		}

		public void build(int l, int r, int rt) {
			if (l == r) {
				sum[rt] = arr[l];
				return;
			}
			int mid = (l + r) >> 1;
			build(l, mid, rt << 1);
			build(mid + 1, r, rt << 1 | 1);
			pushUp(rt);
		}

		private void pushUp(int rt) {
			sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
		}

		public void add(int L, int R, int addNum, int l, int r, int rt) {
			if (L <= l && r <= R) {
				sum[rt] += (r - l + 1) * addNum;
				lazy[rt] += addNum;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				add(L, R, addNum, l, mid, rt << 1);
			}
			if (R > mid) {
				add(L, R, addNum, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		public void update(int L, int R, int updateNum, int l, int r, int rt) {
			if (L <= l && r <= R) {
				sum[rt] = (r - l + 1) * updateNum;
				lazy[rt] = 0;
				update[rt] = true;
				change[rt] = updateNum;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				update(L, R, updateNum, l, mid, rt << 1);
			}
			if (R > mid) {
				update(L, R, updateNum, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		public long query(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				return sum[rt];
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			long ans = 0;
			if (L <= mid) {
				ans += query(L, R, l, mid, rt << 1);
			}
			if (R > mid) {
				ans += query(L, R, mid + 1, r, rt << 1 | 1);
			}
			return ans;
		}

		private void pushDown(int rt, int ln, int rn) {
			if (update[rt]) {
				update[rt << 1] = true;
				update[rt << 1 | 1] = true;
				change[rt << 1] = change[rt];
				change[rt << 1 | 1] = change[rt];
				sum[rt << 1] = change[rt] * ln;
				sum[rt << 1 | 1] = change[rt] * rn;
				lazy[rt << 1] = 0;
				lazy[rt << 1 | 1] = 0;
				change[rt] = 0;
				update[rt] = false;
			}
			if (lazy[rt] != 0) {
				lazy[rt << 1] += lazy[rt];
				lazy[rt << 1 | 1] += lazy[rt];
				sum[rt << 1] += lazy[rt] * ln;
				sum[rt << 1 | 1] += lazy[rt] * rn;
				lazy[rt] = 0;
			}
		}
	}

	// 2.方块下落
	public class SegmentTree1 {
		int N;
		int[] max;
		boolean[] update;
		int[] change;

		public SegmentTree1(int size) {
			N = size;// 4倍大小足够满足所有情况 最差情况N == 2^x+1 2*(N-1)+2*(N-1)-1==>4*N-5
			max = new int[N << 2];
			update = new boolean[this.N << 2];
			change = new int[this.N << 2];
		}

		private void pushUp(int rt) {
			max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
		}

		public void update(int L, int R, int updateNum, int l, int r, int rt) {
			if (L <= l && r <= R) {
				max[rt] = updateNum;
				update[rt] = true;
				change[rt] = updateNum;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				update(L, R, updateNum, l, mid, rt << 1);
			}
			if (R > mid) {
				update(L, R, updateNum, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		public int query(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				return max[rt];
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			int left = 0;
			int right = 0;
			if (L <= mid) {
				left = query(L, R, l, mid, rt << 1);
			}
			if (R > mid) {
				right = query(L, R, mid + 1, r, rt << 1 | 1);
			}
			return Math.max(left, right);
		}

		private void pushDown(int rt, int ln, int rn) {
			if (update[rt]) {
				update[rt << 1] = true;
				update[rt << 1 | 1] = true;
				change[rt << 1] = change[rt];
				change[rt << 1 | 1] = change[rt];
				max[rt << 1] = change[rt];
				max[rt << 1 | 1] = change[rt];
				change[rt] = 0;
				update[rt] = false;
			}
		}
	}

	public HashMap<Integer, Integer> index(int[][] position) {// 坐标离散化
		TreeSet<Integer> set = new TreeSet<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int[] pos : position) {
			set.add(pos[0]);
			set.add(pos[0] + pos[1] - 1);
		}
		int count = 0;
		for (Integer num : set) {
			map.put(num, ++count);
		}
		return map;
	}

	public List<Integer> fallingSquares(int[][] position) {
		HashMap<Integer, Integer> map = index(position);
		SegmentTree1 segmentTree = new SegmentTree1(map.size() + 1);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int N = map.size();
		int maxHeight = 0;
		for (int[] pos : position) {
			int l = map.get(pos[0]);
			int r = map.get(pos[0] + pos[1] - 1);
			int height = segmentTree.query(l, r, 1, N, 1) + pos[1];
			segmentTree.update(l, r, height, 1, N, 1);
			maxHeight = Math.max(height, maxHeight);
			ans.add(maxHeight);
		}
		return ans;
	}

	// 3.最大线段重合问题
	public static class Line {
		int start;
		int end;

		public Line(int l, int r) {
			start = l;
			end = r;
		}

	}

	public static class lineStartComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			// TODO Auto-generated method stub
			return o1.start - o2.start;
		}

	}

	public static class lineEndComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			// TODO Auto-generated method stub
			return o1.end - o2.end;
		}

	}

	public static int maxCoverLine(int[][] lines) {
		if (lines == null || lines.length < 1) {
			return -1;
		}
		ArrayList<Line> list = new ArrayList<Line>();
		for (int i = 0; i < lines.length; i++) {
			list.add(new Line(lines[i][0], lines[i][1]));
		}
		list.sort(new lineStartComparator());
		PriorityQueue<Line> heap = new PriorityQueue<Line>(new lineEndComparator());
		int max = 0;
		for (Line l : list) {
			while (!heap.isEmpty() && heap.peek().end <= l.start) {
				heap.poll();
			}
			heap.add(l);
			max = Math.max(max, heap.size());
		}
		return max;
	}

	// 4.最大矩形重合问题
	public static class Rectangle {
		int up;
		int down;
		int left;
		int right;

		public Rectangle(int up, int down, int left, int right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}

	}

	public static class rectangleDownComparator implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			// TODO Auto-generated method stub
//			return o1.down != o2.down ? (o1.down - o2.down) : o1.toString().compareTo(o2.toString());//为什么
			return o1.down - o2.down;
		}

	}

	public static class rectangleLeftComparator implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			// TODO Auto-generated method stub
			return o1.left - o2.left;
		}

	}

	public static class rectangleRightComparator implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			// TODO Auto-generated method stub
			return o1.right - o2.right;
		}

	}

	public static int maxCoverRectangle(Rectangle[] rectangles) {
		if (rectangles == null || rectangles.length < 1) {
			return -1;
		}
		int max = 0;
		Arrays.sort(rectangles, new rectangleDownComparator());
		TreeSet<Rectangle> leftSet = new TreeSet<Rectangle>(new rectangleLeftComparator());
		for (int i = 0; i < rectangles.length;) {
			do {
				leftSet.add(rectangles[i++]);
			} while (i < rectangles.length && rectangles[i].down == rectangles[i - 1].down);
			removeDownBelow(leftSet, rectangles[i - 1].down);
			TreeSet<Rectangle> rightSet = new TreeSet<Rectangle>(new rectangleRightComparator());
			for (Rectangle rec : leftSet) {
				rightSet.add(rec);
				removeRightLeft(rightSet, rec.left);
				max = Math.max(max, rightSet.size());
			}
		}
		return max;
	}

	private static void removeRightLeft(TreeSet<Rectangle> rightSet, int left) {
		ArrayList<Rectangle> remove = new ArrayList<Rectangle>();
		for (Rectangle rec : rightSet) {
			if (rec.right > left) {
				break;
			}
			remove.add(rec);
		}
		for (Rectangle rec : remove) {
			rightSet.remove(rec);
		}
	}

	private static void removeDownBelow(TreeSet<Rectangle> leftSet, int curDown) {
		ArrayList<Rectangle> remove = new ArrayList<Rectangle>();
		for (Rectangle rec : leftSet) {
			if (rec.up <= curDown) {
				remove.add(rec);
			}
		}
		for (Rectangle rec : remove) {
			leftSet.remove(rec);
		}
	}
}
