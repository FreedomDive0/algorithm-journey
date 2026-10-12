package class03;

public class Code02_ReservoirSampling {
	public static class RandomBox {
		private int capacity;
		private int count;// 记录当前入袋的球号
		private int[] bag;

		public RandomBox(int capacity) {
			this.capacity = capacity;
			count = 0;
			bag = new int[capacity];
		}

		public void add(int n) {
			count++;
//			加入的数小于容量 直接添加
			if (count <= capacity) {
				bag[count - 1] = n;
			} else {
				// capacity/count的概率判断是否进袋子
				if (rand(count) <= capacity) {
					bag[rand(capacity) - 1] = n;
				}
			}
		}

		// 随机生成1-max之间的数
		private int rand(int max) {
			return (int) (Math.random() * max) + 1;
		}

		// 查看选择情况
		public int[] choices() {
			int[] res = new int[capacity];
			for (int i = 0; i < res.length; i++) {
				res[i] = bag[i];
			}
			return res;
		}
	}

	public static void main(String[] args) {
		System.out.println("hello");
		int all = 100;
		int choose = 10;
		int testTimes = 50000;
		int[] counts = new int[all + 1];
		for (int i = 0; i < testTimes; i++) {
			RandomBox box = new RandomBox(choose);
			for (int num = 1; num <= all; num++) {
				box.add(num);
			}
			int[] ans = box.choices();
			for (int j = 0; j < ans.length; j++) {
				counts[ans[j]]++;
			}
		}

		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + " times : " + counts[i]);
		}

	}
}
