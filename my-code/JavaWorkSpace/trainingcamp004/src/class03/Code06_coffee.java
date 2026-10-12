package class03;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code06_coffee {
	public static class CoffeeMachine {
		int start;
		int workTime;

		public CoffeeMachine(int start, int workTime) {
			this.start = start;
			this.workTime = workTime;
		}
	}

	public static class CoffeeCompartor implements Comparator<CoffeeMachine> {

		@Override
		public int compare(CoffeeMachine o1, CoffeeMachine o2) {
			// TODO Auto-generated method stub
			return o1.start + o1.workTime - o2.start - o2.workTime;
		}

	}

	public static int[] coffeeTime(int[] arr, int N) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		PriorityQueue<CoffeeMachine> machine = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			machine.add(new CoffeeMachine(0, arr[i]));
		}
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			CoffeeMachine cur = machine.poll();
			ans[i] = cur.start + cur.workTime;
			machine.add(new CoffeeMachine(ans[i], cur.workTime));
		}
		return ans;
	}
}
