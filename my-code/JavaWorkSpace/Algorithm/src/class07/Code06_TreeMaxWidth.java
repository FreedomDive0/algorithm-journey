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

	public static int maxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		HashMap<Node, Integer> map = new HashMap<Node, Integer>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		map.put(head, 1);
		int curLevel = 1;
		int curLevelNodes = 0;// 因为从头开始弹出时 会增加个数所以在这里不加
		int max = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curNodeLevel = map.get(cur);
			if (cur.left != null) {
				queue.add(cur.left);
				map.put(cur.left, curNodeLevel + 1);
			}
			if (cur.right != null) {
				queue.add(cur.right);
				map.put(cur.right, curNodeLevel + 1);
			}
			if (curNodeLevel == curLevel) {
				curLevelNodes++;
			} else {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;
				curLevel++;
			}
		}
		max = Math.max(max, curLevelNodes);// 因为最后一层没有下一层所以在外部更新
		return max;
	}

	public static int maxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Node curEnd = head;
		Node nextEnd = null;
		queue.add(head);
//		int curLevelNodes = 0;
//		int max = 0;
		int curLevelNodes = 1;
		int max = 1;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
//			curLevelNodes++;
//			if(cur==curEnd) {
//				max = Math.max(max, curLevelNodes);
//				curEnd = nextEnd;
//				curLevelNodes = 0;
//			}
			if (cur != curEnd) {
				curLevelNodes++;
			} else {
				max = Math.max(max, curLevelNodes);
				curEnd = nextEnd;
				curLevelNodes = 1;
			}
		}
		return max;
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

	// 测试
	// 3.树的最大宽度
	// (1)使用HashMap
	public static int maxWidth1(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<Node>();
		HashMap<Node, Integer> levelMap = new HashMap<Node, Integer>();
		levelMap.put(head, 1);
		queue.add(head);
		int max = 0;
		int curLevel = 1;
		int curLevelNodes = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				levelMap.put(cur.left, levelMap.get(cur) + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				levelMap.put(cur.right, levelMap.get(cur) + 1);
				queue.add(cur.right);
			}
			if (curLevel == levelMap.get(cur)) {
				curLevelNodes++;
			} else {
				curLevel++;
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;
			}
		}
		max = Math.max(max, curLevelNodes);
		return max;
	}

	// (2)curEnd nextEnd标记
	public static int maxWidth2(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Node curEnd = head;
		Node NextEnd = null;
		queue.add(head);
		int max = 1;
		int curLevelNodes = 1;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				NextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				NextEnd = cur.right;
			}
			if (curEnd != cur) {
				curLevelNodes++;
			} else {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;// 为了处理一层只有一个节点情况
				curEnd = NextEnd;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxWidthUseMap(head) != maxWidth2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}
}
