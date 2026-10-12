package 栈;

import java.util.Stack;

public class Problem_0155_Min_Stack {
	class MinStack {
		Stack<Integer> nums;
		Stack<Integer> min;

		public MinStack() {
			nums = new Stack<Integer>();
			min = new Stack<Integer>();
		}

		public void push(int val) {
			nums.add(val);
			if (min.isEmpty()) {
				min.add(val);
			} else if (min.peek() >= val) {
				min.add(val);
			}
		}

		public void pop() {
			int num = nums.pop();
			if (num == min.peek()) {
				min.pop();
			}
		}

		public int top() {
			return nums.peek();
		}

		public int getMin() {
			return min.peek();
		}
	}
}
