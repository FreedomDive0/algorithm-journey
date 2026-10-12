package class11;

import java.util.Stack;

public class Code01_Hanoi {
	// 汉诺塔问题
	public static void hanoi1(int N) {
		if (N < 0)
			return;
		leftToRight(N);
	}

	public static void leftToRight(int N) {
		if (N == 1) {
			System.out.println("move 1 from left to right");
			return;
		}
		leftToMid(N - 1);
		System.out.println("move " + N + " from left to right");
		midToRight(N - 1);
	}

	public static void leftToMid(int N) {
		if (N == 1) {
			System.out.println("move 1 from left to mid");
			return;
		}
		leftToRight(N - 1);
		System.out.println("move " + N + " from left to mid");
		rightToMid(N - 1);
	}

	public static void midToRight(int N) {
		if (N == 1) {
			System.out.println("move 1 from mid to right");
			return;
		}
		midToLeft(N - 1);
		System.out.println("move " + N + " from mid to right");
		leftToRight(N - 1);
	}

	public static void midToLeft(int N) {
		if (N == 1) {
			System.out.println("move 1 from mid to left");
			return;
		}
		midToRight(N - 1);
		System.out.println("move " + N + " from mid to left");
		rightToLeft(N - 1);
	}

	public static void rightToLeft(int N) {
		if (N == 1) {
			System.out.println("move 1 from right to left");
			return;
		}
		rightToMid(N - 1);
		System.out.println("move " + N + " from right to left");
		midToLeft(N - 1);
	}

	public static void rightToMid(int N) {
		if (N == 1) {
			System.out.println("move 1 from right to mid");
			return;
		}
		rightToLeft(N - 1);
		System.out.println("move " + N + " from right to mid");
		leftToMid(N - 1);
	}

	public static void hanoi2(int N) {
		if (N < 0)
			return;
		moveProcess(N, "left", "right", "mid");
	}

	public static void moveProcess(int N, String from, String to, String other) {
		if (N == 1) {
			System.out.println("move " + N + " from " + from + " to " + to);
			return;
		}
		moveProcess(N - 1, from, other, to);
		System.out.println("move " + N + " from " + from + " to " + to);
		moveProcess(N - 1, other, to, from);
	}

	public static class Record {
		public String from;
		public String to;
		public String other;
		public boolean finish1;
		public int base;

		public Record(String from, String to, String other, int base) {
			this.from = from;
			this.to = to;
			this.other = other;
			finish1 = false;
			this.base = base;
		}

	}

	public static void hanoi3(int N) {
		if (N < 1)
			return;
		Stack<Record> stack = new Stack<Record>();
		stack.push(new Record("left", "right", "mid", N));
		while (!stack.isEmpty()) {
			Record record = stack.pop();
			if (record.base == 1) {
				System.out.println("move " + record.base + " from " + record.from + " to " + record.to);
				if (!stack.isEmpty())
					stack.peek().finish1 = true;
			} else {
				if (!record.finish1) {
					stack.push(record);
					stack.push(new Record(record.from, record.other, record.to, record.base - 1));
				} else {
					System.out.println("move " + record.base + " from " + record.from + " to " + record.to);
					stack.push(new Record(record.other, record.to, record.from, record.base - 1));
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 5;
//		hanoi1(n);
//		System.out.println("============");
//		hanoi2(n);
//		System.out.println("============");
		hanoi3(n);
	}
}
