package class07;

import java.util.LinkedList;
import java.util.Queue;

public class Code03_LevelTraversalBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void levelTraversalBT(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			System.out.println(head.value + " ");
			if (head.left != null) {
				queue.offer(head.left);
			}
			if (head.right != null) {
				queue.offer(head.right);
			}
		}
	}
}
