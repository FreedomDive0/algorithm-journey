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

	// 常规做法
	public Node lowestCommonAncestor(Node root, Node p, Node q) {
		if (p == null || q == null) {
			return null;
		}
		HashMap<Node, Node> father = new HashMap<Node, Node>();
		father.put(root, null);
		getFatherMap(root, father);
		HashSet<Node> set = new HashSet<Node>();
		Node cur = p;
		while (cur != null) {
			set.add(cur);
			cur = father.get(cur);
		}
		while (q != null) {
			if (set.contains(q)) {
				break;
			}
			q = father.get(q);
		}
		return q;
	}

	public static void getFatherMap(Node node, HashMap<Node, Node> map) {
		if (node.left != null) {
			map.put(node.left, node);
			getFatherMap(node.left, map);
		}
		if (node.right != null) {
			map.put(node.right, node);
			getFatherMap(node.right, map);
		}
	}

	// 二叉树递归套路
	public static class LowestAncestorInfo {
		public boolean findO1;
		public boolean findO2;
		public Node ancestor;

		public LowestAncestorInfo(boolean findO1, boolean findO2, Node ancestor) {
			this.findO1 = findO1;
			this.findO2 = findO2;
			this.ancestor = ancestor;
		}

	}

	public static Node lowestCommonAncestor1(Node root, Node p, Node q) {
		return processL(root, p, q).ancestor;
	}

	public static LowestAncestorInfo processL(Node node, Node p, Node q) {
		if (node == null) {
			return new LowestAncestorInfo(false, false, null);
		}
		LowestAncestorInfo leftInfo = processL(node.left, p, q);
		LowestAncestorInfo rightInfo = processL(node.right, p, q);
		boolean findO1 = node == p || leftInfo.findO1 || rightInfo.findO1;
		boolean findO2 = node == q || leftInfo.findO2 || rightInfo.findO2;
		Node ancestor = leftInfo.ancestor;
		ancestor = rightInfo.ancestor != null ? rightInfo.ancestor : ancestor;
		if (ancestor == null) {
			if (findO1 && findO2) {
				ancestor = node;
			}
		}
		return new LowestAncestorInfo(findO1, findO2, ancestor);
	}
}
