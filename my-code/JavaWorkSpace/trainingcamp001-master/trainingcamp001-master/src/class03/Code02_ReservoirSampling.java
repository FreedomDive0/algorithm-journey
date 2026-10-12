package class03;

public class Code02_ReservoirSampling {

	public static class RandomBox {
		private int[] bag;
		private int N;
		private int count;

		public RandomBox(int capacity) {
			bag = new int[capacity];
			N = capacity;
			count = 0;
		}

		private int rand(int max) {
			return (int) (Math.random() * max) + 1;
		}

		public void add(int num) {
			count++;
			if (count <= N) {
				bag[count - 1] = num;
			} else {
				if (rand(count) <= N) {
					bag[rand(N) - 1] = num;
				}
			}
		}

		public int[] choices() {
			int[] ans = new int[N];
			for (int i = 0; i < N; i++) {
				ans[i] = bag[i];
			}
			return ans;
		}

	}

	// 测试
	// 2.蓄水池算法
	public static class RandomBox1 {
		private int[] bag;
		private int count;
		private int capacity;

		public RandomBox1(int capacity) {
			this.bag = new int[capacity];
			this.count = 0;
			this.capacity = capacity;
		}

		public void add(int num) {
			count++;
			if (count < capacity) {
				bag[count - 1] = num;
			} else {
				if (rand(count) <= capacity) {
					bag[rand(capacity) - 1] = num;
				}
			}
		}

		public int[] choices() {
			int[] ans = new int[capacity];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = bag[i];
			}
			return ans;
		}

		private static int rand(int n) {
			return (int) (Math.random() * n) + 1;
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
