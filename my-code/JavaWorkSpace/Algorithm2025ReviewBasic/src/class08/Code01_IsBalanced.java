package class08;

public class Code01_IsBalanced {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class Info {
		public boolean isBalanced;
		public int height;

		public Info(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}

	}

	public static boolean isBalanced(Node root) {
		return process(root).isBalanced;
	}

	public static Info process(Node node) {
		if (node == null) {
			return new Info(true, 0);
		}
		Info leftInfo = process(node.left);
		Info rightInfo = process(node.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
				&& Math.abs(leftInfo.height - rightInfo.height) < 2;
		return new Info(isBalanced, height);
	}
}
