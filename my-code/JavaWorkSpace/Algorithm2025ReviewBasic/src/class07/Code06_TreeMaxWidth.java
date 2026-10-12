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

	public static int treeMaxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		int max = 0;
		int levelNode = 0;
		int curLevel = 1;
		HashMap<Node, Integer> levelMap = new HashMap<Node, Integer>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		levelMap.put(head, 1);
		while (!queue.isEmpty()) {
			head = queue.poll();
			int level = levelMap.get(head);
			if (head.left != null) {
				queue.add(head.left);
				levelMap.put(head.left, level + 1);
			}
			if (head.right != null) {
				queue.add(head.right);
				levelMap.put(head.right, level + 1);
			}
			if (level == curLevel) {
				levelNode++;
			} else {
				max = Math.max(max, levelNode);
				curLevel++;
				levelNode = 1;
			}
		}
		max = Math.max(max, levelNode);// 因为结束当前层 会把上一层的结果进行比较 但是最后一层没有下一层 所以要比较
		return max;
	}

	public static int treeMaxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		int max = 0;
		int levelNode = 0;
		Node curEnd = head;
		Node nextEnd = null;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			levelNode++;
			if (head.left != null) {
				queue.add(head.left);
				nextEnd = head.left;
			}
			if (head.right != null) {
				queue.add(head.right);
				nextEnd = head.right;
			}
			if (head == curEnd) {
				max = Math.max(max, levelNode);
				curEnd = nextEnd;
				levelNode = 0;
			}
		}
		return max;
	}
}
