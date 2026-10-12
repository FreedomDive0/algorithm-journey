package class04;

public class Code02_BSTtoDoubleLinkedList {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node BTStoDLL(Node head) {
		if (head == null) {
			return null;
		}
		return process(head).start;
	}

	// 给定头节点 返回以该节点为头的树 转换为双向链表的头尾节点
	public static Info process(Node node) {
		if (node == null) {
			return new Info(null, null);
		}
		Info leftInfo = process(node.left);
		Info rightInfo = process(node.right);
		// 得到左右树的信息开始连接
		if (leftInfo.end != null) {// 如果存在 将左树最后一个节点与node相连 next
			leftInfo.end.right = node;
		}
		node.left = leftInfo.end;
		if (rightInfo.start != null) {// 如果存在 将右树第一个节点与node相连 last
			rightInfo.start.left = node;
		}
		node.right = rightInfo.start;
		Node head = leftInfo.start != null ? leftInfo.start : node;
		Node tail = rightInfo.end != null ? rightInfo.end : node;
		return new Info(head, tail);
	}

	public static class Info {
		Node start;
		Node end;

		public Info(Node start, Node end) {
			this.start = start;
			this.end = end;
		}

	}
}
