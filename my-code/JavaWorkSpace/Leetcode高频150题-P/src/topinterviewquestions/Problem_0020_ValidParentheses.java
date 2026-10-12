package topinterviewquestions;

import java.util.Stack;

public class Problem_0020_ValidParentheses {
	public static boolean isValid(String s) {
		if (s == null || s.length() < 1) {
			return true;
		}
		char[] str = s.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		for (char c : str) {
			if (c == '(' || c == '{' || c == '[') {
				stack.add(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char cur = stack.pop();// 分清当前字符和弹出字符
				if (c != (cur == '(' ? ')' : cur == '[' ? ']' : '}')) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		char c = '{';
		char comp = c == '(' ? ')' : c == '[' ? ']' : '}';
		System.out.println(comp);
	}
}
