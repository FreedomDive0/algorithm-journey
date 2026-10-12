package class09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Review1 {
	// 1.String[] return最小字典序拼接
	public static String minJoint(String[] strs) {
		if (strs == null || strs.length < 1) {
			return "";
		}
		Arrays.sort(strs, new MyCom());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static class MyCom implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return (o1 + o2).compareTo(o2 + o1);
		}

	}

	// 2.会议室安排
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class MyComP implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	public static int meetingArrange(Program[] programs) {
		if (programs == null || programs.length < 1) {
			return 0;
		}
		Arrays.sort(programs, new MyComP());
		int timeLine = 0;
		int meetings = 0;
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) {
				meetings++;
				timeLine = programs[i].end;
			}
		}
		return meetings;
	}

	// 3.放路灯问题
	public static int minLights(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		char[] cs = s.toCharArray();
		int light = 0;
		int index = 0;
		while (index < cs.length) {
			if (cs[index] != 'X') {
				light++;
				if (index + 1 == cs.length) {// 之后会使用一次index+1 所以要提前判断
					break;
				}
				if (cs[index + 1] == 'X') {
					index = index + 2;
				} else {
					index = index + 3;
				}
			} else {
				index++;
			}
		}
		return light;
	}

	// 4.分金条
	public static int minCost(int[] golds) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for (int i = 0; i < golds.length; i++) {
			heap.add(golds[i]);
		}
		int res = 0;
		while (heap.size() > 1) {
			int cur = heap.poll() + heap.poll();
			res += cur;
			heap.add(cur);
		}
		return res;
	}

	// 5.最大工程利润
	public static class ProgramInfo {
		int cost;
		int profit;

		public ProgramInfo(int cost, int profit) {
			this.cost = cost;
			this.profit = profit;
		}

	}

	public static class MyComPC implements Comparator<ProgramInfo> {

		@Override
		public int compare(ProgramInfo o1, ProgramInfo o2) {
			return o1.cost - o2.cost;
		}

	}

	public static class MyComPP implements Comparator<ProgramInfo> {

		@Override
		public int compare(ProgramInfo o1, ProgramInfo o2) {
			return o2.profit - o1.profit;
		}

	}

	public static int IPO(int[] cost, int[] profit, int w, int k) {
		PriorityQueue<ProgramInfo> cHeap = new PriorityQueue<ProgramInfo>(new MyComPC());
		PriorityQueue<ProgramInfo> pHeap = new PriorityQueue<ProgramInfo>(new MyComPP());
		for (int i = 0; i < cost.length; i++) {
			ProgramInfo p = new ProgramInfo(cost[i], profit[i]);
			cHeap.add(p);
		}
		for (int i = 0; i < k; i++) {
			while (!cHeap.isEmpty() && cHeap.peek().cost <= w) {
				pHeap.add(cHeap.poll());
			}
			if (pHeap.isEmpty()) {
				return w;
			}
			w += pHeap.poll().profit;// 进入pHeap是已经可以执行的工程
		}
		return w;
	}
}
