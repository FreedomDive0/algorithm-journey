package class05;

import java.util.LinkedList;

public class Code03_ExpressionCompute {

	public static int getValue(String str) {
		return value(str.toCharArray(), 0)[0];
	}

	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组
	// 0) 负责的这一段的结果是多少
	// 1) 负责的这一段计算到了哪个位置
	public static int[] value(char[] str, int i) {
		LinkedList<String> que = new LinkedList<String>();
		int cur = 0;
		int[] bra = null;
		// 从i出发，开始撸串
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') {
				cur = cur * 10 + str[i++] - '0';
			} else if (str[i] != '(') { // 遇到的是运算符号
				addNum(que, cur);
				que.addLast(String.valueOf(str[i++]));
				cur = 0;
			} else { // 遇到左括号了
				bra = value(str, i + 1);
				cur = bra[0];
				i = bra[1] + 1;
			}
		}
		addNum(que, cur);
		return new int[] { getNum(que), i };
	}

	public static void addNum(LinkedList<String> que, int num) {
		if (!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();
			if (top.equals("+") || top.equals("-")) {
				que.addLast(top);
			} else {
				cur = Integer.valueOf(que.pollLast());
				num = top.equals("*") ? (cur * num) : (cur / num);
			}
		}
		que.addLast(String.valueOf(num));
	}

	public static int getNum(LinkedList<String> que) {
		int res = 0;
		boolean add = true;
		String cur = null;
		int num = 0;
		while (!que.isEmpty()) {
			cur = que.pollFirst();
			if (cur.equals("+")) {
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

	// 测试
	// 计算公式的值 公式一定合法
	public static int Expression(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	public static int[] f(char[] chs, int i) {
		LinkedList<String> stack = new LinkedList<String>();
		int cur = 0;
		int[] tmp = null;
		while (i < chs.length && chs[i] != ')') {
			if (chs[i] >= '0' && chs[i] <= '9') {
				cur = cur * 10 + (chs[i++] - '0');
			} else if (chs[i] != '(') {// 是+-*/
				addNum1(stack, cur);
				stack.addLast(String.valueOf(chs[i++]));
				cur = 0;
			} else {
				tmp = f(chs, i + 1);
				cur = tmp[0];
				i = tmp[1] + 1;
			}
		}
		addNum1(stack, cur);
		return new int[] { getRes(stack), i };
	}

	public static void addNum1(LinkedList<String> stack, int cur) {
		if (!stack.isEmpty()) {
			String s = stack.pollLast();
			if (s.equals("+") || s.equals("-")) {
				stack.addLast(s);
			} else {
				int num = Integer.valueOf(stack.pollLast());
				cur = s.equals("*") ? (num * cur) : (num / cur);
			}
		}
		stack.addLast(String.valueOf(cur));
	}

	public static int getRes(LinkedList<String> stack) {
		int res = 0;
		int num = 0;
		String cur = null;
		boolean add = true;
		while (!stack.isEmpty()) {
			cur = stack.pollFirst();
			if (cur.equals("+")) {
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

	public static void main(String[] args) {
		String exp = "48*((70-65)-43)+8*1";
		System.out.println(getValue(exp));
		System.out.println(Expression("48*((70-65)-43)+8*1"));

		exp = "4*(6+78)+53-9/2+45*8";
		System.out.println(getValue(exp));
		System.out.println(Expression(exp));

		exp = "10-5*3";
		System.out.println(getValue(exp));
		System.out.println(Expression(exp));

		exp = "-3*4";
		System.out.println(getValue(exp));
		System.out.println(Expression(exp));

		exp = "3+1*4";
		System.out.println(getValue(exp));
		System.out.println(Expression(exp));

	}

}
