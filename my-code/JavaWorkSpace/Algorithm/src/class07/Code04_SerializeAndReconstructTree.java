package class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code04_SerializeAndReconstructTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Queue<String> preSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		preS(head, res);
		return res;
	}

	public static void preS(Node head, Queue<String> res) {
		if (head == null) {
			res.add(null);
		} else {
			res.add(String.valueOf(head.value));
			preS(head.left, res);
			preS(head.right, res);
		}
	}

	public static Queue<String> posSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		posS(head, res);
		return res;
	}

	public static void posS(Node head, Queue<String> res) {
		if (head == null) {
			res.add(null);
		} else {
			posS(head.left, res);
			posS(head.right, res);
			res.add(String.valueOf(head.value));
		}
	}

	public static Node buildByPreQueue(Queue<String> prelist) {
		if (prelist == null || prelist.size() == 0) {
			return null;
		}
		return preB(prelist);
	}

	public static Node preB(Queue<String> prelist) {
		String value = prelist.poll();
		if (value == null) {
			return null;
		}
		Node res = new Node(Integer.valueOf(value));
		res.left = preB(prelist);
		res.right = preB(prelist);
		return res;
	}

	public static Node buildByPosQueue(Queue<String> prelist) {
		if (prelist == null || prelist.size() == 0) {
			return null;
		}
		// 后序为左右中 没有中没法连 用stack——>中右左
		Stack<String> stack = new Stack<String>();
		while (!prelist.isEmpty()) {
			stack.push(prelist.poll());
		}
		return posB(stack);
	}

	public static Node posB(Stack<String> stack) {
		String value = stack.pop();
		if (value == null) {
			return null;
		}
		Node res = new Node(Integer.valueOf(value));
		res.right = posB(stack);
		res.left = posB(stack);
		return res;
	}

	public static Queue<String> levelSerial(Node head) {
		Queue<String> res = new LinkedList<String>();
		if (head == null) {
			res.add(null);
		} else {
			res.add(String.valueOf(head.value));
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(head);
			while (!queue.isEmpty()) {
				Node cur = queue.poll();
				if (cur.left != null) {
					queue.add(cur.left);
					res.add(String.valueOf(cur.left.value));
				} else {
					res.add(null);
				}
				if (cur.right != null) {
					queue.add(cur.right);
					res.add(String.valueOf(cur.right.value));
				} else {
					res.add(null);
				}
			}
		}
		return res;
	}

	public static Node buildByLevelQueue(Queue<String> levelList) {
		if (levelList == null || levelList.size() == 0) {
			return null;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Node res = generateNode(levelList.poll());
		if (res != null) {
			queue.add(res);
		}
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			cur.left = generateNode(levelList.poll());
			cur.right = generateNode(levelList.poll());
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return res;

	}

	public static Node generateNode(String value) {
		if (value == null) {
			return null;
		}
		return new Node(Integer.valueOf(value));
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// for test
	public static boolean isSameValueStructure(Node head1, Node head2) {
		if (head1 == null && head2 != null) {
			return false;
		}
		if (head1 != null && head2 == null) {
			return false;
		}
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1.value != head2.value) {
			return false;
		}
		return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
	}

	// for test
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Queue<String> pre = preSerialize(head);
			Queue<String> pos = posSerialize(head);
			Queue<String> level = levelSerial(head);
			Node preBuild = buildByPreQueue(pre);
			Node posBuild = buildByPosQueue(pos);
			Node levelBuild = buildByLevelQueue(level);
			if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}
}
