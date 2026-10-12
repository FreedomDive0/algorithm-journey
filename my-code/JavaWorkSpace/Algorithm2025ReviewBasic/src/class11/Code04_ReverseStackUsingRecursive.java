package class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = f(stack);// 获取栈底
		reverse(stack);// 反转剩余栈
		stack.push(i);// 添加栈底到栈顶
	}

	// 弹出栈底 其他保持不变
	public static int f(Stack<Integer> stack) {
		int res = stack.pop();
		if (stack.isEmpty()) {
			return res;
		}
		int last = f(stack);
		stack.push(res);
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
