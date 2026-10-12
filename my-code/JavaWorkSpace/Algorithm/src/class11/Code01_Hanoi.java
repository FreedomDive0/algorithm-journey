package class11;

import java.util.Stack;

public class Code01_Hanoi {
	public static void hanoi1(int n) {
		leftToRight(n);
	}
	public static void leftToRight(int n) {
		if(n==1) {
			System.out.println("Move 1 from left to right");
			return;
		}
		leftToMid(n-1);
		System.out.println("Move "+ n + " from left to right");
		midToRight(n-1);
	}
	public static void leftToMid(int n) {
		if(n==1) {
			System.out.println("Move 1 from left to mid");
			return;
		}
		leftToRight(n-1);
		System.out.println("Move "+ n + " from left to mid");
		rightToMid(n-1);
	}
	public static void rightToMid(int n) {
		if(n==1) {
			System.out.println("Move 1 from right to mid");
			return;
		}
		rightToLeft(n-1);
		System.out.println("Move "+ n + " from right to mid");
		leftToMid(n-1);
	}
	public static void rightToLeft(int n) {
		if(n==1) {
			System.out.println("Move 1 from right to left");
			return;
		}
		rightToMid(n-1);
		System.out.println("Move "+ n + " from right to left");
		midToLeft(n-1);
	}
	public static void midToLeft(int n) {
		if(n==1) {
			System.out.println("Move 1 from mid to left");
			return;
		}
		midToRight(n-1);
		System.out.println("Move "+ n + " from mid to left");
		rightToLeft(n-1);
	}
	public static void midToRight(int n) {
		if(n==1) {
			System.out.println("Move 1 from mid to right");
			return;
		}
		midToLeft(n-1);
		System.out.println("Move "+ n + " from mid to right");
		leftToRight(n-1);
	}
	
	public static void hanoi2(int n) {
		if(n>0) {
			func(n,"left","right","mid");
		}
	}
	public static void func(int n,String from,String to,String other) {
		if(n==1) {
			System.out.println("move 1 from "+from+" to "+to);
		}else {
			func(n-1,from,other,to);
			System.out.println("move "+n+" from "+from+" to "+to);
			func(n-1,other,to,from);
		}
	}
	//非递归版本
	public static class Record{
		public boolean finish;//该条记录是否可以移动
		public int base;//该条记录是第几层的
		public String from;//从哪到哪 无关的是哪一个
		public String to;
		public String other;
		public Record(int base, String from, String to, String other) {
			finish = false;
			this.base = base;
			this.from = from;
			this.to = to;
			this.other = other;
		}
	}
	public static void hanoi3(int N) {
		if(N<0) {
			return;
		}
		Stack<Record> stack  = new Stack<Record>();
		stack.add(new Record(N,"left","right","mid"));
		while(!stack.isEmpty()) {
			Record cur = stack.pop();
			if(cur.base==1) {
				System.out.println("Move 1 from "+cur.from+" to "+cur.to);
				if(!stack.isEmpty()) {
					stack.peek().finish = true;//栈不为空 则下一项可以移动
				}
			}else {
				if(cur.finish) {//这一层可以移动 
					System.out.println("Move "+cur.base+" from "+cur.from+" to "+cur.to);
					//这一层移动完毕并且不是最上面一层 则需要上面一层跟随移动
					stack.add(new Record(cur.base-1,cur.other,cur.to,cur.from));
				}else {//这一层不可以移动 上一层需要先移动腾位置
					stack.add(cur);
					stack.add(new Record(cur.base-1,cur.from,cur.other,cur.to));
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("=============================");
		hanoi2(n);
		System.out.println("=============================");
		hanoi3(n);
	}
}
