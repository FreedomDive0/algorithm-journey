package class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

//1.下落方块问题
//2.房子上色问题
public class Code02_FallingSquares {
	public class SegmentTree {
		public int[] max;
		public int[] change;
		public boolean[] update;

		public SegmentTree(int size) {
			int N = size + 1;
			max = new int[N << 2];
			change = new int[N << 2];
			update = new boolean[N << 2];
		}

		public void update(int L, int R, int c, int l, int r, int rt) {
			if (L <= l && r <= R) {
				update[rt] = true;
				change[rt] = c;
				max[rt] = c;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt);
			if (L <= mid) {
				update(L, R, c, l, mid, rt << 1);
			}
			if (R > mid) {
				update(L, R, c, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		public int query(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				return max[rt];
			}
			int mid = (l + r) >> 1;
			pushDown(rt);
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

		public void pushUp(int rt) {
			max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
		}

		public void pushDown(int rt) {
			if (update[rt]) {
				int left = rt << 1;
				int right = rt << 1 | 1;
				update[left] = true;
				update[right] = true;
				change[left] = change[rt];
				change[right] = change[rt];
				max[left] = change[rt];
				max[right] = change[rt];
				update[rt] = false;
			}
		}
	}

	public List<Integer> fallingSquares(int[][] positions) {
		TreeSet<Integer> posSet = new TreeSet<Integer>();
		// 离散化下标
		for (int i = 0; i < positions.length; i++) {
			posSet.add(positions[i][0]);
			posSet.add(positions[i][0] + positions[i][1] - 1);
		}
		HashMap<Integer, Integer> idxMap = new HashMap<Integer, Integer>();
		int idx = 1;
		for (Integer pos : posSet) {
			idxMap.put(pos, idx++);
		}
		SegmentTree segmentTree = new SegmentTree(idx - 1);
		List<Integer> ans = new ArrayList<Integer>();
		int max = 0;
		for (int i = 0; i < positions.length; i++) {
			int l = idxMap.get(positions[i][0]);
			int r = idxMap.get(positions[i][0] + positions[i][1] - 1);
			int cur = segmentTree.query(l, r, 1, idx - 1, 1);
			max = Math.max(max, cur + positions[i][1]);
			segmentTree.update(l, r, cur + positions[i][1], 1, idx - 1, 1);
			ans.add(max);
		}
		return ans;
	}
}
