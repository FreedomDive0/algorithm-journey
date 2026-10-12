package class01;

public class Code07_MaxSumInTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int val) {
			value = val;
		}
	}

	// 1.从头到所有叶节点最大路径
	public static int maxHeadToLeaf(Node head) {
		if (head == null) {
			return 0;
		}
		max1 = Integer.MIN_VALUE;
		process1(head, 0);
		return max1;
	}

	public static int max1 = Integer.MIN_VALUE;

	public static void process1(Node head, int sum) {// 不需要返回值 只需要跑到叶节点记录答案即可
		if (head.left == null && head.right == null) {
			max1 = Math.max(max1, sum);
		}
		if (head.left != null) {
			process1(head, sum + head.left.value);
		}
		if (head.right != null) {
			process1(head, sum + head.right.value);
		}
	}

	// 2.从任意节点向下的最大路径
	public static int maxAnyDown(Node head) {
		if (head == null) {
			return 0;
		}
		return process2(head).maxAll;
	}

	public static class NodeInfo {
		int maxAll;// 该节点下最打路径 不一定以x为头
		int maxFromHead;// 以x为头的最大路径

		public NodeInfo(int maxAll, int maxFromHead) {
			this.maxAll = maxAll;
			this.maxFromHead = maxFromHead;
		}

	}

	public static NodeInfo process2(Node head) {
		if (head == null) {
			return new NodeInfo(0, 0);
		}
		NodeInfo leftInfo = process2(head.left);// 从左右树得到信息
		NodeInfo rightInfo = process2(head.right);
//		五种情况
//		1.最大值与x无关:
//		(1)最大值在左 (2)最大值在右
//		2.最大值与x有关:
//		(1)x (2)x+左 (3)x+右
		int p1 = leftInfo.maxAll;
		int p2 = rightInfo.maxAll;
		int p3 = head.value;
		int p4 = head.value + leftInfo.maxFromHead;
		int p5 = head.value + rightInfo.maxFromHead;
		int maxAll = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
		int maxFromHead = Math.max(p3, Math.max(p4, p5));
		return new NodeInfo(maxAll, maxFromHead);
	}

	// 从任意节点到任意节点最长路径
	public static int maxAnyToAny(Node head) {
		if (head == null) {
			return 0;
		}
		return process3(head).maxAll;
	}

	public static NodeInfo process3(Node head) {
		if (head == null) {
			return new NodeInfo(0, 0);
		}
		NodeInfo leftInfo = process3(head.left);// 从左右树得到信息
		NodeInfo rightInfo = process3(head.right);
//		六种情况
//		1.最大值与x无关:
//		(1)最大值在左 (2)最大值在右
//		2.最大值与x有关:
//		(1)x (2)x+左 (3)x+右 (4)x+左+右
		int p1 = leftInfo.maxAll;
		int p2 = rightInfo.maxAll;
		int p3 = head.value;
		int p4 = head.value + leftInfo.maxFromHead;
		int p5 = head.value + rightInfo.maxFromHead;
		int p6 = head.value + leftInfo.maxFromHead + rightInfo.maxFromHead;
		int maxAll = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p6, Math.max(p4, p5)));
		int maxFromHead = Math.max(p3, Math.max(p4, p5));
		return new NodeInfo(maxAll, maxFromHead);
	}
}
