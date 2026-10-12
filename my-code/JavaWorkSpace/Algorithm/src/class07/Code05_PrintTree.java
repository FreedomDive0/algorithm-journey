package class07;

public class Code05_PrintTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static void printTree(Node head) {
		print(head,1,17,"H");
	}
	public static void print(Node node,int level,int len,String sign) {
		if(node==null) {
			return;
		}
		print(node.right,level+1,len,"v");
		String value = sign+node.value+sign;
		int left = (len-value.length())/2;
		int right = len - left-value.length();
		value = getSpace((level-1)*len) + getSpace(left) + value + getSpace(right);
		System.out.println(value);
		print(node.left,level+1,len,"^");
	}
	public static String getSpace(int len) {
		String space = " ";
		for(int i=0;i<len-1;i++) {
			space = space +" ";
		}
		return space;
//		String space = " ";
//		StringBuffer buf = new StringBuffer("");
//		for (int i = 0; i < len; i++) {
//			buf.append(space);
//		}
//		return buf.toString();
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);
		System.out.println("————————————————————————————————————————————————————————");
		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);
		System.out.println("————————————————————————————————————————————————————————");
		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);
		
	}
}
