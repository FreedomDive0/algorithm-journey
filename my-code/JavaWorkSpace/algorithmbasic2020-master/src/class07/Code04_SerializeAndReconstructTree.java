package class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code04_SerializeAndReconstructTree {
	/*
	 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化， 以下代码全部实现了。 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
	 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。 比如如下两棵树 __2 / 1 和 1__ \ 2 补足空位置的中序遍历结果都是{
	 * null, 1, null, 2, null}
	 * 
	 */
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Queue<String> preSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		pres(head, ans);
		return ans;
	}

	public static void pres(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}

	public static Queue<String> inSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		ins(head, ans);
		return ans;
	}

	public static void ins(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ins(head.left, ans);
			ans.add(String.valueOf(head.value));
			ins(head.right, ans);
		}
	}

	public static Queue<String> posSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		poss(head, ans);
		return ans;
	}

	public static void poss(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			poss(head.left, ans);
			poss(head.right, ans);
			ans.add(String.valueOf(head.value));
		}
	}

	public static Node buildByPreQueue(Queue<String> prelist) {
		if (prelist == null || prelist.size() == 0) {
			return null;
		}
		return preb(prelist);
	}

	public static Node preb(Queue<String> prelist) {
		String value = prelist.poll();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = preb(prelist);
		head.right = preb(prelist);
		return head;
	}

	public static Node buildByPosQueue(Queue<String> poslist) {
		if (poslist == null || poslist.size() == 0) {
			return null;
		}
		// 左右中 -> stack(中右左)
		Stack<String> stack = new Stack<>();
		while (!poslist.isEmpty()) {
			stack.push(poslist.poll());
		}
		return posb(stack);
	}

	public static Node posb(Stack<String> posstack) {
		String value = posstack.pop();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.right = posb(posstack);
		head.left = posb(posstack);
		return head;
	}

	public static Queue<String> levelSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(head);
			while (!queue.isEmpty()) {
				head = queue.poll();
				if (head.left != null) {
					ans.add(String.valueOf(head.left.value));
					queue.add(head.left);
				} else {
					ans.add(null);
				}
				if (head.right != null) {
					ans.add(String.valueOf(head.right.value));
					queue.add(head.right);
				} else {
					ans.add(null);
				}
			}
		}
		return ans;
	}

	public static Node buildByLevelQueue(Queue<String> levelList) {
		if (levelList == null || levelList.size() == 0) {
			return null;
		}
		Node head = generateNode(levelList.poll());
		Queue<Node> queue = new LinkedList<Node>();
		if (head != null) {
			queue.add(head);
		}
		Node node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNode(levelList.poll());
			node.right = generateNode(levelList.poll());
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return head;
	}

	public static Node generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new Node(Integer.valueOf(val));
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

	// 2026测试
	// 1.先序序列化
	public static Queue<String> preSerialize(Node head) {
		Queue<String> queue = new LinkedList<String>();
		preSProcess(head, queue);
		return queue;
	}

	public static void preSProcess(Node head, Queue<String> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			queue.add(String.valueOf(head.value));
			preSProcess(head.left, queue);
			preSProcess(head.right, queue);
		}
	}

	// 1.先序反序列化
	public static Node preReconstruct(Queue<String> queue) {
		if (queue == null)
			return null;
		return preRProcess(queue);
	}

	public static Node preRProcess(Queue<String> queue) {
		if (queue.isEmpty())
			return null;
		String value = queue.poll();
		if (value == null) {
			return null;
		} else {
			Node cur = new Node(Integer.valueOf(value));
			cur.left = preReconstruct(queue);
			cur.right = preReconstruct(queue);
			return cur;
		}
	}

	// 2.1 后序序列化
	public static Queue<String> posSerialize(Node head) {
		Queue<String> queue = new LinkedList<String>();
		posSProcess(head, queue);
		return queue;
	}

	public static void posSProcess(Node head, Queue<String> queue) {
		if (head == null) {
			queue.add(null);
		} else {
			posSProcess(head.left, queue);
			posSProcess(head.right, queue);
			queue.add(String.valueOf(head.value));
		}
	}

	// 2.2后序反序列化
	public static Node posReconstruct(Queue<String> queue) {
		if (queue == null) {
			return null;
		}
		Stack<String> stack = new Stack<String>();
		while (!queue.isEmpty()) {
			stack.push(queue.poll());
		}
		return posRProcess(stack);
	}

	public static Node posRProcess(Stack<String> stack) {
		if (stack.isEmpty()) {
			return null;
		}
		String value = stack.pop();
		if (value == null) {
			return null;
		} else {
			Node cur = new Node(Integer.valueOf(value));
			cur.right = posRProcess(stack);
			cur.left = posRProcess(stack);
			return cur;
		}
	}

	public static Node createNode(String value) {
		if (value == null) {
			return null;
		} else {
			return new Node(Integer.valueOf(value));
		}
	}

	// 3.1 层序序列化
	public static Queue<String> levelSerialize(Node head) {
		Queue<String> res = new LinkedList<String>();
		if (head == null) {
			return res;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		res.add(String.valueOf(head.value));
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left == null) {
				res.add(null);
			} else {
				queue.add(cur.left);
				res.add(String.valueOf(cur.left.value));
			}
			if (cur.right == null) {
				res.add(null);
			} else {
				queue.add(cur.right);
				res.add(String.valueOf(cur.right.value));
			}
		}
		return res;
	}

	// 3.2 反层序序列化
	public static Node levelReconstruct(Queue<String> res) {
		if (res == null || res.isEmpty() || res.peek() == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(res.poll()));
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!res.isEmpty()) {
			Node cur = queue.poll();
			String value = res.poll();
			if (value != null) {
				cur.left = new Node(Integer.valueOf(value));
				queue.add(cur.left);
			}
			value = res.poll();
			if (value != null) {
				cur.right = new Node(Integer.valueOf(value));
				queue.add(cur.right);
			}
		}
		return head;
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
			Queue<String> level = levelSerialize(head);
			Node preBuild = preReconstruct(pre);
			Node posBuild = posReconstruct(pos);
			Node levelBuild = levelReconstruct(level);
			if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}
}
