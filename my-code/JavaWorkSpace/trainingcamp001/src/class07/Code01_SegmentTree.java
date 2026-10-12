package class07;

public class Code01_SegmentTree {
	// 父亲节点 可以通过左右子树信息得出自身信息 不需要具体参考整体
	public static class SegmentTree {
		private int MaxN;// 原数组长度
		private int[] arr;// 原数组
		private int[] sum;// 范围和
		private int[] lazy;// add值数组
		private int[] change;// 更新值数组
		private boolean[] update;// 是否更新数组

		public SegmentTree(int[] arr) {
			MaxN = arr.length + 1;// 不是用0下标 为了方便快速的位运算
			this.arr = new int[MaxN];
			for (int i = 0; i < arr.length; i++) {
				this.arr[i + 1] = arr[i];
			}
			sum = new int[MaxN << 2];// 4*N可以覆盖所有位置(数组长度为2*N 为最佳 数组长度为2*N+1 为最差)
			lazy = new int[MaxN << 2];
			change = new int[MaxN << 2];
			update = new boolean[MaxN << 2];
		}

		// 构建线段树
		public void build(int l, int r, int index) {
			if (l == r) {
				sum[index] = arr[l];
				return;
			}
			int middle = (l + r) >> 1;
			build(l, middle, index << 1);
			build(middle + 1, r, (index << 1) | 1);
			pushUp(index);
		}

		// 汇总index的sum值
		private void pushUp(int index) {
			sum[index] = sum[index << 1] + sum[(index << 1) | 1];
		}

		// L...R为目标范围 num为增加目标值 l...r为当前所处范围 index为线段树中的下标
		public void add(int L, int R, int num, int l, int r, int index) {
			// 如果目标范围完全涵盖现在所处范围 直接更新
			if (L <= l && r <= R) {
				lazy[index] += num;// lazy[index] index位置sum是更新好的
				sum[index] += (r - l + 1) * num;
				return;
			}
			int middle = (l + r) >> 1;
			int size = (r + l) >> 1;
			pushDown(index, middle - l + 1, r - middle);
			if (L <= middle) {
				add(L, R, num, l, middle, index << 1);
			}
			if (R > middle) {
				add(L, R, num, middle + 1, r, (index << 1) | 1);
			}
			pushUp(index);
		}

		// L...R为目标范围 num为增加目标值 l...r为当前所处范围 index为线段树中的下标
		public void update(int L, int R, int num, int l, int r, int index) {
			// 如果目标范围完全涵盖现在所处范围 直接更新
			if (L <= l && r <= R) {
				update[index] = true;
				lazy[index] = 0;// 添加值失效
				sum[index] = (r - l + 1) * num;
				change[index] = num;
				return;
			}
			int middle = (l + r) >> 1;
			pushDown(index, middle - l + 1, r - middle);
			if (L <= middle) {
				update(L, R, num, l, middle, index << 1);
			}
			if (R > middle) {
				update(L, R, num, middle + 1, r, (index << 1) | 1);
			}
			pushUp(index);
		}

		// 向下分发任务 先更新后add index为当前下标 ln左区域长度 rn为右区域长度
		private void pushDown(int index, int ln, int rn) {
			if (update[index]) {
				sum[index << 1] = change[index] * ln;// 跟新值
				sum[(index << 1) | 1] = change[index] * rn;
				update[index << 1] = true;// 分发任务
				update[(index << 1) | 1] = true;
				change[index << 1] = change[index];
				change[(index << 1) | 1] = change[index];
				lazy[index << 1] = 0;
				lazy[(index << 1) | 1] = 0;
				update[index] = false;
			}
			if (lazy[index] != 0) {
				sum[index << 1] += lazy[index] * ln;
				sum[(index << 1) | 1] += lazy[index] * rn;
				lazy[index << 1] += lazy[index];// 分发任务lazy应该累加
				lazy[(index << 1) | 1] += lazy[index];
				lazy[index] = 0;
			}
		}

		// 求范围和
		public int query(int L, int R, int l, int r, int index) {
			// 如果目标范围完全涵盖现在所处范围 直接返回
			int ans = 0;
			if (L <= l && r <= R) {
				return sum[index];
			}
			int middle = (l + r) >> 1;
			pushDown(index, middle - l + 1, r - middle);
			if (L <= middle) {
				ans += query(L, R, l, middle, index << 1);
			}
			if (R > middle) {
				ans += query(L, R, middle + 1, r, (index << 1) | 1);
			}
			return ans;
		}
	}

	// for test
	public static class Right {
		public int[] arr;

		public Right(int[] origin) {
			arr = new int[origin.length + 1];
			for (int i = 0; i < origin.length; i++) {
				arr[i + 1] = origin[i];
			}
		}

		public void update(int L, int R, int C) {
			for (int i = L; i <= R; i++) {
				arr[i] = C;
			}
		}

		public void add(int L, int R, int C) {
			for (int i = L; i <= R; i++) {
				arr[i] += C;
			}
		}

		public long query(int L, int R) {
			long ans = 0;
			for (int i = L; i <= R; i++) {
				ans += arr[i];
			}
			return ans;
		}

	}

	public static int[] genarateRandomArray(int len, int max) {
		int size = (int) (Math.random() * len) + 1;
		int[] origin = new int[size];
		for (int i = 0; i < size; i++) {
			origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return origin;
	}

	public static boolean test() {
		int len = 100;
		int max = 1000;
		int testTimes = 5000;
		int addOrUpdateTimes = 1000;
		int queryTimes = 500;
		for (int i = 0; i < testTimes; i++) {
			int[] origin = genarateRandomArray(len, max);
			SegmentTree seg = new SegmentTree(origin);
			int S = 1;
			int N = origin.length;
			int root = 1;
			seg.build(S, N, root);
			Right rig = new Right(origin);
			for (int j = 0; j < addOrUpdateTimes; j++) {
				int num1 = (int) (Math.random() * N) + 1;
				int num2 = (int) (Math.random() * N) + 1;
				int L = Math.min(num1, num2);
				int R = Math.max(num1, num2);
				int C = (int) (Math.random() * max) - (int) (Math.random() * max);
				if (Math.random() < 0.5) {
					seg.add(L, R, C, S, N, root);
					rig.add(L, R, C);
				} else {
					seg.update(L, R, C, S, N, root);
					rig.update(L, R, C);
				}
			}
			for (int k = 0; k < queryTimes; k++) {
				int num1 = (int) (Math.random() * N) + 1;
				int num2 = (int) (Math.random() * N) + 1;
				int L = Math.min(num1, num2);
				int R = Math.max(num1, num2);
				long ans1 = seg.query(L, R, S, N, root);
				long ans2 = rig.query(L, R);
				if (ans1 != ans2) {
					return false;
				}
			}
		}
		return true;
	}

	// 复习
	public static class SegmentTree1 {
		int[] orign;
		int[] sum;
		int[] lazy;
		boolean[] update;
		int[] change;
		int N = 0;

		public SegmentTree1(int[] orign) {
			this.orign = new int[orign.length + 1];
			N = orign.length;
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
				add(L, R, C, l, mid, index >> 1);
			}
			if (R > mid) {
				add(L, R, C, mid + 1, r, index >> 1 | 1);
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
				update(L, R, C, l, mid, index >> 1);
			}
			if (R > mid) {
				update(L, R, C, mid + 1, r, index >> 1 | 1);
			}
			// 处理完左右的值 汇总
			pushUp(index);
		}

		// 下发任务 更新下方结构 ln,lr左右项个数
		public void pushDown(int index, int ln, int rn) {
			if (update[index]) {// 存在更新任务
				sum[index >> 1] = ln * change[index];
				sum[index >> 1 | 1] = rn * change[index];
				lazy[index >> 1] = 0;
				lazy[index >> 1 | 1] = 0;
				update[index >> 1] = true;
				update[index >> 1 | 1] = true;
				change[index >> 1] = change[index];
				change[index >> 1 | 1] = change[index];
				update[index] = false;
			}
			if (lazy[index] != 0) {// 存在累加任务
				sum[index >> 1] += lazy[index] * ln;
				sum[index >> 1 | 1] += lazy[index] * rn;
				lazy[index >> 1] += lazy[index];
				lazy[index >> 1 | 1] += lazy[index];
				lazy[index] = 0;
			}
		}

		// 查询范围累加和
		public int query(int L, int R, int l, int r, int index) {
			if (L <= l && r <= R) {// 目标范围完全覆盖实际范围
				return sum[index];
			}
			int ans = 0;
			int mid = (r + l) >> 1;
			if (L <= mid) {
				ans += query(L, R, l, mid, index >> 1);
			}
			if (R > mid) {
				ans += query(L, R, mid + 1, r, index >> 1 | 1);
			}
			return ans;
		}

	}

	public static void main(String[] args) {
		int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
		SegmentTree seg = new SegmentTree(origin);
		int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
		int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
		int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
		int L = 2; // 操作区间的开始位置 -> 可变
		int R = 5; // 操作区间的结束位置 -> 可变
		int C = 4; // 要加的数字或者要更新的数字 -> 可变
		// 区间生成，必须在[S,N]整个范围上build
		seg.build(S, N, root);
		// 区间修改，可以改变L、R和C的值，其他值不可改变
		seg.add(L, R, C, S, N, root);
		// 区间更新，可以改变L、R和C的值，其他值不可改变
		seg.update(L, R, C, S, N, root);
		// 区间查询，可以改变L和R的值，其他值不可改变
		long sum = seg.query(L, R, S, N, root);
		System.out.println(sum);

		System.out.println("对数器测试开始...");
		System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

	}
}
