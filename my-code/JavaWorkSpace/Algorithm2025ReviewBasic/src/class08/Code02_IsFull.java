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

	public static class isFullInfo {
		public boolean isFull;
		public int height;

		public isFullInfo(boolean isFull, int height) {
			this.isFull = isFull;
			this.height = height;
		}

	}

	public static boolean isFull(Node root) {
		return process(root).isFull;
	}

	public static isFullInfo process(Node node) {
		if (node == null) {
			return new isFullInfo(true, 0);
		}
		isFullInfo leftInfo = process(node.left);
		isFullInfo rightInfo = process(node.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		return new isFullInfo(isFull, height);
	}
}
