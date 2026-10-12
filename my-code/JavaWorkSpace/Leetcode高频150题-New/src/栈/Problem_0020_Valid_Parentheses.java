package 栈;

import java.util.Stack;

public class Problem_0020_Valid_Parentheses {
	public boolean isValid(String s) {
		if ((s.length() & 1) != 0) {
			return false;
		}
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '{' || c == '[' || c == '(') {
				stack.add(c);
			} else {// 保证栈中有东西弹
				if (stack.isEmpty() || c == '}' && stack.pop() != '{' || c == ']' && stack.pop() != '['
						|| c == ')' && stack.pop() != '(') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
