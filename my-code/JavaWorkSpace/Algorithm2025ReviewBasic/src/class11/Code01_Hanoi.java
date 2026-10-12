package class11;

import java.util.Stack;

public class Code01_Hanoi {
	// 最直白的递归
	public static void hanoi1(int n) {
		leftToRight(n);
	}

	public static void leftToRight(int i) {
		if (i == 1) {
			System.out.println("Move 1 from left to right");
			return;
		}
		leftToMid(i - 1);
		System.out.println("Move " + i + " from left to right");
		midToRight(i - 1);
	}

	public static void leftToMid(int i) {
		if (i == 1) {
			System.out.println("Move 1 from left to mid");
			return;
		}
		leftToRight(i - 1);
		System.out.println("Move " + i + " from left to mid");
		rightToMid(i - 1);
	}

	public static void midToRight(int i) {
		if (i == 1) {
			System.out.println("Move 1 from mid to right");
			return;
		}
		midToLeft(i - 1);
		System.out.println("Move " + i + " from mid to right");
		leftToRight(i - 1);
	}

	public static void rightToMid(int i) {
		if (i == 1) {
			System.out.println("Move 1 from right to mid");
			return;
		}
		rightToLeft(i - 1);
		System.out.println("Move " + i + " from right to mid");
		leftToMid(i - 1);
	}

	public static void midToLeft(int i) {
		if (i == 1) {
			System.out.println("Move 1 from mid to left");
			return;
		}
		midToRight(i - 1);
		System.out.println("Move " + i + " from mid to left");
		rightToLeft(i - 1);
	}

	public static void rightToLeft(int i) {
		if (i == 1) {
			System.out.println("Move 1 from right to left");
			return;
		}
		rightToMid(i - 1);
		System.out.println("Move " + i + " from right to left");
		midToLeft(i - 1);
	}

	// 第二种递归
	public static void hanoi2(int n) {
		processh(n, "left", "right", "mid");
	}

	public static void processh(int idx, String from, String to, String other) {
		if (idx == 1) {
			System.out.println("Move 1 from " + from + " to " + to);
			return;
		}
		processh(idx - 1, from, other, to);
		System.out.println("Move " + idx + " from " + from + " to " + to);
		processh(idx - 1, other, to, from);
	}

	public static class MoveRecord {
		public int idx;
		public String from;
		public String to;
		public String other;
		public boolean canDo;

		public MoveRecord(int idx, String from, String to, String other) {
			this.idx = idx;
			this.from = from;
			this.to = to;
			this.other = other;
			canDo = false;
		}

	}

	// 非递归版本
	public static void hanoi3(int n) {
		if (n < 0) {
			return;
		}
		MoveRecord f = new MoveRecord(n, "left", "right", "mid");
		Stack<MoveRecord> stack = new Stack<MoveRecord>();
		stack.push(f);
		while (!stack.isEmpty()) {
			MoveRecord cur = stack.pop();
			if (cur.idx == 1) {
				System.out.println("Move " + cur.idx + " from " + cur.from + " to " + cur.to);
				if (!stack.isEmpty()) {
					stack.peek().canDo = true;
				}
			} else {
				if (!cur.canDo) {
					stack.push(cur);
					stack.push(new MoveRecord(cur.idx - 1, cur.from, cur.other, cur.to));
				} else {
					System.out.println("Move " + cur.idx + " from " + cur.from + " to " + cur.to);
					stack.push(new MoveRecord(cur.idx - 1, cur.other, cur.to, cur.from));
				}
			}
		}
	}

	static class Frame {
		int n;
		char from;
		char to;
		char aux;
		boolean isSubProblem2; // 用于标记是否为中间子任务

		Frame(int n, char from, char to, char aux, boolean isSubProblem2) {
			this.n = n;
			this.from = from;
			this.to = to;
			this.aux = aux;
			this.isSubProblem2 = isSubProblem2;
		}
	}

	static class Move {
		int idx;
		String from;
		String to;
		String other;
		boolean isMove; // 用于标记是否为中间子任务

		Move(int idx, String from, String to, String other, boolean isMove) {
			this.idx = idx;
			this.from = from;
			this.to = to;
			this.other = other;
			this.isMove = isMove;
		}
	}

	public static void solveHanoi(int n, char from, char to, char aux) {
		Stack<Frame> stack = new Stack<>();
		stack.push(new Frame(n, from, to, aux, false));
		while (!stack.isEmpty()) {
			Frame current = stack.pop();
			if (current.n == 1) {
				System.out.printf("Move disk 1 from %c to %c\n", current.from, current.to);
				continue;
			}

			if (current.isSubProblem2) {
				// 中间步骤：移动最大的盘子
				System.out.printf("Move disk %d from %c to %c\n", current.n, current.from, current.to);
				// 第三步：移动 n-1 个盘子从辅助柱到目标柱
				stack.push(new Frame(current.n - 1, current.aux, current.to, current.from, false));
			} else {
				// 按照递归顺序：第三步推入，第二步再来一次，第一步最后推入
				// 第二步：执行中间步骤
				stack.push(new Frame(current.n, current.from, current.to, current.aux, true));
				// 第一小步：将 n-1 个盘子从 from 移动到 aux
				stack.push(new Frame(current.n - 1, current.from, current.aux, current.to, false));
			}
		}
	}

	// 好理解版本
	public static void hanoi4(int N) {
		if (N < 1) {
			return;
		}
		Stack<Move> stack = new Stack<>();
		stack.add(new Move(N, "left", "right", "mid", false));
		while (!stack.isEmpty()) {
			Move cur = stack.pop();
			if (cur.isMove) {
				System.out.println("Move " + cur.idx + " from " + cur.from + " to " + cur.to);
				continue;
			}
			if (cur.idx == 1) {
				stack.push(new Move(1, cur.from, cur.to, cur.other, true));
			} else {
				stack.push(new Move(cur.idx - 1, cur.other, cur.to, cur.from, false));
				stack.push(new Move(cur.idx, cur.from, cur.to, cur.other, true));
				stack.push(new Move(cur.idx - 1, cur.from, cur.other, cur.to, false));
			}
		}
	}

	public static void main(String[] args) {
		hanoi1(3);
		System.out.println("------------");
//		hanoi2(3);
		System.out.println("------------");
//		hanoi3(3);
		System.out.println("------------");
		hanoi4(3);
	}
}
