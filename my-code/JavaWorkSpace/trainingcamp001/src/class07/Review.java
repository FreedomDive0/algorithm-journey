package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Review {
	// 1.线段树
	public static class SegmentTree1 {
		int[] orign;
		int[] sum;
		int[] lazy;
		boolean[] update;
		int[] change;
		int N;

		public SegmentTree1(int[] orign) {
			this.orign = new int[orign.length + 1];
			N = orign.length + 1;
			sum = new int[N << 2];
			lazy = new int[N << 2];
			update = new boolean[N << 2];
			change = new int[N << 2];
			for (int i = 0; i < orign.length; i++) {
				this.orign[i + 1] = orign[i];
			}
		}

		// 构建线段树 L~R为目标位置 index为l~r范围实际坐标
		public void build(int l, int r, int index) {
			if (l == r) {// 最下一层
				sum[index] = orign[l];
				return;
			}
			int mid = (l + r) >> 1;
			build(l, mid, index << 1);// 处理左右分支
			build(mid + 1, r, index << 1 | 1);
			pushUp(index);
		}

		public void pushUp(int index) {
			sum[index] = sum[index << 1] + sum[index << 1 | 1];
		}

		// 线段树范围增加值 L~R为目标位置 l~r为实际位置 index为l~r范围实际坐标
		public void add(int L, int R, int C, int l, int r, int index) {
			if (L <= l && r <= R) {// 目标范围完全覆盖实际范围
				lazy[index] += C;
				sum[index] += (r - l + 1) * C;
				return;
			}
			int mid = (r + l) >> 1;
			// 下发任务 更新下方结构以便更细分的处理
			pushDown(index, mid - l + 1, r - mid);// ln与rn个数求法 ∵范围下标不一定从1开始 ∴用坐标求范围内值的个数
			if (L <= mid) {
				add(L, R, C, l, mid, index << 1);
			}
			if (R > mid) {
				add(L, R, C, mid + 1, r, index << 1 | 1);
			}
			// 处理完左右的值 汇总
			pushUp(index);
		}

		// 线段树范围更新
		public void update(int L, int R, int C, int l, int r, int index) {
			if (L <= l && r <= R) {// 目标范围完全覆盖实际范围
				sum[index] = (r - l + 1) * C;
				lazy[index] = 0;
				update[index] = true;
				change[index] = C;
				return;
			}
			int mid = (r + l) >> 1;
			// 下发任务 更新下方结构以便更细分的处理
			pushDown(index, mid - l + 1, r - mid);// ln与rn个数求法 ∵范围下标不一定从1开始 ∴用坐标求范围内值的个数
			if (L <= mid) {
				update(L, R, C, l, mid, index << 1);
			}
			if (R > mid) {
				update(L, R, C, mid + 1, r, index << 1 | 1);
			}
			// 处理完左右的值 汇总
			pushUp(index);
		}

		// 下发任务 更新下方结构 ln,lr左右项个数
		public void pushDown(int index, int ln, int rn) {
			if (update[index]) {// 存在更新任务
				sum[index << 1] = ln * change[index];
				sum[index << 1 | 1] = rn * change[index];
				lazy[index << 1] = 0;
				lazy[index << 1 | 1] = 0;
				update[index << 1] = true;
				update[index << 1 | 1] = true;
				change[index << 1] = change[index];
				change[index << 1 | 1] = change[index];
				update[index] = false;
			}
			if (lazy[index] != 0) {// 存在累加任务
				sum[index << 1] += lazy[index] * ln;
				sum[index << 1 | 1] += lazy[index] * rn;
				lazy[index << 1] += lazy[index];
				lazy[index << 1 | 1] += lazy[index];
				lazy[index] = 0;
			}
		}

		// 查询范围累加和
		public long query(int L, int R, int l, int r, int index) {
			if (L <= l && r <= R) {// 目标范围完全覆盖实际范围
				return sum[index];
			}
			long ans = 0;
			int mid = (r + l) >> 1;
			pushDown(index, mid - l + 1, r - mid);// 查询不完得下放任务
			if (L <= mid) {
				ans += query(L, R, l, mid, index << 1);
			}
			if (R > mid) {
				ans += query(L, R, mid + 1, r, index << 1 | 1);
			}
			return ans;
		}

	}

	// 2.接积木
	public List<Integer> maxHeight(int[][] squares) {
		if (squares == null || squares.length == 0) {
			return null;
		}
		ArrayList<Integer> res = new ArrayList<>();
		HashMap<Integer, Integer> map = index(squares);
		int N = map.size();
		SegmentTree segmentTree = new SegmentTree(N);
		int max = 0;
		for (int[] square : squares) {
			int a = map.get(square[0]);
			int b = map.get(square[0] + square[1] - 1);
			int height = segmentTree.query(a, b, 1, N, 1) + square[1];
			max = Math.max(max, height);
			segmentTree.update(a, b, height, 1, N, 1);
			res.add(max);
		}
		return res;
	}

	// 坐标离散化
	public static HashMap<Integer, Integer> index(int[][] squares) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int[] arr : squares) {// 将矩形内坐标排序
			set.add(arr[0]);
			set.add(arr[0] + arr[1] - 1);
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		int index = 1;
		for (Integer value : set) {
			map.put(value, index++);
		}
		return map;
	}

	public static class SegmentTree {
		int[] max;
		boolean[] update;
		int[] change;
		int N;

		public SegmentTree(int size) {
			N = size + 1;
			max = new int[N << 2];
			update = new boolean[N << 2];
			change = new int[N << 2];

		}

		public void pushUp(int index) {
			max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
		}

		// 线段树范围更新
		public void update(int L, int R, int C, int l, int r, int index) {
			if (L <= l && r <= R) {// 目标范围完全覆盖实际范围
				max[index] = C;
				update[index] = true;
				change[index] = C;
				return;
			}
			int mid = (r + l) >> 1;
			// 下发任务 更新下方结构以便更细分的处理
			pushDown(index, mid - l + 1, r - mid);// ln与rn个数求法 ∵范围下标不一定从1开始 ∴用坐标求范围内值的个数
			if (L <= mid) {
				update(L, R, C, l, mid, index << 1);
			}
			if (R > mid) {
				update(L, R, C, mid + 1, r, index << 1 | 1);
			}
			// 处理完左右的值 汇总
			pushUp(index);
		}

		// 下发任务 更新下方结构 ln,lr左右项个数
		public void pushDown(int index, int ln, int rn) {
			if (update[index]) {// 存在更新任务
				max[index << 1] = change[index];
				max[index << 1 | 1] = change[index];
				update[index << 1] = true;
				update[index << 1 | 1] = true;
				change[index << 1] = change[index];
				change[index << 1 | 1] = change[index];
				update[index] = false;
			}

		}

		// 查询范围累加和
		public int query(int L, int R, int l, int r, int index) {
			if (L <= l && r <= R) {// 目标范围完全覆盖实际范围
				return max[index];
			}
			int left = 0;
			int right = 0;
			int mid = (r + l) >> 1;
			pushDown(index, mid - l + 1, r - mid);// 查询不完得下放任务
			if (L <= mid) {
				left = query(L, R, l, mid, index << 1);
			}
			if (R > mid) {
				right = query(L, R, mid + 1, r, index << 1 | 1);
			}
			return Math.max(left, right);
		}

	}

	// 3.最大线段重合问题
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

	public static class Line {
		int start;
		int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

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

	// 4.最大矩形重合问题
	public static int maxCoverR(int[][] rectangles) {
		if (rectangles == null || rectangles.length < 1) {
			return 0;
		}
		int max = 0;
		Rectangle[] Rectangles = new Rectangle[rectangles.length];
		int index = 0;
		for (int[] rectangle : rectangles) {
			Rectangles[index++] = new Rectangle(rectangle[0], rectangle[1], rectangle[2], rectangle[3]);
		}
		Arrays.sort(Rectangles, new downCom());// 按底边大小排序
		TreeSet<Rectangle> leftOrder = new TreeSet<>(new leftCom());
		for (int i = 0; i < Rectangles.length;) {
			do {
				leftOrder.add(Rectangles[i++]);// 小加速 将相同底边矩形统一处理
			} while (Rectangles[i].down == Rectangles[i - 1].down && i < Rectangles.length);
			removeDown(leftOrder, Rectangles[i - 1].down);// 删除底边下的矩形
			TreeSet<Rectangle> rightOrder = new TreeSet<>(new rightCom());
			for (Rectangle rectangle : leftOrder) {
				removeLeft(rightOrder, rectangle.left);// 删除新矩形左边的矩形
				rightOrder.add(rectangle);
				max = Math.max(max, rightOrder.size());
			}
		}
		return max;
	}

	// 删除底边下的矩形
	public static void removeDown(TreeSet<Rectangle> leftOrder, int down) {
		ArrayList<Rectangle> remove = new ArrayList<>();
		for (Rectangle rectangle : leftOrder) {
			if (rectangle.up <= down) {
				remove.add(rectangle);
			}
		}
		for (Rectangle rectangle : remove) {
			leftOrder.remove(rectangle);
		}
	}

	// 删除新矩形左边的矩形
	public static void removeLeft(TreeSet<Rectangle> leftOrder, int left) {
		ArrayList<Rectangle> remove = new ArrayList<>();
		for (Rectangle rectangle : leftOrder) {
			if (rectangle.right > left) {// ∵是按照left排序的∴可以加速
				break;
			}
			remove.add(rectangle);
		}
		for (Rectangle rectangle : remove) {
			leftOrder.remove(rectangle);
		}
	}

	public static class Rectangle {
		int left;
		int right;
		int up;
		int down;

		public Rectangle(int left, int right, int up, int down) {
			this.left = left;
			this.right = right;
			this.up = up;
			this.down = down;
		}

	}

	public static class downCom implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			// TODO Auto-generated method stub
			return o1.down - o2.down;
		}

	}

	public static class leftCom implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			// TODO Auto-generated method stub
			return o1.left - o2.left;
		}

	}

	public static class rightCom implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			// TODO Auto-generated method stub
			return o1.right - o2.right;
		}

	}
}
