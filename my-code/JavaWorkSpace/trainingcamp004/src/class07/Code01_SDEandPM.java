package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Code01_SDEandPM {
	// 项目经理和程序员
	public static class Program {
		int pm;// 属于那个经理
		int startTime;// 润色完的时间
		int rank;// 优先级
		int costTime;// 花费程序员的时间
		int index;

		public Program(int pm, int startTime, int rank, int costTime, int index) {
			this.pm = pm;
			this.startTime = startTime;
			this.rank = rank;
			this.costTime = costTime;
			this.index = index;
		}// 在原数组的位置

	}

	// 项目经理对于项目的排序
	public static class PmLove implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			if (o1.rank != o2.rank) {
				return o1.rank - o2.rank;
			} else if (o1.costTime != o2.costTime) {
				return o1.costTime - o2.costTime;
			} else {
				return o1.startTime - o2.startTime;
			}
		}

	}

	// 项目润色完时间排序
	public static class ProgramStartCom implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.startTime - o2.startTime;
		}

	}

	// 程序员的喜好
	public static int sdeLoveRule(Program p1, Program p2) {
		if (p1.costTime != p2.costTime) {
			return p1.costTime - p2.costTime;
		}
		return p1.pm - p2.pm;
	}

	public static class BigQueue {
		Program[] programs;
		int heapSize;
		List<PriorityQueue<Program>> pmQueue;
		int[] indexs;

		public BigQueue(int pm) {
			programs = new Program[pm];
			heapSize = 0;
			pmQueue = new ArrayList<>();
			indexs = new int[pm + 1];
			for (int i = 0; i <= pm; i++) {
				indexs[i] = -1;
			}
			for (int i = 0; i <= pm; i++) {// pm从0开始计算
				pmQueue.add(new PriorityQueue<Program>(new PmLove()));
			}
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public void add(Program program) {
			PriorityQueue<Program> queue = pmQueue.get(program.pm);
			queue.add(program);
			Program head = queue.peek();
			int index = indexs[program.pm];
			if (index == -1) {
				programs[heapSize] = program;
				indexs[program.pm] = heapSize;
				heapInsert(heapSize++);
			} else {
				programs[index] = head;
				heapInsert(index);
				heapify(index);
			}
		}

		public Program pop() {
			PriorityQueue<Program> queue = pmQueue.get(programs[0].pm);
			Program top = queue.poll();
			if (queue.isEmpty()) {
				swap(0, heapSize - 1);
				programs[--heapSize] = null;
				indexs[top.pm] = -1;
			} else {
				programs[0] = queue.peek();
			}
			heapify(0);
			return top;
		}

		public void heapInsert(int index) {
			while (index != 0) {
				int parent = (index - 1) / 2;
				if (sdeLoveRule(programs[index], programs[parent]) < 0) {
					swap(index, parent);
					index = parent;
				} else {
					break;
				}
			}
		}

		public void heapify(int index) {
			int left = index * 2 + 1;
			int right = index * 2 + 2;
			int best = index;
			while (left < heapSize) {
				if (sdeLoveRule(programs[index], programs[left]) > 0) {
					best = index;
				}
				if (right < heapSize && sdeLoveRule(programs[index], programs[right]) > 0) {
					best = index;
				}
				if (best == index) {
					break;
				}
				swap(index, best);
				index = best;
				left = index * 2 + 1;
				right = index * 2 + 2;
			}
		}

		private void swap(int index1, int index2) {
			Program p1 = programs[index1];
			Program p2 = programs[index2];
			programs[index1] = p2;
			programs[index2] = p1;
			indexs[p1.pm] = index2;
			indexs[p2.pm] = index1;
		}
	}

	public static int[] sdeAndPM(int[][] programs, int pm, int sde) {
		PriorityQueue<Program> startQueue = new PriorityQueue<Program>(new ProgramStartCom());// 项目开始栈
		PriorityQueue<Integer> wakeQueue = new PriorityQueue<Integer>();// 程序员栈
		for (int i = 0; i < sde; i++) {
			wakeQueue.add(1);
		}
		for (int i = 0; i < programs.length; i++) {
			startQueue.add(new Program(programs[i][0], programs[i][1], programs[i][2], programs[i][3], i));
		}
		BigQueue stack = new BigQueue(pm);
		int num = 0;
		int[] finish = new int[programs.length];
		while (num < finish.length) {
			int startTime = wakeQueue.poll();
			while (!startQueue.isEmpty()) {
				if (startQueue.peek().startTime > startTime) {
					break;
				}
				stack.add(startQueue.poll());
			}
			if (stack.isEmpty()) {
				wakeQueue.add(startQueue.peek().startTime);
			} else {
				Program cur = stack.pop();
				finish[cur.index] = startTime + cur.costTime;
				wakeQueue.add(finish[cur.index]);
				num++;
			}
		}
		return finish;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {
		int pms = 2;
		int sde = 2;
		int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
		int[] ans = sdeAndPM(programs, pms, sde);
		printArray(ans);
	}
}
