package class03;

//蓄水池算法
public class Code02_ReservoirSampling {
	public static class RandomBox {
		private int[] bag;
		private int limit;
		private int count;

		public RandomBox(int limit) {
			this.limit = limit;
			bag = new int[this.limit];
			count = 0;
		}

		public void add(int num) {
			count++;// 数量得先算 会影响后序概率判断
			if (count <= limit) {
				bag[count - 1] = num;
			} else {
				if (random(count) <= limit) {// limit/count概率入
					bag[random(limit) - 1] = num;// 其中的数 1/limit出
				}
			}
		}

		public int[] choices() {
			int[] ans = new int[limit];
			for (int i = 0; i < ans.length; i++) {
				ans[i] = bag[i];
			}
			return ans;
		}

		private int random(int n) {
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
