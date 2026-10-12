package class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Code02_FallingSquares {
	public class SegmentTree {
		private int MaxN;
		private int[] max;
		private int[] change;
		private boolean[] update;

		public SegmentTree(int N) {
			MaxN = N + 1;
			max = new int[MaxN << 2];
			change = new int[MaxN << 2];
			update = new boolean[MaxN << 2];
		}

		public void update(int L, int R, int height, int l, int r, int index) {
			if (L <= l && r <= R) {
				update[index] = true;
				change[index] = height;
				max[index] = height;
				return;// 执行完就返回
			}
			int middle = (l + r) >> 1;
			pushDown(index, middle - l + 1, r - middle);
			if (L <= middle) {
				update(L, R, height, l, middle, index << 1);
			}
			if (R > middle) {
				update(L, R, height, middle + 1, r, (index << 1) | 1);
			}
			pushUp(index);
		}

		public int query(int L, int R, int l, int r, int index) {
			if (L <= l && r <= R) {
				return max[index];
			}
			int left = 0;
			int right = 0;
			int middle = (l + r) >> 1;
			pushDown(index, middle - l + 1, r - middle);
			if (L <= middle) {
				left = query(L, R, l, middle, index << 1);
			}
			if (R > middle) {
				right = query(L, R, middle + 1, r, (index << 1) | 1);
			}
			return Math.max(left, right);
		}

		private void pushUp(int index) {
			max[index] = Math.max(max[index << 1], max[(index << 1) | 1]);
		}

		private void pushDown(int index, int ln, int rn) {
			if (update[index]) {
				update[index << 1] = true;
				update[(index << 1) | 1] = true;
				change[index << 1] = change[index];
				change[(index << 1) | 1] = change[index];
				max[index << 1] = change[index];
				max[(index << 1) | 1] = change[index];
				update[index] = false;
			}
		}
	}

//	下标离散化(将方块底边长度坐标化) 节省空间
	public static HashMap<Integer, Integer> index(int[][] position) {
		TreeSet<Integer> set = new TreeSet<Integer>();// 有序集合
		for (int[] arr : position) {
			set.add(arr[0]);
			set.add(arr[0] + arr[1] - 1);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for (Integer integer : set) {
			map.put(integer, ++count);// 下标由一开始
		}
		return map;
	}

	public List<Integer> fallingSquares(int[][] positions) {
		HashMap<Integer, Integer> map = index(positions);
		int N = map.size();
		SegmentTree segmentTree = new SegmentTree(N);
		List<Integer> res = new ArrayList<Integer>();
		int max = 0;
		for (int[] arr : positions) {
			int L = map.get(arr[0]);
			int R = map.get(arr[0] + arr[1] - 1);
			int height = segmentTree.query(L, R, 1, N, 1) + arr[1];
			max = Math.max(max, height);
			segmentTree.update(L, R, height, 1, N, 1);
			res.add(max);
		}
		return res;
	}
}
