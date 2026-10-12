package class10;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Code04_Kruskal {
	public static class UnionSet {
		HashMap<Node, Node> parents;
		HashMap<Node, Integer> nodesSize;

		public UnionSet(Collection<Node> collection) {
			for (Node node : collection) {
				parents.put(node, node);
				nodesSize.put(node, 1);
			}
		}

		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}

		public Node findFather(Node node) {
			Node cur = node;
			Stack<Node> stack = new Stack<Node>();
			while (cur != parents.get(cur)) {
				stack.add(cur);
				cur = parents.get(cur);
			}
			while (!stack.isEmpty()) {// 扁平化处理
				parents.put(node, cur);
			}
			return cur;
		}

		public void union(Node a, Node b) {
			Node aHead = findFather(a);
			Node bHead = findFather(b);
			if (aHead != bHead) {
				Node big = nodesSize.get(aHead) > nodesSize.get(bHead) ? aHead : bHead;
				Node small = big == aHead ? bHead : aHead;
				parents.put(small, big);
				nodesSize.put(big, nodesSize.get(big) + nodesSize.get(small));
				nodesSize.remove(small);
			}
		}
	}

	public static class MyCompareTo implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static Set<Edge> K(Graph graph) {
		if (graph == null) {
			return null;
		}
		UnionSet nodeSet = new UnionSet(graph.nodes.values());
		HashSet<Edge> edgeSet = new HashSet<Edge>();
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>(new MyCompareTo());
		for (Edge edge : graph.edges) {
			edgeQueue.add(edge);
		}
		while (!edgeQueue.isEmpty()) {
			Edge cur = edgeQueue.poll();
			if (!nodeSet.isSameSet(cur.from, cur.to)) {
				edgeSet.add(cur);
				nodeSet.union(cur.from, cur.to);
			}
		}
		return edgeSet;
	}
}
