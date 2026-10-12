package class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Code05_Prim {

	public static class MyCompareTo implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static HashSet<Edge> prim(Graph graph) {
		if (graph == null) {
			return null;
		}
		HashSet<Edge> result = new HashSet<Edge>();
//		每次解锁的边
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>(new MyCompareTo());
//		防止重复加点与边
		HashSet<Node> nodeSet = new HashSet<Node>();
		HashSet<Edge> edgeSet = new HashSet<Edge>();
//		大循环防止森林
		for (Node node : graph.nodes.values()) {
			for (Edge edge : node.edges) {
				if (!edgeSet.contains(edge)) {
					edgeQueue.add(edge);
					edgeSet.add(edge);
				}
			}
//			取一条边解所一个点 再将剩余边放入
			while (!edgeQueue.isEmpty()) {
				Edge cur = edgeQueue.poll();
				if (!nodeSet.contains(cur.to)) {
					result.add(cur);
					nodeSet.add(cur.to);
					for (Edge nextEdge : cur.to.edges) {
						if (!edgeSet.contains(nextEdge)) {
							edgeQueue.add(nextEdge);
							edgeSet.add(nextEdge);
						}
					}
				}
			}
		}
		return result;
	}
}
