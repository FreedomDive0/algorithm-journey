package class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code06_TreeMaxWidth {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 二叉树最大宽度 使用HashMap
	public static int maxWidthUseMap1(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<Node>();
		HashMap<Node, Integer> levelMap = new HashMap<Node, Integer>();
		queue.add(head);
		levelMap.put(head, 1);
		int max = 0;
		int curLevel = 1;
		int count = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int nodeLevel = levelMap.get(cur);
			if (cur.left != null) {
				queue.add(cur.left);
				levelMap.put(cur.left, nodeLevel + 1);
			}
			if (cur.right != null) {
				queue.add(cur.right);
				levelMap.put(cur.right, nodeLevel + 1);
			}
			if (nodeLevel == curLevel) {
				count++;
			} else {
				max = Math.max(max, count);
				count = 1;
				curLevel = nodeLevel;
			}
		}
		max = Math.max(max, count);
		return max;
	}

	// 二叉树最大宽度 不使用HashMap
	public static int maxWidth(Node head) {
		if (head == null) {
			return 0;
		}
		Node curEnd = head;
		Node nextEnd = null;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		int max = 0;
		int count = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			count++;
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			if (cur == curEnd) {
				max = Math.max(max, count);
				curEnd = nextEnd;
				count = 0;
			}
		}
		return max;
	}
}
