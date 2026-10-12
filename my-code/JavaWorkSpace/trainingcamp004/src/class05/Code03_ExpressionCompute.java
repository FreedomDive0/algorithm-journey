package class05;

import java.util.LinkedList;

public class Code03_ExpressionCompute {
	// 计算公式的值 公式一定合法
	public static int Expression(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	public static int[] f(char[] chs, int i) {// 计算从i到第一个")"或者最后的值
		LinkedList<String> stack = new LinkedList<String>();
		int cur = 0;
		int[] tmp = null;// 存储左括号之后的返回值
		while (i < chs.length && chs[i] != ')') {// 遇到第一个")" 或者 到最后
			if (chs[i] >= '0' && chs[i] <= '9') {// 遇到的字符为数字
				cur = cur * 10 + (chs[i++] - '0');//
			} else if (chs[i] != '(') {// 是+-*/
				addNum(stack, cur);// 将数字添加到栈中
				stack.addLast(String.valueOf(chs[i++]));// 添加运算符号
				cur = 0;// 将cur归零
			} else {// 遇到了左括号
				tmp = f(chs, i + 1);// 算左括号 到右括号的值
				cur = tmp[0];// 返回值
				i = tmp[1] + 1;// 返回i的位置
			}
		}
		addNum(stack, cur);// 1.当前循环到了最后 把最后的数字加进去
		return new int[] { getRes(stack), i };
	}

	public static void addNum(LinkedList<String> stack, int cur) {
		if (!stack.isEmpty()) {// 栈不为空
			String s = stack.pollLast();// 弹出最上面字符查验符号
			if (s.equals("+") || s.equals("-")) {// + -不用管
				stack.addLast(s);
			} else {// 符号为*/ 取出值计算
				int num = Integer.valueOf(stack.pollLast());
				cur = s.equals("*") ? (num * cur) : (num / cur);
			}
		}
		stack.addLast(String.valueOf(cur));// 计算完或是加减法直接添加值
	}

	public static int getRes(LinkedList<String> stack) {// 计算栈里的值
		int res = 0;
		int num = 0;
		String cur = null;
		boolean add = true;
		while (!stack.isEmpty()) {
			cur = stack.pollFirst();// 从左向右计算
			if (cur.equals("+")) {// 只存在+ -法
				add = true;
			} else if (cur.equals("-")) {
				add = false;
			} else {
				num = Integer.valueOf(cur);
				res += add ? num : (-num);
			}
		}
		return res;
	}

}
