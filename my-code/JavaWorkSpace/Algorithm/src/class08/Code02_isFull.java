package class08;

public class Code02_isFull {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class Infor {
		public int height;
		public int nodes;

		public Infor(int height, int nodes) {
			this.height = height;
			this.nodes = nodes;
		}
	}

	public static boolean isFull(Node head) {
		if (head == null) {
			return true;
		}
		Infor infor = process(head);
		return (Math.pow(2, infor.height) - 1) == infor.nodes;
	}

	public static Infor process(Node node) {
		if (node == null) {
			return new Infor(0, 0);
		}
		Infor leftInfor = process(node.left);
		Infor rightInfor = process(node.right);
		return new Infor(Math.max(leftInfor.height, rightInfor.height) + 1, leftInfor.nodes + rightInfor.nodes + 1);
	}

//	for test
	public static boolean isFull1(Node head) {
		if (head == null) {
			return true;
		}
		int height = h(head);
		int nodes = n(head);
		return (1 << height) - 1 == nodes;
	}

	public static int h(Node head) {
		if (head == null) {
			return 0;
		}
		return Math.max(h(head.left), h(head.right)) + 1;
	}

	public static int n(Node head) {
		if (head == null) {
			return 0;
		}
		return n(head.left) + n(head.right) + 1;
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

	// 复习
	public static boolean isFull2(Node root) {
		isFInfo info = processF(root);
		return (Math.pow(2, info.height) - 1) == info.nodes;
	}

	public static class isFInfo {
		int height;
		int nodes;

		public isFInfo(int height, int nodes) {
			this.height = height;
			this.nodes = nodes;
		}

	}

	public static isFInfo processF(Node root) {
		if (root == null) {
			return new isFInfo(0, 0);
		}
		isFInfo leftInfo = processF(root.left);
		isFInfo rightInfo = processF(root.right);
		return new isFInfo(Math.max(leftInfo.height, rightInfo.height) + 1, leftInfo.nodes + rightInfo.nodes + 1);
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isFull1(head) != isFull2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
