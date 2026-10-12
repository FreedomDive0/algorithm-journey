package class02;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {
	public static class twoStackQueue {
		Stack<Integer> pushStack;
		Stack<Integer> popStack;

		public twoStackQueue() {
			pushStack = new Stack<Integer>();
			popStack = new Stack<Integer>();
		}

		public void add(int value) {
			pushStack.push(value);
			pushToPop();
		}

		public int poll() {
			pushToPop();
			if (popStack.isEmpty())
				throw new RuntimeException("Queue is empty");
			return popStack.pop();
		}

		public void pushToPop() {
			if (!popStack.isEmpty())
				return;
			while (!pushStack.isEmpty()) {
				popStack.push(pushStack.pop());
			}
		}
	}
}
