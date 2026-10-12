package 二叉树;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem_0117_Populating_Next_Right_Pointers_in_Each_NodeII {
	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}

	public Node connect(Node root) {
		if (root == null) {
			return root;
		}
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			Node last = null;
			for (int i = 0; i < n; i++) {// 每次解决一层
				Node cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
				if (i != 0) {
					last.next = cur;
				}
				last = cur;
			}
		}
		return root;
	}

}
