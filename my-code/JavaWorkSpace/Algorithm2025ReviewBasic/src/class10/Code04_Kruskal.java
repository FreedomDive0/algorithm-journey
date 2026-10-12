package class10;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Code04_Kruskal {
	// 最小生成树算法
	public static class UnionFind {
		public HashMap<Node, Node> fatherMap;
		public HashMap<Node, Integer> sizeMap;

		public UnionFind(Collection<Node> list) {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
			for (Node node : list) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public boolean isSameSet(Node n1, Node n2) {
			if (!fatherMap.containsKey(n1) || !fatherMap.containsKey(n2)) {
				return false;
			}
			return findFather(n1) == findFather(n2);
		}

		public void union(Node n1, Node n2) {
			if (!fatherMap.containsKey(n1) || !fatherMap.containsKey(n2)) {
				return;
			}
			Node f1 = findFather(n1);
			Node f2 = findFather(n2);
			if (f1 != f2) {
				int s1 = sizeMap.get(f1);
				int s2 = sizeMap.get(f2);
				Node big = s1 > s2 ? f1 : f2;
				Node small = big == f1 ? f2 : f1;
				fatherMap.put(small, big);
				sizeMap.put(big, s1 + s2);
				sizeMap.remove(small);
			}
		}

		private Node findFather(Node node) {
			if (!fatherMap.containsKey(node)) {
				return null;
			}
			Stack<Node> stack = new Stack<Node>();
			while (node != fatherMap.get(node)) {
				stack.push(node);
				node = fatherMap.get(node);
			}
			while (!stack.isEmpty()) {
				fatherMap.put(stack.pop(), node);
			}
			return node;
		}

	}

	public static class EdgeCom implements Comparator<Edge> {
		@Override
		public int compare(Edge e1, Edge e2) {
			return e1.weight - e2.weight;
		}
	}

	public static Set<Edge> kruskalMST(Graph graph) {
		HashSet<Edge> res = new HashSet<Edge>();
		if (graph == null) {
			return res;
		}
		UnionFind unionFind = new UnionFind(graph.nodes.values());
//		List<Edge> edges = new ArrayList<Edge>(graph.edges);
//		Collections.sort(edges, new EdgeCom());// 也可以使用小根堆实现
//		for (Edge edge : edges) {
//			if (!unionFind.isSameSet(edge.from, edge.to)) {
//				res.add(edge);
//				unionFind.union(edge.from, edge.to);
//			}
//		}
		PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new EdgeCom());
		for (Edge edge : graph.edges) {
			heap.add(edge);
		}
		while (!heap.isEmpty()) {
			Edge edge = heap.poll();
			if (!unionFind.isSameSet(edge.from, edge.to)) {
				res.add(edge);
				unionFind.union(edge.from, edge.to);
			}
		}
		return res;
	}
}
