package class09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Code04_BestArrange {
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static int bestArrange1(Program[] pros) {
		if (pros == null || pros.length == 0) {
			return 0;
		}
		return process(pros, 0, 0);
	}

	public static int process(Program[] pros, int timeline, int numP) {
		if (pros.length == 0) {
			return numP;
		}
		int res = numP;
		for (int i = 0; i < pros.length; i++) {
			if (timeline <= pros[i].start) {
				Program[] copyOne = copyButExcept(pros, i);
//				递归可以保存每次的res
				res = Math.max(res, process(copyOne, pros[i].end, numP + 1));
			}
		}
		return res;
	}

	public static Program[] copyButExcept(Program[] pros, int index) {
		Program[] newOne = new Program[pros.length - 1];
		for (int i = 0, j = 0; i < pros.length; i++) {
			if (i != index) {
				newOne[j++] = pros[i];
			}
		}
		return newOne;
	}

	public static int bestArrange2(Program[] pros) {
		if (pros == null || pros.length == 0) {
			return 0;
		}
		int res = 0;
		int timeline = 0;
		Arrays.sort(pros, new MyCompare());
		for (int i = 0; i < pros.length; i++) {
			if (timeline <= pros[i].start) {
				res++;
				timeline = pros[i].end;
			}
		}
		return res;
	}

	public static class MyCompare implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o1.end - o2.end;
		}
	}

	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}

	// 复习
	public static int arrange(Program[] programs) {
		if (programs == null) {
			return 0;
		}
		return processA(programs, 0, new HashSet<>());
	}

	public static int processA(Program[] programs, int start, HashSet<Integer> set) {
		int res = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start && !set.contains(i)) {
				set.add(i);
				res = Math.max(res, processA(programs, programs[i].end, set) + 1);
				set.remove(i);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrange1(programs) != arrange(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
