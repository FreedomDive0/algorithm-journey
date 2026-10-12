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

	public static int bestArrange1(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		return process(programs, 0, 0);
	}

	// 还剩什么会议都放在programs里
	// done 之前已经安排了多少会议，数量
	// timeLine目前来到的时间点是什么

	// 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
	// 返回能安排的最多会议数量
	public static int process(Program[] programs, int done, int timeLine) {
		if (programs.length == 0) {
			return done;
		}
		// 还有会议可以选择
		int max = done;
		// 当前安排的会议是什么会，每一个都枚举
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) {
				Program[] next = copyButExcept(programs, i);
				max = Math.max(max, process(next, done + 1, programs[i].end));
			}
		}
		return max;
	}

	public static Program[] copyButExcept(Program[] programs, int i) {
		Program[] ans = new Program[programs.length - 1];
		int index = 0;
		for (int k = 0; k < programs.length; k++) {
			if (k != i) {
				ans[index++] = programs[k];
			}
		}
		return ans;
	}

	public static int bestArrange2(Program[] programs) {
		Arrays.sort(programs, new ProgramComparator());
		int timeLine = 0;
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (timeLine <= programs[i].start) {
				result++;
				timeLine = programs[i].end;
			}
		}
		return result;
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
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

	// 复习2026
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
//				max = Math.max(max, bAProcess(copyExcept(programs, i), programs[i].end, ++count));//++count会影响同层分支
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

	public static void main(String[] args) {
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrangePermutation(programs) != bestArrange1(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
