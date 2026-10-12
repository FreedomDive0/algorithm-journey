package class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int last = f(stack);// 去掉底
		reverse(stack);// 反转剩余
		stack.push(last);// 把底加在顶
	}

	public static int f(Stack<Integer> stack) {
		int cur = stack.pop();
		if (stack.isEmpty()) {
			return cur;
		}
		int last = f(stack);
		stack.push(cur);// 返回之后按序压入
		return last;
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}
}
