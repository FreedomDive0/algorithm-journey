package class08;

import java.util.ArrayList;

public class Code05_MaxSubBSTHead {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static class Infor{
		public Node subHead;
		public int maxSize;
		public int min;
		public int max;
		
		public Infor(Node subHead,int maxSize,int min,int max) {
			this.subHead = subHead;
			this.maxSize = maxSize;
			this.max = max;
			this.min = min;
		}
	}
	
	public static Node MaxSubBSTHead(Node head) {
		if(head==null) {
			return null;
		}
		return findSub(head).subHead;
	}
	public static Infor findSub(Node node) {
		if(node==null) {
			return null;
		}
		Infor leftInfor = findSub(node.left);
		Infor rightInfor = findSub(node.right);
		int min = node.value;
		int max = node.value;
		int maxSize = 0;
		Node maxHead = null;
		if(leftInfor!=null) {
			min = Math.min(min, leftInfor.min);
			max = Math.max(max, leftInfor.max);
			maxSize = leftInfor.maxSize;
			maxHead = leftInfor.subHead;
		}
		if(rightInfor!=null) {
			min = Math.min(min, rightInfor.min);
			max = Math.max(max, rightInfor.max);
			maxHead = rightInfor.maxSize>maxSize?rightInfor.subHead:maxHead;
		}
		if((leftInfor==null?true:(leftInfor.subHead==node.left&&node.value>leftInfor.max))&&(rightInfor==null?true:(rightInfor.subHead==node.right&&node.value<rightInfor.min))) {
			maxSize = (leftInfor==null?0:leftInfor.maxSize)+(rightInfor==null?0:rightInfor.maxSize)+1;
			return new Infor(node, maxSize, min, max);
		}else {
			maxSize = Math.max((leftInfor==null?0:leftInfor.maxSize), (rightInfor==null?0:rightInfor.maxSize));
			return new Infor(maxHead, maxSize, min, max);
		}
	}
	
//	for test
	public static int getBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static Node maxSubBSTHead1(Node head) {
		if (head == null) {
			return null;
		}
		if (getBSTSize(head) != 0) {
			return head;
		}
		Node leftAns = maxSubBSTHead1(head.left);
		Node rightAns = maxSubBSTHead1(head.right);
		return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
	}

	
	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxSubBSTHead1(head) != MaxSubBSTHead(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
