package class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {
//	删除栈底并返回栈底的值 
	public static int f(Stack<Integer> stack) {
		int result = stack.pop();
		if(stack.isEmpty()) {
			return result;
		}else {
			int last = f(stack);//寻找栈底
			stack.push(result);//将其他弹回
			return last;//传递栈底
		}
	}
	public static void reverseStack(Stack<Integer> stack) {
		if(stack.isEmpty()) {
			return;
		}else {
			int i = f(stack);
			reverseStack(stack);
			stack.push(i);
		}
	}
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverseStack(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}
}
