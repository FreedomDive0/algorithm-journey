package class02;

import java.util.Stack;

public class Code05_GetMinStack {
	// 最小栈 随时得到栈中的最小值
	public static class MinStack {
		public Stack<Integer> stack;
		public Stack<Integer> minStack;

		public MinStack() {
			stack = new Stack<Integer>();
			minStack = new Stack<Integer>();
		}

		public void push(int value) {
			if (stack.isEmpty()) {
				stack.push(value);
				minStack.push(value);
			} else {
				int minNum = Math.min(value, minStack.peek());
				stack.push(value);
				minStack.push(minNum);
			}
		}

		public int pop() {
			if (stack.isEmpty()) {
				throw new RuntimeException("stack is empty");
			}
			int res = stack.pop();
			minStack.pop();
			return res;
		}

		public int getmin() {
			if (minStack.isEmpty()) {
				throw new RuntimeException("stack is empty");
			}
			return minStack.peek();
		}
	}
}
