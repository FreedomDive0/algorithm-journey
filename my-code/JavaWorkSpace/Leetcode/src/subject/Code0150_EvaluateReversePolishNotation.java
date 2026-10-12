package subject;

import java.util.Stack;

public class Code0150_EvaluateReversePolishNotation {
	// 逆波兰表达式求值
	public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0) {
			return 0;
		}
		int num1 = 0;
		int num2 = 0;
		Stack<String> stack = new Stack<String>();
		for (String token : tokens) {
			if (token.equals("+")) {
				num1 = Integer.valueOf(stack.pop());
				num2 = Integer.valueOf(stack.pop());
				stack.push(num1 + num2 + "");
			} else if (token.equals("-")) {
				num1 = Integer.valueOf(stack.pop());
				num2 = Integer.valueOf(stack.pop());
				stack.push(num2 - num1 + "");
			} else if (token.equals("*")) {
				num1 = Integer.valueOf(stack.pop());
				num2 = Integer.valueOf(stack.pop());
				stack.push(num1 * num2 + "");
			} else if (token.equals("/")) {
				num1 = Integer.valueOf(stack.pop());
				num2 = Integer.valueOf(stack.pop());
				stack.push(num2 / num1 + "");// 记得运算顺序得反转
			} else {
				stack.push(token);
			}
		}
		return Integer.valueOf(stack.pop());
	}
}
