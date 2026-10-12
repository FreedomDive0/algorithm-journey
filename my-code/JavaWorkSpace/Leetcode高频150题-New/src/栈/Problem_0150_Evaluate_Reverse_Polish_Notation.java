package 栈;

import java.util.Stack;

public class Problem_0150_Evaluate_Reverse_Polish_Notation {
	public int evalRPN(String[] tokens) {
		if (tokens.length == 1) {
			return Integer.valueOf(tokens[0]);
		}
		Stack<Integer> stack = new Stack<Integer>();
		for (String str : tokens) {
			if ("+".equals(str)) {
				stack.add(stack.pop() + stack.pop());
			} else if ("-".equals(str)) {
				stack.add(-stack.pop() + stack.pop());
			} else if ("*".equals(str)) {
				stack.add(stack.pop() * stack.pop());
			} else if ("/".equals(str)) {
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.add(num2 / num1);
			} else {
				stack.add(Integer.valueOf(str));
			}
		}
		return stack.pop();
	}
}
