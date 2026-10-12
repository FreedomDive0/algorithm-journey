package class08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code07_lowestAncestor {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static class Infor {
		boolean findO1;
		boolean findO2;
		Node firstNode;

		public Infor(boolean findO1, boolean findO2, Node firstNode) {
			super();
			this.findO1 = findO1;
			this.findO2 = findO2;
			this.firstNode = firstNode;
		}
	}

	public static Node lowestAncestor(Node head, Node o1, Node o2) {
		return process(head, o1, o2).firstNode;
	}

	public static Infor process(Node node, Node o1, Node o2) {
		if (node == null) {
			return new Infor(false, false, null);
		}
		Infor leftInfor = process(node.left, o1, o2);
		Infor rightInfor = process(node.right, o1, o2);
		boolean findO1 = node == o1 ? true : leftInfor.findO1 || rightInfor.findO1;
		boolean findO2 = node == o2 ? true : leftInfor.findO2 || rightInfor.findO2;
		Node FirstNode = null;
		if (leftInfor.firstNode != null) {
			FirstNode = leftInfor.firstNode;
		}
		if (rightInfor.firstNode != null) {
			FirstNode = rightInfor.firstNode;
		}
		if (FirstNode == null && (findO1 && findO2)) {// 第一次找到公共祖先
			FirstNode = node;
		}
		return new Infor(findO1, findO2, FirstNode);
	}

//	for test
	public static Node lowestAncestor1(Node head, Node o1, Node o2) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> parentMap = new HashMap();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
		HashSet<Node> pSet = new HashSet<Node>();
		Node cur = o1;
		pSet.add(cur);
		while (cur != null) {
			cur = parentMap.get(cur);
			pSet.add(cur);
		}
		cur = o2;
		while (!pSet.contains(cur)) {
			cur = parentMap.get(cur);
		}
		return cur;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
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
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}