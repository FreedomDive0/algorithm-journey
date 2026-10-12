package class09;

import java.util.Arrays;
import java.util.Comparator;

public class Code04_BestArrange {
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramCom implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o1.end - o2.end;
		}
	}

	// 贪心法
	public static int bestArrange(Program[] programs) {
		if (programs == null || programs.length < 1) {
			return 0;
		}
		Arrays.sort(programs, new ProgramCom());
		int done = 0;
		int timeLine = 0;
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) {
				done++;
				timeLine = programs[i].end;
			}
		}
		return done;
	}

	// 全排列
	public static int bestArrangeB(Program[] programs) {
		if (programs == null || programs.length < 1) {
			return 0;
		}
		return processBA(programs, 0, 0);
	}

	public static int processBA(Program[] programs, int done, int timeLine) {
		if (programs.length == 0) {
			return done;
		}
		int max = done;// 注意把结果要保留
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) {
				Program[] copy = copyExceptIndex(programs, i);
				max = Math.max(max, processBA(copy, done + 1, programs[i].end));
			}
		}
		return max;
	}

	public static Program[] copyExceptIndex(Program[] programs, int idx) {
		Program[] copy = new Program[programs.length - 1];
		int k = 0;
		for (int i = 0; i < programs.length; i++) {
			if (i != idx) {
				copy[k++] = programs[i];
			}
		}
		return copy;
	}
}
