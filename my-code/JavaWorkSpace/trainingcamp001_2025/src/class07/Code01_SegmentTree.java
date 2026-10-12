package class07;

public class Code01_SegmentTree {
	// 线段树 处理区间内统一改变 或者通过左右信息独立处理就能得到自身信息
	public static class SegmentTree2 {
		public int[] arr;
		public int[] sum;
		public int[] lazy;
		public int[] change;
		public boolean[] update;
		public int N;

		public SegmentTree2(int[] origin) {
			N = origin.length + 1;
			arr = new int[N];
			for (int i = 0; i < origin.length; i++) {
				arr[i + 1] = origin[i];
			}
			sum = new int[N << 2];
			lazy = new int[N << 2];
			change = new int[N << 2];
			update = new boolean[N << 2];
		}

		// 根据[l,r]确定rt
		public void build(int l, int r, int rt) {
			if (l == r) {
				sum[rt] = arr[l];
				return;
			}
			int mid = (l + r) >> 1;
			build(l, mid, rt << 1);// 建立左右信息
			build(mid + 1, r, rt << 1 | 1);
			pushUp(rt);
		}

		public void pushUp(int rt) {
			sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
		}

		// L,R是任务范围 l,r是数组实际范围 rt是[l,r]对应位置下标
		public void add(int L, int R, int c, int l, int r, int rt) {
			if (L <= l && r <= R) {
				sum[rt] += (r - l + 1) * c;
				lazy[rt] += c;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {// 注意任务需要判断是否需要下发 判断逻辑画图观察
				add(L, R, c, l, mid, rt << 1);
			}
			if (R > mid) {
				add(L, R, c, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		public void update(int L, int R, int c, int l, int r, int rt) {
			if (L <= l && r <= R) {
				sum[rt] = (r - l + 1) * c;
				change[rt] = c;
				update[rt] = true;
				lazy[rt] = 0;// 更新操作需要将add操作覆盖
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				update(L, R, c, l, mid, rt << 1);// 注意目标下标
			}
			if (R > mid) {
				update(L, R, c, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);// 更新信息汇总
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

		// 下发任务
		public void pushDown(int rt, int lSize, int rSize) {
			int left = rt << 1;
			int right = rt << 1 | 1;
			if (update[rt]) {
				update[left] = true;
				update[right] = true;
				change[left] = change[rt];
				change[right] = change[rt];
				sum[left] = lSize * change[rt];
				sum[right] = rSize * change[rt];
				lazy[left] = 0;// 更新操作需要将add操作覆盖
				lazy[right] = 0;
				update[rt] = false;
			}
			if (lazy[rt] != 0) {
				lazy[left] += lazy[rt];
				lazy[right] += lazy[rt];
				sum[left] += lSize * lazy[rt];
				sum[right] += rSize * lazy[rt];
				lazy[rt] = 0;
			}
		}
	}

	public static void main(String[] args) {
		int a = 1;
		System.out.println(a << 1 | 1);
	}
}
