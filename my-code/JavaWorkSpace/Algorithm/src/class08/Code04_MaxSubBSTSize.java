package class08;

import java.util.ArrayList;

public class Code04_MaxSubBSTSize {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static class Infor{
		public int maxSize;
		public boolean isContain;
		public int min;
		public int max;
		
		public Infor(int maxSize,boolean isContain,int min,int max) {
			this.maxSize = maxSize;
			this.isContain = isContain;
			this.max = max;
			this.min = min;
		}
	}
	
	public static int MaxSubBSTSize(Node head) {
		if(head==null) {
			return 0;
		}
		return findSub(head).maxSize;
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
		if(leftInfor!=null) {
			min = Math.min(min, leftInfor.min);
			max = Math.max(max, leftInfor.max);
		}
		if(rightInfor!=null) {
			min = Math.min(min, rightInfor.min);
			max = Math.max(max, rightInfor.max);
		}
		if((leftInfor==null?true:(leftInfor.isContain&&node.value>leftInfor.max))&&(rightInfor==null?true:(rightInfor.isContain&&node.value<rightInfor.min))) {
			maxSize = (leftInfor==null?0:leftInfor.maxSize)+(rightInfor==null?0:rightInfor.maxSize)+1;
			return new Infor(maxSize, true, min, max);
		}else {
			maxSize = Math.max((leftInfor==null?0:leftInfor.maxSize), (rightInfor==null?0:rightInfor.maxSize));
			return new Infor(maxSize, false, min, max);
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

	public static int maxSubBSTSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
				if (maxSubBSTSize1(head) != MaxSubBSTSize(head)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}
}
