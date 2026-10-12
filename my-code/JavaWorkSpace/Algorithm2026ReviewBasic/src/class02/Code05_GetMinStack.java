package class02;

import java.util.Stack;

public class Code05_GetMinStack {
	public static class GetMinStack {
		Stack<Integer> minStack;
		Stack<Integer> dataStack;

		public GetMinStack() {
			minStack = new Stack<Integer>();
			dataStack = new Stack<Integer>();
		}

		public void push(int value) {
			if (minStack.isEmpty() || minStack.peek() > value) {
				minStack.push(value);
			} else {
				minStack.push(minStack.peek());
			}
			dataStack.push(value);
		}

		public int pop() {
			if (dataStack.isEmpty())
				throw new RuntimeException("stack is empty");
			minStack.pop();
			return dataStack.pop();
		}

		public int getMin() {
			if (dataStack.isEmpty())
				throw new RuntimeException("stack is empty");
			return minStack.peek();
		}
	}
}
