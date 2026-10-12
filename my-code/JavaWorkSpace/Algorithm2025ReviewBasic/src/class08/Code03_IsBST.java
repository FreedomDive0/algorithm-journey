package class08;

public class Code03_IsBST {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class BSTInfo {
		public int min;
		public int max;
		public boolean isBST;

		public BSTInfo(int min, int max, boolean isBST) {
			this.min = min;
			this.max = max;
			this.isBST = isBST;
		}

	}

	public static boolean isBST(Node root) {
		return processBST(root).isBST;
	}

	public static BSTInfo processBST(Node node) {
		if (node == null) {
			return null;
		}
		BSTInfo leftInfo = processBST(node.left);
		BSTInfo rightInfo = processBST(node.right);
		int min = node.value;
		int max = node.value;
		boolean isBST = true;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			isBST = leftInfo.isBST && node.value > leftInfo.max;
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			isBST = isBST && rightInfo.isBST && node.value < rightInfo.min;
		}
		return new BSTInfo(min, max, isBST);
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(1);
		n1.left = n2;
		System.out.println(isBST(n1));
	}
}
