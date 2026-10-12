package class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {
	// 最小生成树 P算法 Node
	public Set<Edge> primMST(Graph graph) {
		HashSet<Edge> res = new HashSet<Edge>();
		if (graph == null) {
			return res;
		}
		PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new EdgeCom());
		HashSet<Node> union = new HashSet<Node>();
		HashSet<Edge> edgeSet = new HashSet<Edge>();
		for (Node node : graph.nodes.values()) {
			if (union.contains(node)) {
				continue;
			}
			union.add(node);
			for (Edge edge : node.edges) {
				if (!edgeSet.contains(edge)) {
					heap.add(edge);
				}
			}
			while (!heap.isEmpty()) {
				Edge edge = heap.poll();
				if (!union.contains(edge.to)) {
					union.add(edge.to);
					res.add(edge);
					edgeSet.add(edge);
					for (Edge newEdge : edge.to.edges) {
						if (!edgeSet.contains(newEdge)) {
							heap.add(newEdge);
						}
					}
				}
			}
//			break;//防森林
		}
		return res;
	}

	public class EdgeCom implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}
}
