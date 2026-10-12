package class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {
	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static int findMaximizedCapitalPermutation(int K, int W, int[] Profits, int[] Capital) {
		if (K == 0 || Profits == null || Profits.length == 0 || Capital == null || Capital.length == 0) {
			return W;// 没有项目可以返回本金
		}
		Program[] programs = new Program[Profits.length];
		for (int i = 0; i < programs.length; i++) {
			programs[i] = new Program(Profits[i], Capital[i]);
		}
		PriorityQueue<Program> capitalHeap = new PriorityQueue<Program>(new CapitalCom());
		PriorityQueue<Program> profitHeap = new PriorityQueue<Program>(new ProfitCom());
		for (int i = 0; i < programs.length; i++) {
			capitalHeap.add(programs[i]);
		}
		for (int i = 0; i < K; i++) {
			while (!capitalHeap.isEmpty() && capitalHeap.peek().c <= W) {
				profitHeap.add(capitalHeap.poll());
			}
			if (profitHeap.isEmpty()) {
				break;
			}
			W += profitHeap.poll().p;
		}
		return W;
	}

	public static class CapitalCom implements Comparator<Program> {

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
