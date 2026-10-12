package class05;

public class Code03_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 请保证head为头的树，是完全二叉树
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// node在第level层，h是总的深度（h永远不变，全局变量
	// 以node为头的完全二叉树，节点个数是多少
	public static int bs(Node node, int Level, int h) {
		if (Level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, Level + 1) == h) {
			return (1 << (h - Level)) + bs(node.right, Level + 1, h);
		} else {
			return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
		}
	}

	// 如果node在第level层，
	// 求以node为头的子树，最大深度是多少
	// node为头的子树，一定是完全二叉树
	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	// 测试
	public static int CTN(Node head) {
		if (head == null) {
			return 0;
		}
		return process(head, 1, mostRightlevel(head, 1));
	}

	// level层有多少个节点
	public static int process(Node cur, int level, int height) {
		if (level == height) {
			return 1;
		}
		if (mostRightlevel(cur.right, level + 1) == height) {// 左侧是满的
			return (1 << (height - level)) + process(cur.right, level + 1, height);// 左侧个数+右侧
		} else {
			return (1 << (height - level - 1)) + process(cur.left, level + 1, height);// 右侧个数+左侧
		}
	}

	// 得到右树能到第几层
	public static int mostRightlevel(Node cur, int level) {
		while (cur != null) {
			cur = cur.left;
			level++;
		}
		return level - 1;
	}

	// 测试
	// 3.完全二叉树节点个数
	public static int completeTreeNodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return processCTNN(head, 1, mostLeftLevel(head, 1));
	}

	public static int processCTNN(Node node, int level, int h) {
		if (level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, level + 1) == h) {
			return (1 << (h - level)) + processCTNN(node.right, level + 1, h);// h-level表示下方对应规模的节点
		} else {
			return (1 << (h - level - 1)) + processCTNN(node.left, level + 1, h);
		}
	}

//	public static int mostLeftLevel(Node node, int level) {
//		while (node != null) {
//			level++;
//			node = node.left;
//		}
//		return level - 1;
//	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));
		System.out.println(CTN(head));
		System.out.println(completeTreeNodeNum(head));

	}

}
