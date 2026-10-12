package class05;

public class Code03_CompleteTreeNodeNumber {
	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}

	}

	public static int CTN(Node head) {
		if (head == null) {
			return 0;
		}
		return process(head, 1, mostRightlevel(head, 1));
	}

	// level层有多少个节点
	public static int process(Node cur, int level, int height) {
		if (level == height) {
			return 1;
		}
		if (mostRightlevel(cur.right, level + 1) == height) {// 左侧是满的
			return (1 << (height - level)) + process(cur.right, level + 1, height);// 左侧个数+右侧
		} else {
			return (1 << (height - level - 1)) + process(cur.left, level + 1, height);// 右侧个数+左侧
		}
	}

	// 得到右树能到第几层
	public static int mostRightlevel(Node cur, int level) {
		while (cur != null) {
			cur = cur.left;
			level++;
		}
		return level - 1;
	}
}
