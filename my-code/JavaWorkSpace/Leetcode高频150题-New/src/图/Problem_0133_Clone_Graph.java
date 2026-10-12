package 图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_0133_Clone_Graph {
	class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.add(node);
		Node copyHead = new Node(node.val);
		map.put(node, copyHead);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (Node neighbor : cur.neighbors) {
				if (!map.containsKey(neighbor)) {
					map.put(neighbor, new Node(neighbor.val));
					queue.add(neighbor);
				}
				map.get(cur).neighbors.add(map.get(neighbor));
			}
		}
		return copyHead;
	}

	public Node cloneGraph1(Node node) {
		if (node == null) {
			return null;
		}
		Node copyHead = new Node(node.val);
		Queue<Node> queue = new LinkedList<Node>();
		HashMap<Integer, Node> map = new HashMap<Integer, Node>();
		HashSet<Integer> set = new HashSet<Integer>();
		map.put(copyHead.val, copyHead);
		queue.add(node);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (set.contains(cur.val)) {
				continue;
			}
			Node copy = null;
			if (map.containsKey(cur.val)) {
				copy = map.get(cur.val);
			} else {
				copy = new Node(cur.val);
				map.put(cur.val, copy);
			}
			List<Node> copyList = new ArrayList<Node>();
			copy.neighbors = copyList;
			for (Node n : cur.neighbors) {
				if (map.containsKey(n.val)) {
					copyList.add(map.get(n.val));
				} else {
					Node cn = new Node(n.val);
					map.put(n.val, cn);
					copyList.add(cn);
				}
				queue.add(n);
			}
			set.add(cur.val);
		}
		return copyHead;
	}
}
