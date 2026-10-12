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

	public static int maxDistance(Node head) {
		return mProcess(head).maxDistance;
	}

	public static class MDInfo {
		public int maxDistance;
		public int height;

		public MDInfo(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}

	}

	public static MDInfo mProcess(Node node) {
		if (node == null) {
			return new MDInfo(0, 0);
		}
		MDInfo leftInfo = mProcess(node.left);
		MDInfo rightInfo = mProcess(node.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height + 1);
		return new MDInfo(maxDistance, height);
	}
}
