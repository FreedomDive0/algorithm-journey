package class08;

public class Code05_MaxSubBSTHead {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node maxSubBSTHead(Node head) {
		if (head == null) {
			return null;
		}
		return mSBSTHProcess(head).head;
	}

	public static class MSBSTHInfo {
		public Node head;
		public int max;
		public int min;
		public int nodes;

		public MSBSTHInfo(Node head, int max, int min, int nodes) {
			this.head = head;
			this.max = max;
			this.min = min;
			this.nodes = nodes;
		}

	}

	public static MSBSTHInfo mSBSTHProcess(Node node) {
		if (node == null) {
			return null;
		}
		MSBSTHInfo leftInfo = mSBSTHProcess(node.left);
		MSBSTHInfo rightInfo = mSBSTHProcess(node.right);
		int min = node.value;
		int max = node.value;
		int nodes = 0;
		boolean isBST = true;
		Node head = null;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			nodes = leftInfo.nodes;
			isBST = node.left == leftInfo.head && node.value > leftInfo.max;
			head = leftInfo.head;
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			if (rightInfo.nodes > nodes) {
				nodes = rightInfo.nodes;
				head = rightInfo.head;
			}
			if (isBST)
				isBST = node.right == rightInfo.head && node.value < rightInfo.min;
		}
		if (isBST) {
			nodes = (leftInfo != null ? leftInfo.nodes : 0) + (rightInfo != null ? rightInfo.nodes : 0) + 1;
			head = node;
		}
		return new MSBSTHInfo(head, max, min, nodes);
	}
}
