package class08;

public class Code04_MaxSubBSTSize {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 最大搜索子树节点个数
	public static int maxSubBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		return mSBSTProcess(head).nodes;
	}

	public static class MSBSTInfo {
		public boolean isBST;
		public int min;
		public int max;
		public int nodes;

		public MSBSTInfo(boolean isBST, int min, int max, int nodes) {
			this.isBST = isBST;
			this.min = min;
			this.max = max;
			this.nodes = nodes;
		}

	}

	public static MSBSTInfo mSBSTProcess(Node node) {
		if (node == null) {
			return null;
		}
		MSBSTInfo leftInfo = mSBSTProcess(node.left);
		MSBSTInfo rightInfo = mSBSTProcess(node.right);
		int max = node.value;
		int min = node.value;
		boolean isBST = true;
		int nodes = 0;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			isBST = node.value > leftInfo.max && leftInfo.isBST;
			nodes = leftInfo.nodes;
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			if (isBST)
				isBST = node.value < rightInfo.min && rightInfo.isBST;
			nodes = Math.max(nodes, rightInfo.nodes);
		}
		if (isBST)// 统一判断更好
			nodes = (leftInfo != null ? leftInfo.nodes : 0) + (rightInfo != null ? rightInfo.nodes : 0) + 1;
		return new MSBSTInfo(isBST, min, max, nodes);
	}
}
