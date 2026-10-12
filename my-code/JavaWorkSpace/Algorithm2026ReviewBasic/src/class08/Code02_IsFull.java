package class08;

public class Code02_IsFull {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class FullInfo {
		public int nodes;
		public int height;

		public FullInfo(int nodes, int height) {
			this.nodes = nodes;
			this.height = height;
		}

	}

	// 判断是否是满二叉树
	public static boolean isFull(Node head) {
		if (head == null) {
			return true;
		}
		FullInfo info = fProcess(head);
		return ((2 << (info.height - 1)) - 1) == info.nodes;
	}

	public static FullInfo fProcess(Node node) {
		if (node == null) {
			return new FullInfo(0, 0);
		}
		FullInfo leftInfo = fProcess(node.left);
		FullInfo rightInfo = fProcess(node.right);
		int nodes = leftInfo.nodes + rightInfo.nodes + 1;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new FullInfo(nodes, height);
	}

}
