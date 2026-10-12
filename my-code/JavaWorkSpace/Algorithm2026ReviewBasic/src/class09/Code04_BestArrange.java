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

	// 1.最佳安排全排列
	public static int bestArrangePermutation(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		return bAProcess(programs, 0, 0);
	}

	public static int bAProcess(Program[] programs, int timeLine, int count) {
		if (programs.length == 0) {
			return count;
		}
		int max = count;
		for (int i = 0; i < programs.length; i++) {
			if (timeLine <= programs[i].start) {
				max = Math.max(max, bAProcess(copyExcept(programs, i), programs[i].end, count + 1));// 因为之前的会议也需要检查
//					max = Math.max(max, bAProcess(copyExcept(programs, i), programs[i].end, ++count));//++count会影响同层分支
			}
		}
		return max;
	}

	public static Program[] copyExcept(Program[] programs, int i) {
		Program[] res = new Program[programs.length - 1];
		int idx = 0;
		for (int j = 0; j < programs.length; j++) {
			if (j != i) {
				res[idx++] = programs[j];
			}
		}
		return res;
	}

	// 2.最佳安排贪心
	public static int bestArrangeGreedy(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		Arrays.sort(programs, new ProgramCom());
		int count = 0;
		int timeLine = 0;
		for (int i = 0; i < programs.length; i++) {
			if (timeLine <= programs[i].start) {
				count++;
				timeLine = programs[i].end;
			}
		}
		return count;
	}

	public static class ProgramCom implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}
}
