package class08;

public class Code01_RandToRand {
	// 这个结构是唯一的随机机制
	// 你只能初始化并使用，不可修改
	public static class RandomBox {
		private int min;
		private int max;

		// 初始化时请一定不要让mi==ma
		public RandomBox(int mi, int ma) {
			min = mi;
			max = ma;
		}

//			随机生成min~max的数
		public int rand() {
			return (int) (Math.random() * (max - min + 1)) + min;
		}

		public int min() {
			return min;
		}

		public int max() {
			return max;
		}
	}

//	给定原随机范围 生成随机生成0-1
	public static int random01(RandomBox randomBox) {
		int min = randomBox.min;
		int max = randomBox.max;
		int size = max - min + 1;
		boolean odd = (size & 1) == 1;
		int mid = size / 2;
		int ans = 0;
		do {
			ans = randomBox.rand() - min;// 摇出的数为0~(size-1)
		} while (odd && ans == mid);// 摇到了奇数 并且是中间位舍弃
		return ans = ans < mid ? 0 : 1;
	}

//	给定原随机范围 生成给定新随即范围
	public static int random(RandomBox randomBox, int from, int to) {
		if (from == to) {
			return from;
		}
		int range = to - from;// 范围
		int num = 1;// 需要几位二进制数表达
		int ans = 0;
		while ((1 << num) - 1 < range) {// 该位数2进制数不能表达 0~range范围 扩大范围
			num++;
		}
		do {
			ans = 0;
			for (int i = 0; i < num; i++) {
				ans |= random01(randomBox) << i;// 生成的0or1右移并且与上个数相加
			}
		} while (ans > range);
		return from + ans;
	}

	public static void main(String[] args) {
		RandomBox randomBox = new RandomBox(3, 9);
		int from = 17;
		int to = 29;
		int[] ans = new int[to + 1];
		int testTime1 = 1000000;
		for (int i = 0; i < testTime1; i++) {
			ans[random(randomBox, from, to)]++;
		}
		for (int i = from; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
		System.out.println("==========");

	}
}
