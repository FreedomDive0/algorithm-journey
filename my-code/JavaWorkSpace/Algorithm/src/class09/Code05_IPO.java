package class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {
	public static int findMaximizedCapital(int K,int M,int[] cost,int[] profits) {
		PriorityQueue<Program> costP = new PriorityQueue<Program>(new MyComCost());
		PriorityQueue<Program> profP = new PriorityQueue<Program>(new MyComProf());
		for(int i=0;i<cost.length;i++) {
			costP.add(new Program(cost[i], profits[i]));
		}
		for(int i=0;i<K;i++) {
			while(!costP.isEmpty()&&M>=costP.peek().c) {
				profP.add(costP.poll());
			}
			if(profP.isEmpty()) {
				return M;
			}
			M += profP.poll().p; 
		}
		return M;
	}
	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
	public static class MyComCost implements Comparator<Program>{

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o1.c-o2.c;
		}
		
	}
	public static class MyComProf implements Comparator<Program>{

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o2.p-o1.p;
		}
		
	}
}
