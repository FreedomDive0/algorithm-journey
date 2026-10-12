package 栈;

import java.util.Stack;

public class Problem_0224_Basic_Calculator {
	public int calculate(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int sign = 1;
		stack.push(1);
		int res = 0;
		int i = 0;
		int n = s.length();
		while (i < n) {
			if (s.charAt(i) == ' ') {
				i++;
			} else if (s.charAt(i) == '+') {
				sign = stack.peek();
				i++;
			} else if (s.charAt(i) == '-') {
				sign = -stack.peek();
				i++;
			} else if (s.charAt(i) == '(') {
				stack.push(sign);
				i++;
			} else if (s.charAt(i) == ')') {
				stack.pop();
				i++;
			} else {
				long num = 0;
				while (i < n && Character.isDigit(s.charAt(i))) {
					num = num * 10 + s.charAt(i) - '0';
					i++;
				}
				res += sign * num;
			}
		}
		return res;
	}
}
