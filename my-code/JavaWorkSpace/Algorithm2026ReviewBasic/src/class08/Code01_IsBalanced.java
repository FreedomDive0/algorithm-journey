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

	public static class BalanceInfo {
		public boolean isBalanced;
		public int height;

		public BalanceInfo(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}

	}

	public static boolean isBTBalanced(Node head) {
		return processB(head).isBalanced;
	}

	public static BalanceInfo processB(Node node) {
		if (node == null) {
			return new BalanceInfo(true, 0);
		}
		BalanceInfo leftInfo = processB(node.left);
		BalanceInfo rightInfo = processB(node.right);
		boolean isBalance = leftInfo.isBalanced && rightInfo.isBalanced
				&& Math.abs(leftInfo.height - rightInfo.height) < 2;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new BalanceInfo(isBalance, height);
	}
}
