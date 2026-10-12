package class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {
	public static class EdgeCom implements Comparator<Edge> {
		@Override
		public int compare(Edge e1, Edge e2) {
			return e1.weight - e2.weight;
		}
	}

	// 最小生成树算法
	public static Set<Edge> primMST(Graph graph) {
		HashSet<Edge> res = new HashSet<Edge>();
		if (graph == null) {
			return res;
		}
		PriorityQueue<Edge> heap = new PriorityQueue<Edge>();
		HashSet<Node> nodeSet = new HashSet<Node>();
		for (Node node : graph.nodes.values()) {
			if (!nodeSet.contains(node)) {// 需判断节点是否添加过
				nodeSet.add(node);
				for (Edge edge : node.edges) {
					heap.add(edge);
				}
				while (!heap.isEmpty()) {
					Edge cur = heap.poll();
					if (!nodeSet.contains(cur.to)) {
						res.add(cur);
						nodeSet.add(cur.to);
						for (Edge edge : cur.to.edges) {
							heap.add(edge);
						}
					}
				}
			}
		}
		return res;
	}
}
