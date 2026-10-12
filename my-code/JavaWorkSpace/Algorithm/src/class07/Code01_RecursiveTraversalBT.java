package class07;

public class Code01_RecursiveTraversalBT {
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
	}
//	递归序
	public static void f(Node head) {
		if (head == null) {
			return;
		}
		// 1
		f(head.left);
		// 2
		f(head.right);
		// 3
	}
	
	public static void preTraversal(Node head) {
		if(head==null) {
			return;
		}
		System.out.print(head.value+" ");
		preTraversal(head.left);
		preTraversal(head.right);
	}
	
	public static void inTraversal(Node head) {
		if(head==null) {
			return;
		}
		inTraversal(head.left);
		System.out.print(head.value+" ");
		inTraversal(head.right);
	}
	
	public static void posTraversal(Node head) {
		if(head==null) {
			return;
		}
		posTraversal(head.left);
		posTraversal(head.right);
		System.out.print(head.value+" ");
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		preTraversal(head);
		System.out.println("========");
		inTraversal(head);
		System.out.println("========");
		posTraversal(head);
		System.out.println("========");

	}
}
