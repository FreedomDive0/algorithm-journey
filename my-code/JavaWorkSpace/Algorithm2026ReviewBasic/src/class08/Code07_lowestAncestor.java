package class08;

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

	public static Node lowestAncestor(Node head, Node n1, Node n2) {
		return lAProcess(head, n1, n2).ancestor;
	}

	public static class LAInfo {
		boolean findN1;
		boolean findN2;
		Node ancestor;

		public LAInfo(boolean findN1, boolean findN2, Node ancestor) {
			this.findN1 = findN1;
			this.findN2 = findN2;
			this.ancestor = ancestor;
		}

	}

	public static LAInfo lAProcess(Node node, Node n1, Node n2) {
		if (node == null) {
			return new LAInfo(false, false, null);
		}
		LAInfo leftInfo = lAProcess(node.left, n1, n2);
		LAInfo rightInfo = lAProcess(node.right, n1, n2);
		boolean findN1 = n1 == node || leftInfo.findN1 || rightInfo.findN1;
		boolean findN2 = n2 == node || leftInfo.findN2 || rightInfo.findN2;
		Node ancestor = null;
		if (leftInfo.ancestor != null) {
			ancestor = leftInfo.ancestor;
		}
		if (rightInfo.ancestor != null) {
			ancestor = rightInfo.ancestor;
		}
		if (ancestor == null && findN1 && findN2) {
			ancestor = node;
		}
		return new LAInfo(findN1, findN2, ancestor);

	}

	public static Node lowestAncestorHS(Node head, Node n1, Node n2) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		getNodeMap(head, map);
		HashSet<Node> set = new HashSet<Node>();
		Node cur = n1;
		while (map.containsKey(cur)) {
			set.add(cur);
			cur = map.get(cur);
		}
		set.add(head);// 头节点需要补上
		while (!set.contains(n2)) {
			n2 = map.get(n2);
		}
		return n2;
	}

	public static void getNodeMap(Node node, HashMap<Node, Node> map) {
		if (node.left != null) {
			map.put(node.left, node);
			getNodeMap(node.left, map);
		}
		if (node.right != null) {
			map.put(node.right, node);
			getNodeMap(node.right, map);
		}
	}

	public static Node lowestAncestorHS1(Node head, Node n1, Node n2) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		map.put(head, null);
		getNodeMap(head, map);
		HashSet<Node> set = new HashSet<Node>();
		Node cur = n1;
		set.add(cur);
		while (map.get(cur) != null) {
			cur = map.get(cur);
			set.add(cur);
		}
		cur = n2;
		while (!set.contains(cur)) {
			cur = map.get(cur);
		}
		return cur;
	}

}
