package class07;

import java.util.Stack;


public class Code02_UnRecursiveTraversalBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}
	
	public static void pre(Node head) {
		if(head==null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.print(cur.value+" ");
			if(cur.right!=null) {
				stack.add(cur.right);
			}
			if(cur.left!=null) {
				stack.add(cur.left);
			}
		}
	}
//	使用两个栈 头右左——>左右头
	public static void pos1(Node head) {
		if(head==null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		Stack<Node> res = new Stack<Node>();
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			res.add(cur);
			if(cur.left!=null) {
				stack.add(cur.left);
			}
			if(cur.right!=null) {
				stack.add(cur.right);
			}
		}
		while(!res.isEmpty()) {
			System.out.print(res.pop().value+" ");
		}
	}
//	使用一个栈
	public static void pos2(Node head) {
		if(head==null) {
			return;
		}
		Node last = head;
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while(!stack.isEmpty()) {
			Node cur = stack.peek();
			if(cur.left!=null&&cur.left!=last&&cur.right!=last) {//有左孩子且左右孩子都没有处理
				stack.add(cur.left);
			}
			else if(cur.right!=null&&cur.right!=last) {//有右孩子 有孩子没被处理
				stack.add(cur.right);
			}else {
				last = stack.pop();
				System.out.print(last.value+" ");
			}
			
		}
	}
//	中序遍历
//	public static void in(Node head) {
//		if(head==null) {
//			return;
//		}
//		Stack<Node> stack = new Stack<Node>();
//		stack.add(head);
//		while(!stack.isEmpty()) {
//			if(head.left!=null) {
//				stack.add(head);
//				head = head.left;
//			}else {
//				Node cur = stack.pop();
//				if(cur.right!=null) {
//					stack.add(cur.right);
//				}
//				System.out.print(cur.value+" ");
//			}
//		}
//	}
	public static void in(Node head) {
		if(head!=null) {
			Stack<Node> stack = new Stack<Node>();
			while(head!=null||!stack.isEmpty()) {
				if(head!=null) {
					stack.add(head);
					head = head.left;
				}else {
					Node cur = stack.pop();
					head = cur.right;
					System.out.print(cur.value+" ");
				}
			}
		}
	}
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}
}
