package class08;

public class Code08_MaxDistance {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class DisInfo {
		public int height;
		public int maxDis;

		public DisInfo(int height, int maxDis) {
			this.height = height;
			this.maxDis = maxDis;
		}

	}

	public static int maxDis(Node root) {
		return procesDis(root).maxDis;
	}

	public static DisInfo procesDis(Node node) {
		if (node == null) {
			return new DisInfo(0, 0);
		}
		DisInfo leftInfo = procesDis(node.left);
		DisInfo rightInfo = procesDis(node.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int maxDis = leftInfo.height + rightInfo.height + 1;
		maxDis = Math.max(maxDis, Math.max(leftInfo.maxDis, rightInfo.maxDis));
		return new DisInfo(height, maxDis);
	}
}
