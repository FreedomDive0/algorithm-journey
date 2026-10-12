package class02;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {
	// 双栈实现队列
	public static class TwoStackQueue {
		public Stack<Integer> inStack;
		public Stack<Integer> outStack;

		public TwoStackQueue() {
			inStack = new Stack<Integer>();
			outStack = new Stack<Integer>();
		}

		public void add(int value) {
			inStack.push(value);
			in2Out();
		}

		public int poll() {
			in2Out();
			if (outStack.isEmpty()) {
				throw new RuntimeException("queue is empty");
			}
			return outStack.pop();
		}

		public int peek() {
			in2Out();
			if (outStack.isEmpty()) {
				throw new RuntimeException("queue is empty");
			}
			return outStack.peek();
		}

		private void in2Out() {
			if (outStack.isEmpty()) {
				while (!inStack.isEmpty()) {
					outStack.push(inStack.pop());
				}
			}
		}
	}
}
