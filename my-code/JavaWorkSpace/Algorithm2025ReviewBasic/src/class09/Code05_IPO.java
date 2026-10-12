package class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {
	public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		if (profits == null || capital == null || profits.length < 1 || capital.length < 1 || k < 1) {
			return 0;
		}
		PriorityQueue<Program> costHeap = new PriorityQueue<Program>(new CostCom());
		PriorityQueue<Program> doHeap = new PriorityQueue<Program>(new ProfitCom());
		for (int i = 0; i < profits.length; i++) {
			costHeap.add(new Program(profits[i], capital[i]));
		}
		for (int i = 0; i < k; i++) {
			while (!costHeap.isEmpty() && w >= costHeap.peek().c) {
				doHeap.add(costHeap.poll());
			}
			if (doHeap.isEmpty()) {
				break;
			}
			w += doHeap.poll().p;

		}
//		while (!costHeap.isEmpty()) {
//			if (w >= costHeap.peek().c) {
//				doHeap.add(costHeap.poll());
//			} else {
//				break;
//			}
//		}
//		while (k > 0 && !doHeap.isEmpty()) {
//			w += doHeap.poll().p;
//			k--;
//			while (!costHeap.isEmpty()) {
//				if (w >= costHeap.peek().c) {
//					doHeap.add(costHeap.poll());
//				} else {
//					break;
//				}
//			}
//		}
		return w;
	}

	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class CostCom implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}
	}

	public static class ProfitCom implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}
}
